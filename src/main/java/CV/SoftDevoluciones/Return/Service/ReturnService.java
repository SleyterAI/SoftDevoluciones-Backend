package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Return.Dto.ReturnRequestRequest;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Entity.ReturnRequest;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRequestRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnService;
import CV.SoftDevoluciones.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService implements IReturnService {

    private final ReturnRequestRepository returnRequestRepository;
    private final OrderRepository orderRepository;

    @Override
    public ReturnRequest createReturnRequest(String email, ReturnRequestRequest returnRequestRequest) {
        Order order = orderRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        ReturnRequest returnRequest = ReturnRequest.builder()
                .requestDate(LocalDateTime.now())
                .status(ReturnStatus.SOLICITADO)
                .reason(returnRequestRequest.getReason())
                .comment(returnRequestRequest.getComment())
                //.operatorNotes() null para CLIENTE
                .order(order)
                .build();

        ReturnDetail returnDetail = ReturnDetail.builder()
                .quantity(returnRequestRequest.getQuantity())
                .returnRequest(returnRequest)
                .build();

        returnRequest.setReturnDetail(returnDetail);
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public List<ReturnRequest> getUserReturnRequestsByEmail(String email) {
        return returnRequestRepository.findByOrderUserEmail(email);
    }

    @Override
    public ReturnDetail getReturnById(Long id) {
        return null;
    }

    @Override
    public ReturnDetail getAllReturn() {
        return null;
    }

    @Override
    public String updateReturnStatus(Long id) {
        return "";
    }
}
