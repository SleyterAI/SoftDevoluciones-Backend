package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Order.Repository.OrderDetailRepository;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnService implements IReturnService {

    private final ReturnRepository returnRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public Return createReturnRequest(String email, ReturnRequest request) {

        /*
        * se crea return y se asigna datos no enviados y enviados
        * despues se crea returndetail y se asigna datos datos
        * se asigna tambien el return q se tiene hasta el momento
        * despues se agrega returndetail a return
        * se guarda return en bd
        * */
        Order order = orderRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

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
        return returnRepository.save(aReturn);
    }

    @Override
    public List<Return> getUserReturnRequestsByEmail(String email) {
        return returnRepository.findByOrderUserEmail(email);
    }

    @Override
    public Return getReturnById(Long id) {
        return returnRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public ReturnDetail getAllReturn() {
        return null;
    }

    @Override
    public Return updateReturnStatus(Long id, ReturnStatus newEstado) {
        Return aReturn = returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El ticket no existe"));

        if (!aReturn.getStatus().validateWorkflow(newEstado)) {
            throw new RuntimeException(
                    "No se puede cambiar de " + aReturn.getStatus() + " a " + newEstado
            );
        }
        aReturn.setStatus(newEstado);
        return returnRepository.save(aReturn);
    }
}
