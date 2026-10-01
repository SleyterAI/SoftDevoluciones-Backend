package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnClientResponse;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnMessageCreated;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import java.util.List;

public interface IReturnClientService {

    //create
    ReturnMessageCreated createReturnRequest(String email, ReturnRequest returnRequest);

    //read
    List<ReturnClientResponse> getUserReturnRequestsByEmail(String email);
    ReturnClientResponse getReturnClientById(Long id);
}
