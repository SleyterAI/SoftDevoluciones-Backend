package CV.SoftDevoluciones.Return.Controller;

import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Service.ReturnAdminService;
import CV.SoftDevoluciones.Return.Service.ReturnClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/return")
@RequiredArgsConstructor
public class ReturnAdminController {
    private final ReturnAdminService returnAdminService;

    @GetMapping
    public ResponseEntity<List<ReturnAdminResponse>> getAllAdminReturn() {
        return ResponseEntity.ok(returnAdminService.getAllAdminReturn());
    }

    //GET: getreturnbyid - ADMIN -OPERATOR -CLIENTE
    @GetMapping("/{id}")
    public ResponseEntity<Return> getReturnClientById(@PathVariable Long id) {
        return ResponseEntity.ok(returnAdminService.getReturnAdminById(id));
    }

    //GET: getalldevoluciones?estado=xxxxx; paginacion: añadir limite de resultados visibles
    //ADMIN, filtros: estado, fecha desde, fecha hasta filtros


    //PATCH: estado -> workflow, referencia soporteYa
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateReturnStatus(@PathVariable Long id, @RequestBody Return newStatus) {
        Return aReturn = returnAdminService.updateReturnStatus(id, newStatus.getStatus());
        return ResponseEntity.ok("Product Id: "+aReturn.getId()+", visible now is: "+aReturn.getStatus());
    }


}
