package CV.SoftDevoluciones.Return.Service.Interface;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnClientResponse;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import java.util.List;

public interface IReturnClientService {

    //create
    Return createReturnRequest(String email, ReturnRequest returnRequest);

    //read
    Return getReturnClientById(Long id);
    List<ReturnClientResponse> getUserReturnRequestsByEmail(String email);




}
