package CV.SoftDevoluciones.Return.Controller;


import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Service.ReturnService;
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
public class ReturnController {

    private final ReturnService returnService;

    @PostMapping
    public ResponseEntity<Return> createTicket(Authentication authentication,
                                               @Valid @RequestBody ReturnRequest returnRequest) {
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnService.createReturnRequest(email, returnRequest));
    }

    //GET:  returns del cliente auth auth - CLIENTE
    @GetMapping("/my-returns")
    public ResponseEntity<List<Return>> getClientReturn(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(returnService.getUserReturnRequestsByEmail(email));
    }

    //GET: getreturnbyid - ADMIN -OPERATOR
    @GetMapping("/{id}")
    public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnService.getReturnById(id));
    }
    //GET: getalldevoluciones?estado=xxxxx; paginacion - verificar si se pueden añadir mas filtros
    //ADMIN

    //PATCH: estado -> workflow, referencia soporteYa
    @PatchMapping("/admin/{id}/status")
    public ResponseEntity<String> updateReturnStatus(@PathVariable Long id, @RequestBody Return newStatus) {
        Return aReturn = returnService.updateReturnStatus(id, newStatus.getStatus());
        return ResponseEntity.ok("Product Id: "+aReturn.getId()+", visible now is: "+aReturn.getStatus());
    }
}
