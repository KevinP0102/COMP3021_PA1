package hk.ust.comp3021.rank;

import hk.ust.comp3021.Order;

public class CustomerPriorityRank implements PendingOrderRank {
    @Override
    public int compare(Order source, Order target) {
        if (source.getCustomer().getCustomerType().equals(target.getCustomer().getCustomerType())) {
            return 0;
        } else if (source.getCustomer().getCustomerType() < target.getCustomer().getCustomerType()) {
            return 1;
        } else
            return -1;
    }
}
