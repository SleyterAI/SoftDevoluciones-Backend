package CV.SoftDevoluciones.Return.Controller;

import CV.SoftDevoluciones.Return.Dto.NoteRequest;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import CV.SoftDevoluciones.Return.Enum.ReturnStatus;
import CV.SoftDevoluciones.Return.Service.ReturnAdminService;
import CV.SoftDevoluciones.Return.Service.ReturnClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    //GET By Id
    @GetMapping("/{id}")
    public ResponseEntity<ReturnAdminResponse> getAdminReturnById(@PathVariable Long id) {
        return ResponseEntity.ok(returnAdminService.getReturnAdminById(id));
    }

    //GET filters
    @GetMapping("/filter")
    public ResponseEntity<List<ReturnAdminResponse>> getReturnsWithFilters(
            @RequestParam(required = false) ReturnStatus status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate toDate) {

        LocalDateTime startDateTime = (fromDate != null) ? fromDate.atStartOfDay() : null;
        LocalDateTime endDateTime = (toDate != null) ? toDate.atTime(LocalTime.MAX) : null;
        List<ReturnAdminResponse> response = returnAdminService.getReturnsWithFilers(status, startDateTime, endDateTime);
        return ResponseEntity.ok(response);
    }

    //PATCH: estado -> workflow
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateReturnStatus(@PathVariable Long id, @RequestBody Return newStatus) {
        Return aReturn = returnAdminService.updateReturnStatus(id, newStatus.getStatus());
        return ResponseEntity.ok("Product Id: "+aReturn.getId()+", visible now is: "+aReturn.getStatus());
    }

    @PatchMapping("/{id}/notes")
    public ResponseEntity<String> updateOperatorNotes(@PathVariable Long id, @RequestBody NoteRequest request) {
        Return aReturn = returnAdminService.updateOperatorNotes(id, request.getNotes());
        return ResponseEntity.ok("Return Id: "+aReturn.getId()+", operator notes: "+aReturn.getOperatorNotes());
    }
}
