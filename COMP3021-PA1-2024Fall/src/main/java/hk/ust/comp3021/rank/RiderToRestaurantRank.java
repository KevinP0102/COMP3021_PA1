package hk.ust.comp3021.rank;

import hk.ust.comp3021.Task;

public class RiderToRestaurantRank implements TaskRank{
    @Override
    public int compare(Task source, Task target) {
        if (source.getRider().getLocation().distanceTo(source.getOrder().getRestaurant().getLocation())
            < target.getRider().getLocation().distanceTo(target.getOrder().getRestaurant().getLocation())) {
            return -1;
        }   else if (source.getRider().getLocation().distanceTo(source.getOrder().getRestaurant().getLocation()).equals(target.getRider().getLocation().distanceTo(source.getOrder().getRestaurant().getLocation()))) {
            return 0;
        } else {
            return 1;
        }
    }
}
