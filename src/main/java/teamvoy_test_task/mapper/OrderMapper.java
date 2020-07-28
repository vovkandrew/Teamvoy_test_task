package teamvoy_test_task.mapper;

import org.springframework.stereotype.Component;
import teamvoy_test_task.model.Item;
import teamvoy_test_task.model.Order;
import teamvoy_test_task.model.dto.OrderRequestDto;
import teamvoy_test_task.model.dto.OrderResponseDto;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    private static final Integer EXPIRATION_PERIOD_MINS = 10;
    public Order getOrderFromOrderRequestDto(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        LocalDateTime willExpireIn = LocalDateTime.now().plusMinutes(EXPIRATION_PERIOD_MINS);
        order.setExpirationTime(willExpireIn);
        order.setPrice(orderRequestDto.getPrice());
        order.setQuantity(orderRequestDto.getQuantity());
        order.setItem(Item.valueOf(orderRequestDto.getItem()));
        return order;
    }

    public OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setExpirationTime(order.getExpirationTime().toString());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setQuantity(order.getQuantity());
        orderResponseDto.setItem(order.getItem().name());
        return orderResponseDto;
    }
}
