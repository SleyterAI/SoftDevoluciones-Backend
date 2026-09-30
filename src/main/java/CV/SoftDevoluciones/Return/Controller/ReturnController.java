package CV.SoftDevoluciones.Return.Controller;


import CV.SoftDevoluciones.Return.Dto.Return.ReturnRequest;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Service.ReturnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //GET: getreturnbyid - ADMIN -OPERATOR

    //GET: getalldevoluciones?estado=xxxxx; paginacion - verificar si se pueden añadir mas filtros
    //ADMIN

    //PATCH: estado -> workflow, referencia soporteYa
}
