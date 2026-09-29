package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Return.Dto.ReturnRequestRequest;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Entity.ReturnRequest;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnService;
import CV.SoftDevoluciones.User.Entity.User;
import CV.SoftDevoluciones.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReturnService implements IReturnService {

    private final ReturnRepository returnRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public ReturnRequest createReturn(String email, ReturnRequestRequest returnRequestRequest) {
        Order order = orderRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        ReturnRequest returnRequest = ReturnRequest.builder()
                .requestDate(LocalDateTime.now())
                .status(ReturnStatus.SOLICITADO)
                .reason(returnRequestRequest.getReason())
                .comment(returnRequestRequest.getComment())
                .operatorNotes(returnRequestRequest.getOperatorNotes())
                .order(order)
                .user(order.getUser())
                .build();
        return returnRepository.save(returnRequest);
    }

    @Override
    public List<ReturnDetail> getUserReturnByEmail(String email) {
        return List.of();
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
