package CV.SoftDevoluciones.Return.Mapper;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminRespListMapper {

    public List<ReturnAdminResponse> mapToReturnAdminResponse(List<Return> returns) {
        return returns.stream()
                .map(aReturn -> ReturnAdminResponse.builder()
                        .return_id(aReturn.getId())
                        .user_name(aReturn.getOrder().getUser().getUsername())
                        .order_id(aReturn.getOrder().getId())
                        .date(aReturn.getRequestDate())
                        .status(aReturn.getStatus())
                        .returnTotal(aReturn.getOrder().getTotal())
                        .build())
                .toList();
    }
}
