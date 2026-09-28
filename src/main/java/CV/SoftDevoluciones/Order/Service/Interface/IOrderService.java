package CV.SoftDevoluciones.Order.Service.Interface;

import CV.SoftDevoluciones.Order.Dto.OrderRequestDto;
import CV.SoftDevoluciones.Order.Dto.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;

import java.util.List;

public interface IOrderService {
    //Create
    OrderResponseDto createOrder(String email, OrderRequestDto orderRequestDto);

    //Read
    List<OrderSummaryResponseDto> getAllOrder();
    OrderResponseDto getOrderById(Long id);

    //Update
    Order updateOrder(Long id, Order order);

    //Delete
    void deleteOrder(Long id);
}
