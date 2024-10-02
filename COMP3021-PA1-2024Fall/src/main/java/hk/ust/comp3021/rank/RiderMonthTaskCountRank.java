package hk.ust.comp3021.rank;

import hk.ust.comp3021.Task;

import java.util.Comparator;

public class RiderMonthTaskCountRank implements TaskRank {

    @Override
    public int compare(Task source, Task target) {
        if (source.getRider().getMonthTaskCount() < target.getRider().getMonthTaskCount()) {
            return -1;
        } else if (source.getRider().getMonthTaskCount() > target.getRider().getMonthTaskCount()) {
            return 1;
        } else {
            return 0;
        }
    }
}
