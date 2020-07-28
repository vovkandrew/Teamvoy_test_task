package teamvoy_test_task.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamvoy_test_task.model.Item;
import teamvoy_test_task.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order o where o.item = ?1 and o.quantity >= ?2 order by o.price asc")
    public List<Order> findOrdersByItemAndQuantity(Item item, Integer quantity);

    @Query(value = "select o from Order o where o.item = ?1 order by o.price asc")
    public List<Order> findOrdersByItem(Item item);
}
