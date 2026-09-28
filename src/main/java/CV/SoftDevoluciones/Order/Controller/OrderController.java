package CV.SoftDevoluciones.Order.Controller;

import CV.SoftDevoluciones.Order.Dto.OrderRequestDto;
import CV.SoftDevoluciones.Order.Dto.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public ResponseEntity<List<OrderSummaryResponseDto>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
