package CV.SoftDevoluciones.Order.Mapper;

import CV.SoftDevoluciones.Order.Dto.OrderDetail.OrderDetailResponseDto;
import CV.SoftDevoluciones.Order.Dto.Order.OrderResponseDto;
import CV.SoftDevoluciones.Order.Dto.OrderSummary.OrderSummaryResponseDto;
import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Order.Entity.OrderDetail;
import CV.SoftDevoluciones.Product.Entity.Product;
import CV.SoftDevoluciones.User.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    //method for getAllOrder
    //returns -> full summary order
    public OrderSummaryResponseDto toOrderSummaryDto(Order order) {
        User user = order.getUser();
        return OrderSummaryResponseDto.builder()
                // Order
                .order_id(order.getId())
                .order_phoneNumber(order.getPhoneNumber())
                .order_address(order.getAddress())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                // User
                .user_name(user.getUsername())
                .user_email(user.getEmail())
                .build();
    }

    // 2 methods for getOrderById
    //returns -> full order + orderDetail
    public OrderResponseDto toOrderDto(Order order) {
        User user = order.getUser();
        return OrderResponseDto.builder()

                // Order
                .order_id(order.getId())
                .order_phoneNumber(order.getPhoneNumber())
                .order_address(order.getAddress())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                // User
                .user_name(user.getUsername())
                .user_email(user.getEmail())

                // OrderDetails

                .orderDetailResponseDto(
                        order.getOrderDetail()
                                .stream()
                                .map(this::toOrderDetailDto)
                                .toList()
                )

                .build();
    }

    public OrderDetailResponseDto toOrderDetailDto(OrderDetail orderDetail) {

        Product product = orderDetail.getProduct();
        Order order = orderDetail.getOrder();
        User user = orderDetail.getOrder().getUser();

        return OrderDetailResponseDto.builder()

                // OrderDetail
                .orderDetail_id(orderDetail.getId())
                .orderDetail_quantity(orderDetail.getQuantity())
                .orderDetail_unitPrice(orderDetail.getUnitPrice())
                .orderDetail_subTotal(orderDetail.getSubTotal())

                // Product
                .product_id(product.getId())
                .product_name(product.getName())
                .product_imageUrl(product.getImageUrl())

                // Category
                .category_name(orderDetail.getProduct().getCategory().getName())

                //User
                .user_name(user.getUsername())
                .user_email(user.getEmail())

                //Order
                .order_address(order.getAddress())
                .order_phoneNumber(order.getPhoneNumber())
                .order_date(order.getDate())
                .order_status(order.getStatus())
                .order_total(order.getTotal())

                .build();
    }
}
