package teamvoy_test_task.model.dto;

import teamvoy_test_task.model.Item;

import java.math.BigDecimal;

public class OrderRequestDto {
    private BigDecimal price;
    private Integer quantity;
    private String item;

    public OrderRequestDto() {
    }

    public OrderRequestDto(BigDecimal price, Integer quantity, String item) {
        this.price = price;
        this.quantity = quantity;
        this.item = item;
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
