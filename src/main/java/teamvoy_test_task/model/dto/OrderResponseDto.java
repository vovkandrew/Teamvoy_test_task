package teamvoy_test_task.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDto {
    private String expirationTime;
    private BigDecimal price;
    private Integer quantity;
    private String item;

    public OrderResponseDto() {
    }

    public OrderResponseDto(String expirationTime, BigDecimal price, Integer quantity, String item) {
        this.expirationTime = expirationTime;
        this.price = price;
        this.quantity = quantity;
        this.item = item;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
