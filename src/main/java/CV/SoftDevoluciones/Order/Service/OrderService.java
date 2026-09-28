package CV.SoftDevoluciones.Order.Service;

import CV.SoftDevoluciones.Order.Dto.OrderRequestDto;
import CV.SoftDevoluciones.Order.Dto.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Mapper.OrderMapper;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Order.Service.Interface.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(String email, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public List<OrderSummaryResponseDto> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderSummaryDto)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
         Order order = orderRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException("El pedido no existe")
                );

        return orderMapper.toOrderDto(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }

}
