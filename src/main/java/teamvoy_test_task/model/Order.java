package teamvoy_test_task.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime expirationTime;
    private BigDecimal price;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private Item item;

    public Order() {
    }

    public Order(Long orderId, BigDecimal price, int quantity, Item item) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.item = item;
    }

    public Order(Long orderId, LocalDateTime expirationTime, BigDecimal price, Integer quantity, Item item) {
        this.orderId = orderId;
        this.expirationTime = expirationTime;
        this.price = price;
        this.quantity = quantity;
        this.item = item;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
