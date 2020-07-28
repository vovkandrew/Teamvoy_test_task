package teamvoy_test_task.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamvoy_test_task.model.Item;
import teamvoy_test_task.model.Order;
import teamvoy_test_task.model.dto.OrderRequestDto;
import teamvoy_test_task.model.dto.OrderResponseDto;
import teamvoy_test_task.mapper.OrderMapper;
import teamvoy_test_task.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    private void setOrderServiceOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private OrderMapper orderMapper;

    @Autowired
    private void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @PostMapping("/")
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.getOrderFromOrderRequestDto(orderRequestDto);
        orderService.save(order);
        return orderMapper.getOrderResponseDtoFromOrder(order);
    }

    @GetMapping("/{item}+{quantity}")
    public List<OrderResponseDto> getTheCheapestOrderByItem(@PathVariable String item, @PathVariable Integer quantity) {
        List<Order> order = orderService.findOrdersByItemAndQuantity(Item.valueOf(item), quantity);
        if (order.isEmpty()) {
            List<Order> orders = orderService.findOrdersByItem(Item.valueOf(item));
            return orders.stream()
                    .map(o -> orderMapper.getOrderResponseDtoFromOrder(o))
                    .collect(Collectors.toList());
        }
        Order cheapest = order.stream().findFirst().get();
        cheapest.setQuantity(cheapest.getQuantity() - quantity);
        if (cheapest.getQuantity() == 0) {
            orderService.removeOrder(cheapest);
        } else {
            orderService.save(cheapest);
        }
        cheapest.setQuantity(quantity);
        return List.of(orderMapper.getOrderResponseDtoFromOrder(cheapest));
    }
}
