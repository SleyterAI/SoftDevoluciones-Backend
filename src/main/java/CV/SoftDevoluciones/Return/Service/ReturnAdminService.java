package CV.SoftDevoluciones.Return.Service;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Mapper.AdminRespListMapper;
import CV.SoftDevoluciones.Return.Repository.ReturnRepository;
import CV.SoftDevoluciones.Return.Service.Interface.IReturnAdminService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ReturnAdminService implements IReturnAdminService {

    private final ReturnRepository returnRepository;
    private final AdminRespListMapper adminRespListMapper;

    @Override
    public List<ReturnAdminResponse> getAllAdminReturn() {
        List<Return> returns = returnRepository.findAll();
        return adminRespListMapper.mapToReturnAdminResponse(returns);
    }

    @Override
    public ReturnAdminResponse getReturnAdminById(Long id) {
        Return aReturn = returnRepository.findById(id)
                .orElseThrow();
        return ReturnAdminResponse.builder()
                .return_id(aReturn.getId())
                .user_name(aReturn.getOrder().getUser().getUsername())
                .order_id(aReturn.getOrder().getId())
                .date(aReturn.getRequestDate())
                .status(aReturn.getStatus())
                .returnTotal(aReturn.getOrder().getTotal())
                .operatorNotes(aReturn.getOperatorNotes())
                .build();
    }

    @Override
    public List<ReturnAdminResponse> getReturnsWithFilers(ReturnStatus status, LocalDateTime fromDate, LocalDateTime toDate) {
        Specification<Return> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("requestDate"), fromDate));
            }
            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("requestDate"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Return> returns = returnRepository.findAll(spec);
        return adminRespListMapper.mapToReturnAdminResponse(returns);
    }

    @Override
    public Return updateReturnStatus(Long id, ReturnStatus newEstado) {
        Return aReturn = returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El return no existe"));

        if (!aReturn.getStatus().validateWorkflow(newEstado)) {
            throw new RuntimeException(
                    "No se puede cambiar de " + aReturn.getStatus() + " a " + newEstado
            );
        }
        aReturn.setStatus(newEstado);
        return returnRepository.save(aReturn);
    }

    public Return updateOperatorNotes(Long return_id, String notes){
        Return aReturn = returnRepository.findById(return_id)
                .orElseThrow(() -> new RuntimeException("El return no existe"));
        aReturn.setOperatorNotes(notes);
        return returnRepository.save(aReturn);
    }
}
