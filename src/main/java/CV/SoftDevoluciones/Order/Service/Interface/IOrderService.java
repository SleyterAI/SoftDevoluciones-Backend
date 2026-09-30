package CV.SoftDevoluciones.Order.Service.Interface;

import CV.SoftDevoluciones.Order.Dto.Order.OrderRequestDto;
import CV.SoftDevoluciones.Order.Dto.Order.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderDetail.OrderDetailResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;

import java.util.List;

public interface IOrderService {

    //ADMIN
    List<OrderSummaryResponseDto> getAllOrder();

    //ADMIN - CLIENTE
    OrderResponseDto getOrderById(Long id);

    //CLIENTE
    List<OrderSummaryResponseDto> getAllClientOrders(String email);
    //CLIENTE
    OrderDetailResponseDto getOrderDetailByUserEmail(String email);

}
