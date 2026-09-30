package CV.SoftDevoluciones.Order.Mapper;

import CV.SoftDevoluciones.Order.Dto.Order.OrderResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Return.Dto.Return.ReturnAdminResponse;
import CV.SoftDevoluciones.Return.Entity.Return;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderListMapper {
    public List<OrderResponseDto> mapToOrderListResponse(List<Order> order) {
        return order.stream()
                .map(aReturn -> OrderResponseDto.builder()
                        .return_id(aReturn.getId())
                        .user_name(aReturn.getOrder().getUser().getUsername())
                        .order_id(aReturn.getOrder().getId())
                        .date(aReturn.getRequestDate())
                        .status(aReturn.getStatus())
                        .returnTotal(aReturn.getOrder().getTotal())
                        .build())
                .toList();
    }
}
