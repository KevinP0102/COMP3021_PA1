package hk.ust.comp3021.rank;

import hk.ust.comp3021.Order;

public class RestaurantToCustomerDistanceRank implements PendingOrderRank{
    @Override
    public int compare(Order source, Order target) {
        Double sourceDistance = source.getRestaurant().getLocation().distanceTo(source.getCustomer().getLocation());
        Double targetDistance = target.getRestaurant().getLocation().distanceTo(target.getCustomer().getLocation());

        return sourceDistance.compareTo(targetDistance);
    }
}
