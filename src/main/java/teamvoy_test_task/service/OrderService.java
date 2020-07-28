package teamvoy_test_task.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamvoy_test_task.model.Item;
import teamvoy_test_task.model.Order;
import teamvoy_test_task.repository.OrderRepository;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    private void setOrderRepositoryOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findOrdersByItem(Item item) {
        return orderRepository.findOrdersByItem(item);
    }

    public List<Order> findOrdersByItemAndQuantity(Item item, Integer quantity) {
        return orderRepository.findOrdersByItemAndQuantity(item, quantity);
    }

    public void removeOldOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.stream()
                .filter(o -> o.getExpirationTime().isBefore(LocalDateTime.now()))
                .forEach(o -> orderRepository.delete(o));
    }

    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }
}
