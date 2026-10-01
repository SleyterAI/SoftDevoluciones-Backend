package CV.SoftDevoluciones.Return.Controller;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnClientResponse;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnMessageCreated;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Service.ReturnClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return")
@RequiredArgsConstructor
public class ReturnClientController {

    private final ReturnClientService returnClientService;

    @PostMapping
    public ResponseEntity<ReturnMessageCreated> createReturn(Authentication authentication,
                                                             @Valid @RequestBody ReturnRequest returnRequest) {
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnClientService.createReturnRequest(email, returnRequest));
    }

    //GET:  returns del cliente auth auth - CLIENTE
    @GetMapping("/my-returns")
    public ResponseEntity<List<ReturnClientResponse>> getClientReturn(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(returnClientService.getUserReturnRequestsByEmail(email));
    }

    //GET: getreturnbyid - ADMIN -OPERATOR -CLIENTE
    @GetMapping("/{id}")
        public ResponseEntity<ReturnClientResponse> getClientReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnClientService.getReturnClientById(id));
    }
}
