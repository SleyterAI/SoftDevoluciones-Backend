package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Entity.ReturnRequest;

import java.util.List;

public interface IReturnService {

    //create return
    ReturnRequest createReturn(ReturnRequest returnRequest);

    //read return
    List<ReturnDetail> getUserReturnByEmail(String email);
    ReturnDetail getReturnById(Long id);

    //filtros y paginacion ADMIN
    ReturnDetail getAllReturn();

    //update return PATCH ADMIN
    String updateReturnStatus(Long id);

    //delete return

}
