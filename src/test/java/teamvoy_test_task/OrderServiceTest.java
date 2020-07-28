package teamvoy_test_task;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import teamvoy_test_task.model.Item;
import teamvoy_test_task.model.Order;
import teamvoy_test_task.repository.OrderRepository;
import teamvoy_test_task.service.OrderService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOrdersByItemOk() {
        List<Order> orders = new ArrayList<>();
        Order orderOne = new Order(1L, LocalDateTime.now().minusMinutes(5),
                BigDecimal.valueOf(10L),10, Item.valueOf("TEA"));
        Order orderTwo = new Order(2L, LocalDateTime.now().minusMinutes(4),
                BigDecimal.valueOf(9L),20, Item.valueOf("TEA"));
        Order orderThree = new Order(3L, LocalDateTime.now().minusMinutes(3),
                BigDecimal.valueOf(11L),30, Item.valueOf("TEA"));
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        when(orderRepository.findOrdersByItem(Item.valueOf("TEA"))).thenReturn(orders);
        List<Order> testOrders = orderService.findOrdersByItem(Item.valueOf("TEA"));
        Assert.assertEquals(3,testOrders.size());
        verify(orderRepository, times(1)).findOrdersByItem(Item.valueOf("TEA"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOrdersByItemWrongItemName() {
        List<Order> orders = new ArrayList<>();
        Order orderOne = new Order(1L, LocalDateTime.now().minusMinutes(5),
                BigDecimal.valueOf(10L),10, Item.valueOf("TEA"));
        Order orderTwo = new Order(2L, LocalDateTime.now().minusMinutes(4),
                BigDecimal.valueOf(9L),20, Item.valueOf("TEA"));
        orders.add(orderOne);
        orders.add(orderTwo);
        when(orderRepository.findOrdersByItem(Item.valueOf("TEA"))).thenReturn(orders);
        List<Order> testOrders = orderService.findOrdersByItem(Item.valueOf("RANDOM"));
        Assert.assertEquals(3,testOrders.size());
        verify(orderRepository, times(1)).findOrdersByItem(Item.valueOf("TEA"));
    }

    @Test
    public void findOrdersByItemAndQuantityOk() {
        List<Order> orders = new ArrayList<>();
        Order orderTwo = new Order(2L, LocalDateTime.now().minusMinutes(4),
                BigDecimal.valueOf(9L),20, Item.valueOf("TEA"));
        Order orderThree = new Order(3L, LocalDateTime.now().minusMinutes(3),
                BigDecimal.valueOf(11L),30, Item.valueOf("TEA"));
        orders.add(orderTwo);
        orders.add(orderThree);
        when(orderRepository.findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20)).thenReturn(orders);
        List<Order> testOrders = orderService.findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20);
        Assert.assertEquals(2,testOrders.size());
        verify(orderRepository, times(1)).
                findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20);
    }

    @Test
    public void findOrdersByItemAndQuantityEmpty() {
        when(orderRepository.findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20)).thenReturn(List.of());
        List<Order> testOrders = orderService.findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20);
        Assert.assertEquals(0,testOrders.size());
        verify(orderRepository, times(1)).
                findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOrdersByItemAndQuantityWrongItemName() {
        when(orderRepository.findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20)).thenReturn(List.of());
        List<Order> testOrders = orderService.findOrdersByItemAndQuantity(Item.valueOf("RANDOM"), 20);
        Assert.assertEquals(0,testOrders.size());
        verify(orderRepository, times(1)).
                findOrdersByItemAndQuantity(Item.valueOf("TEA"), 20);
    }
}
