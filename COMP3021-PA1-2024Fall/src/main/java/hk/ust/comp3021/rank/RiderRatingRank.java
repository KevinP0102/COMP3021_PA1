package hk.ust.comp3021.rank;

import hk.ust.comp3021.Task;

import java.util.Comparator;

public class RiderRatingRank implements TaskRank {

    @Override
    public int compare(Task source, Task target) {
        if (source.getRider().getUserRating() > target.getRider().getUserRating()) {
            return -1;
        } else if (source.getRider().getUserRating() < target.getRider().getUserRating()) {
            return 1;
        } else {
            return 0;
        }

    }
}
