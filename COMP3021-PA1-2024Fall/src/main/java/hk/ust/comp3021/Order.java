package hk.ust.comp3021;

import java.util.List;

public class Order {
    private Long id;
    private Integer status;
    private Restaurant restaurant;
    private Customer customer;
    private Long createTime;
    private Boolean isPayed;
    private List<Dish> dishes;
    private Rider rider;
    private Double estimatedTime;

    public Order(Long id, Integer status, Restaurant restaurant, Customer customer, Long createTime, Boolean isPayed, List<Dish> dishes, Rider rider) {
        this.id = id;
        this.status = status;
        this.restaurant = restaurant;
        this.customer = customer;
        this.createTime = createTime;
        this.isPayed = isPayed;
        this.dishes = dishes;
        this.rider = rider;
        this.estimatedTime = (double) 0;
    }

    public Double calculateEstimatedTime() {}

    public Long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public List<Dish> getOrderedDishes() {
        return dishes;
    }

    public Rider getRider() {
        return rider;
    }

    public Double getEstimatedTime() {
        return estimatedTime;
    }
}
