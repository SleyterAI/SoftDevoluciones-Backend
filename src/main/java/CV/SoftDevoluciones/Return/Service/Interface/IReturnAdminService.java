package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;

import java.util.List;

public interface IReturnAdminService {

    //read
    List<ReturnAdminResponse> getAllAdminReturn();
    Return getReturnAdminById(Long id);

    //update return PATCH ADMIN
    Return updateReturnStatus(Long id, ReturnStatus newEstado);
}
