package hk.ust.comp3021.rank;

import hk.ust.comp3021.Order;

public class OrderCreateTimeRank implements PendingOrderRank{
    @Override
    public int compare(Order source, Order target) {
        if (source.getCreateTime() < target.getCreateTime()) {
            return 1;
        } else if (source.getCreateTime() > target.getCreateTime()) {
            return -1;
        } else {
            return 0;
        }
    }
}
