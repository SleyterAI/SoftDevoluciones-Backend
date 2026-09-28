package CV.SoftDevoluciones.Order.Controller;

import CV.SoftDevoluciones.Order.Dto.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderSummaryResponseDto>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
