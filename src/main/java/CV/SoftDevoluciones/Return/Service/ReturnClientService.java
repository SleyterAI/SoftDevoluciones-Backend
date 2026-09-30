package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Order.Repository.OrderDetailRepository;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnClientResponse;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    @Override
    public Return createReturnRequest(String email, ReturnRequest request) {

        /*
        * se crea return y se asigna datos no enviados y enviados
        * despues se crea returndetail y se asigna datos datos
        * se asigna tambien el return q se tiene hasta el momento
        * despues se agrega returndetail a return
        * se guarda return en bd
        *
        Order order = orderRepository.findByUserEmail(email);
    // deberia de buscar orderdetail creoooo...
        OrderDetail orderDetail = orderDetailRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order detail not found"));

        if (!orderDetail.getOrder().getId().equals(order.getId())) {
            throw new IllegalArgumentException("The order detail does not belong to this order");
        }

        Return aReturn = Return.builder()
                .requestDate(LocalDateTime.now())
                .status(ReturnStatus.SOLICITADO)
                .reason(request.getReason())
                .comment(request.getComment())
                .operatorNotes("Sin comentarios")
                .amount(request.getAmount())
                .order(order)
                .build();

        ReturnDetail returnDetail = ReturnDetail.builder()
                .quantity(request.getReturnDetailRequest().getQuantity()) //problema
                .orderDetail(orderDetail)
                .aReturn(aReturn)
                .build();

        aReturn.setReturnDetail(returnDetail);
        return returnRepository.save(aReturn);*/
        return null;
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
                .returnTotal(aReturn.getOrder().getTotal())
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
