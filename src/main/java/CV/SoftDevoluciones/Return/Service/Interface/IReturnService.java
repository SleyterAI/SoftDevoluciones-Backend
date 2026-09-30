package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;

import java.util.List;

public interface IReturnService {

    //create return
    Return createReturnRequest(String email, ReturnRequest returnRequest);

    //read return
    List<Return> getUserReturnRequestsByEmail(String email);

    Return getReturnById(Long id);

    //filtros y paginacion ADMIN
    ReturnDetail getAllReturn();

    //update return PATCH ADMIN
    Return updateReturnStatus(Long id, ReturnStatus newEstado);

}
