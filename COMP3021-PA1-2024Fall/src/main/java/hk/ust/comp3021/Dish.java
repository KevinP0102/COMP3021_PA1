package hk.ust.comp3021;

import java.math.BigDecimal;

public class Dish {
    private Long id;
    private String name;
    private String desc;
    private BigDecimal price;
    private Long restaurantId;

    public Dish(Long id, String name, String desc, BigDecimal price, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Dish(" +
                "id=" + id +
                ", name=" + name +
                ", desc=" + desc +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ')';
    }
}
