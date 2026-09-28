package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Return.Dto.ReturnRequestRequest;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Entity.ReturnRequest;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService implements IReturnService {

    private final ReturnRepository returnRepository;

    @Override
    public ReturnRequest createReturn(Authentication authentication, ReturnRequestRequest returnRequestRequest) {
        //order id
        String email = authentication.getName();
        Long order_id = returnRepository.findUserIdByEmail(email);
        Order order;
        order.setId
        //user id

        ReturnRequest returnRequest = ReturnRequest.builder()
                .requestDate(LocalDateTime.now())
                .status(ReturnStatus.SOLICITADO)
                .reason(returnRequestRequest.getReason())
                .comment(returnRequestRequest.getComment())
                .operatorNotes(returnRequestRequest.getOperatorNotes())
                .order(order_id)
                .user()
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
