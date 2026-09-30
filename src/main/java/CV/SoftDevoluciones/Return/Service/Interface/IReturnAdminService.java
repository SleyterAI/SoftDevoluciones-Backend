package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Dto.ReturnDetail.ReturnDetailAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IReturnAdminService {

    //read
    List<ReturnAdminResponse> getAllAdminReturn();
    ReturnAdminResponse getReturnAdminById(Long id);
    List<ReturnAdminResponse> getReturnsWithFilers(ReturnStatus status, LocalDateTime fromDate, LocalDateTime toDate);
    //update return PATCH ADMIN
    Return updateReturnStatus(Long id, ReturnStatus newEstado);
}
