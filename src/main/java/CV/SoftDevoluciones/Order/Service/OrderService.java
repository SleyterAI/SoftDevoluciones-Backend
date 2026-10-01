package CV.SoftDevoluciones.Order.Service;

import CV.SoftDevoluciones.Order.Dto.Order.OrderRequestDto;
import CV.SoftDevoluciones.Order.Dto.Order.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderDetail.OrderDetailResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Order.Mapper.OrderMapper;
import CV.SoftDevoluciones.Order.Repository.OrderDetailRepository;
import CV.SoftDevoluciones.Order.Repository.OrderRepository;
import CV.SoftDevoluciones.Order.Service.Interface.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;

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
    public List<OrderSummaryResponseDto> getAllClientOrders(String email) {
        return orderRepository.findByUserEmail(email)
                .stream()
                .map(orderMapper::toOrderSummaryDto)
                .toList();
    }


    //1ero ordersummary -> 2do orderresponse (aca viene incluido orderdetail)
    // yo : getorderdetail (ahi viene order)

    @Override
    public OrderDetailResponseDto getOrderDetailByOrderIdAndProductId(Long orderId, Long productId) {
         OrderDetail orderDetail = orderDetailRepository
                .findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));
        return orderMapper.toOrderDetailDto(orderDetail);
    }


}
