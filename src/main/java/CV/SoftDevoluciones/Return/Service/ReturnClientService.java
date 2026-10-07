package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Order.Repository.OrderDetailRepository;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnClientResponse;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnMessageCreated;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnClientService implements IReturnClientService {

    private final ReturnRepository returnRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    //TERMINAR¡¡¡¡
    /*
     * se crea return y se asigna datos no enviados y enviados
     * despues se crea returndetail y se asigna datos datos
     * se asigna tambien el return q se tiene hasta el momento
     * despues se agrega returndetail a return
     * se guarda return en bd
     **/
    @Override
    @Transactional
    public ReturnMessageCreated createReturnRequest(String email, ReturnRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(request.getOrderDetail_id())
                .orElseThrow(() -> new EntityNotFoundException("Order detail not found"));

        Order order = orderDetail.getOrder();

        if (!order.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException("The order does not belong to the user");
        }

        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (request.getQuantity() > orderDetail.getQuantity()) {
            throw new IllegalArgumentException("Return quantity cannot exceed purchased quantity");
        }

        BigDecimal amount = orderDetail.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        Return aReturn = Return.builder()
                .requestDate(LocalDateTime.now())
                .status(ReturnStatus.SOLICITADO)
                .reason(request.getReason())
                .comment(request.getComment())
                .operatorNotes(null)
                .amount(amount)
                .order(order)
                .build();

        ReturnDetail returnDetail = ReturnDetail.builder()
                .quantity(request.getQuantity())
                .orderDetail(orderDetail)
                .aReturn(aReturn)
                .build();

        aReturn.setReturnDetail(returnDetail);
        returnRepository.save(aReturn);
        return ReturnMessageCreated.builder()
                .message("Return created correctly")
                .build();
    }


    @Override
    public List<ReturnClientResponse> getUserReturnRequestsByEmail(String email) {
        List<Return> returns = returnRepository.findByOrderUserEmail(email);
        return returns.stream().map(aReturn -> ReturnClientResponse.builder()
                .return_id(aReturn.getId())
                .order_id(aReturn.getOrder().getId())
                .date(aReturn.getRequestDate())
                .productsQuantity(aReturn.getReturnDetail().getQuantity())
                .status(aReturn.getStatus())
                .returnTotal(aReturn.getAmount())
                .build())
                .toList();
    }

    @Override
    public ReturnClientResponse getReturnClientById(Long id) {
        Return aReturn = returnRepository.findById(id)
                .orElseThrow();
        return ReturnClientResponse.builder()
                .return_id(aReturn.getId())
                .order_id(aReturn.getOrder().getId())
                .date(aReturn.getRequestDate())
                .productsQuantity(aReturn.getReturnDetail().getQuantity())
                .status(aReturn.getStatus())
                .returnTotal(aReturn.getOrder().getTotal())
                .build();
    }

}
