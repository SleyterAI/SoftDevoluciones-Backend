package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnAdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnAdminService implements IReturnAdminService {

    private final ReturnRepository returnRepository;

    @Override
    public List<ReturnAdminResponse> getAllAdminReturn() {
        List<Return> returns = returnRepository.findAll();
        return returns.stream().map(aReturn -> ReturnAdminResponse.builder()
                        .return_id(aReturn.getId())
                        .user_name(aReturn.getOrder().getUser().getUsername())
                        .order_id(aReturn.getOrder().getId())
                        .date(aReturn.getRequestDate())
                        .status(aReturn.getStatus())
                        .returnTotal(aReturn.getOrder().getTotal())
                        .build())
                .toList();
    }

    @Override
    public Return getReturnAdminById(Long id) {
        return returnRepository.findById(id)
                .orElseThrow();
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
