package hk.ust.comp3021;

public class Task {
    private Order order;
    private Rider rider;

    public Task(Order order, Rider rider) {
        this.order = order;
        this.rider = rider;
    }

    public Order getOrder() {
        return order;
    }

    public Rider getRider() {
        return rider;
    }
}
