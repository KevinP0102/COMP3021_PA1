package hk.ust.comp3021;

import hk.ust.comp3021.rank.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DispatchSystem {

    /// The singleton you will use in the project.
    private static volatile DispatchSystem dispatchSystem;

    /// The field represents the current time stamp, we assume the current time stamp is 3600 seconds.
    private Long currentTimestamp = 3600L;

    /// The list stores the dishes parsed from the file.
    private List<Dish> availableDishes;

    /// The list stores the orders parsed from the file.
    private List<Order> availableOrders;

    /// The list stores the orders that is dispatched this time, and the orders should have a non-null rider field and calculated estimated time.
    private List<Order> dispatchedOrders;

    /// Task 1: Implement the constructor of the singleton pattern for the DispatchSystem class.
    /// Hint: Check if the dispatchSystem is null or not null, skip it when not null. Initialize the fields.

    private DispatchSystem() {
        if (dispatchSystem == null) {
            availableDishes = new ArrayList<Dish>();
            availableOrders = new ArrayList<Order>();
            dispatchedOrders = new ArrayList<Order>();
        }
        else return;
    }

    /// Task 1: Implement the getInstance() method for the singleton pattern.
    /// Hint: Check if the dispatchSystem is null or not null and create a new instance here.
    public static DispatchSystem getInstance() {
        if (dispatchSystem == null) {
            dispatchSystem = new DispatchSystem();
        }
        return dispatchSystem;
    }

    public Dish getDishById(Long id) {
        for (Dish d : availableDishes) {
            if (d.getId().equals(id)) {return d;}
        }
        return null;
    }

    public Boolean checkDishesInRestaurant(Restaurant restaurant, Long[] dishIds) {
        for (int i = 0; i < dishIds.length; i++) {
            boolean found = false;
            for (Dish d : restaurant.getDishes()) {
                if (d.getId().equals(dishIds[i])) {
                    found = true;
                }
            }
            if (!found) {return false;}
        }
        return true;
    }

    /// Task 2: Implement the parseAccounts() method to parse the accounts from the file.
    /// Hint: Do not forget to register the accounts into the static manager.
    public void parseAccounts(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the accounts.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The account file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                String id = fields[0];
                String accountType = fields[1];
                String name = fields[2];
                String contactNumber = fields[3];
                fields[4] = fields[4].replace("[", "").replace("]", "");
                String[] locationSplit = fields[4].split(" ");
                Location location = new Location(Double.parseDouble(locationSplit[0]),
                                                 Double.parseDouble(locationSplit[1]));

                // TODO.

                switch (accountType) {
                    case "CUSTOMER" -> {

                        String customerType = fields[5];
                        String gender = fields[6];
                        String email = fields[7];

                        Customer c = new Customer(
                                Long.parseLong(id),
                                name,
                                contactNumber,
                                location,
                                Integer.parseInt(customerType),
                                gender,
                                email);

                        c.register();
                    }
                    case "RESTAURANT" -> {

                        String district = fields[5];
                        String street = fields[6];

                        Restaurant r = new Restaurant(
                                Long.parseLong(id),
                                name,
                                contactNumber,
                                location,
                                district,
                                street);

                        r.register();
                    }
                    case "RIDER" -> {

                        String gender = fields[5];
                        String status = fields[6];
                        String userRating = fields[7];
                        String monthTaskCount = fields[8];

                        Rider r = new Rider(
                                Long.parseLong(id),
                                name,
                                contactNumber,
                                location,
                                gender,
                                Integer.parseInt(status),
                                Double.parseDouble(userRating),
                                Integer.parseInt(monthTaskCount));

                        r.register();
                    }
                    default -> throw new IOException("Wrong Account Type!");
                }

            }
        }
    }

    /// Task 3: Implement the parseDishes() method to parse the dishes from the file.
    /// Hint: Do not forget to add the dishes to the corresponding restaurant and the availableDishes list.
    public void parseDishes(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the dishes.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The dish file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                // TODO.

                String id = fields[0];
                String name = fields[1];
                String desc = fields[2];
                String price = fields[3];
                String restaurantId = fields[4];

                Dish d = new Dish(
                        Long.parseLong(id),
                        name, desc,
                        new BigDecimal(price),
                        Long.parseLong(restaurantId));

                availableDishes.add(d);
                Restaurant.getRestaurantById(Long.parseLong(restaurantId)).addDish(d);
            }
        }
    }

    /// Task 4: Implement the parseOrders() method to parse the orders from the file.
    /// Hint: Do not forget to add the order to the availableOrders list and check if the dishes ordered are in the same restaurant, skip the record if not. You can use getDishById(), checkDishesInRestaurant(), and etc. here.
    public void parseOrders(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the orders.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The order file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                // TODO.

                String id = fields[0];
                String status = fields[1];
                String restaurantId = fields[2];
                String customerId = fields[3];
                String createTime = fields[4];
                String isPayed = fields[5];
                if (isPayed.equals("1")) {
                    isPayed = "True";
                } else if (isPayed.equals("0")) {
                    isPayed = "False";
                }
                String dishes = fields[6];
                String riderId = fields[7];

                dishes = dishes.replace("[", "").replace("]", "");
                String[] dishesSplit = dishes.split(" ");
                List<Dish> dishesList = new ArrayList<Dish>();
                Long[] dishIds = new Long[dishesSplit.length];
                for (int i = 0; i < dishesSplit.length; i++) {
                    dishIds[i] = Long.parseLong(dishesSplit[i]);
                }

                if (checkDishesInRestaurant(Restaurant.getRestaurantById(Long.parseLong(restaurantId)), dishIds)) {
                    for (Long ids : dishIds) {
                        dishesList.add(getDishById(ids));
                    }

                    Rider rider = null;
                    if (!riderId.equals("NA")) rider = Rider.getRiderById(Long.parseLong(riderId));

                    Order o = new Order(
                            Long.parseLong(id),
                            Integer.parseInt(status),
                            Restaurant.getRestaurantById(Long.parseLong(restaurantId)),
                            Customer.getCustomerById(Long.parseLong(customerId)),
                            Long.parseLong(createTime),
                            Boolean.parseBoolean(isPayed),
                            dishesList,
                            rider
                            );

                    availableOrders.add(o);
                }

            }
        }
    }

    /// Task 5: Implement the getAvailablePendingOrders() method to get the available pending orders.
    /// Hint: The available pending orders should have the status of PENDING_ORDER, is payed, and the rider is null.
    public List<Order> getAvailablePendingOrders() {
        List<Order> result = new ArrayList<Order>();
        for (Order o : availableOrders) {
            if (o.getStatus() == Constants.PENDING_ORDER && o.getIsPayed() && o.getRider() == null) {
                result.add(o);
            }
        }
        return result;
    }

    /// Task 6: Implement the getRankedPendingOrders() method to rank the pending orders.
    /// Hint: Use the comparators you defined before, and sort the pending orders in order of the customer type (Top priority), order creation time (Second priority), and restaurant to customer distance (Least priority).
    public List<Order> getRankedPendingOrders(List<Order> pendingOrders) {

        pendingOrders.sort(
                new CustomerPriorityRank().thenComparing(
                    new OrderCreateTimeRank().thenComparing(
                        new RestaurantToCustomerDistanceRank())));

        return pendingOrders;
    }

    /// Task 7: Implement the getAvailableRiders() method to get the available riders to dispatch.
    /// Hint: The available riders should have the status of RIDER_ONLINE_ORDER.
    public List<Rider> getAvailableRiders() {
        List<Rider> result = new ArrayList<Rider>();
        for (Rider r : Account.accountManager.getRegisteredRiders()) {
            if (r.getStatus().equals(Constants.RIDER_ONLINE_ORDER)) {
                result.add(r);
            }
        }
        return result;
    }

    /// Task 8: Implement the matchTheBestTask() method to choose the best rider for the order.
    /// Hint: The best rider should have the highest rank ranked in order of the distance
    /// between the rider and the restaurant (Top priority), the rider's user rating (Second priority),
    /// and the rider's month task count (Least priority).
    /// Use the comparators you defined before, you will also use the Task class here and
    /// the availableRiders here should be the currently available riders.
    public Task matchTheBestTask(Order order, List<Rider> availableRiders) {
        List<Task> taskList = new ArrayList<Task>();
        for (Rider r : availableRiders) {
            Task t = new Task(order, r);
            taskList.add(t);
        }

        taskList.sort(
                new RiderToRestaurantRank().thenComparing(
                        new RiderRatingRank().thenComparing(
                                new RiderMonthTaskCountRank())));


        return taskList.get(0);
    }

    /// Task 9: Implement the dispatchFirstRound() method to dispatch the first round of orders.
    /// Hint: The strategy is that we assign the best rider to the orders ranked one by one until the orders or riders list is empty.
    /// Do not forget to
    /// 1. remove the dispatched rider every iteration,
    /// 2. change the status of the order and the rider after the order is dispatched, and
    /// 3. calculate the estimated time for the order.
    public void dispatchFirstRound() {
        List<Order> rankedPendingOrders = getRankedPendingOrders(getAvailablePendingOrders());
        List<Rider> availableRiders = getAvailableRiders();
        for (Order o : rankedPendingOrders) {
            if (!availableRiders.isEmpty()) {
                Task t = matchTheBestTask(o, availableRiders);
                availableRiders.remove(t.getRider());
                dispatchedOrders.add(o);
                t.getRider().setStatus(Constants.RIDER_DELIVERING);
                t.getOrder().setStatus(Constants.DISPATCHED_ORDER);
                t.getOrder().setRider(t.getRider());
                t.getOrder().setEstimatedTime(t.getOrder().calculateEstimatedTime());
            } else break;
        }
    }

    /// Do not modify the method. You should use the method to output orders for us to check the correctness of your implementation.
    public void writeOrders(String fileName, List<Order> orders) throws IOException {
        List<Order> orderedOrders = orders.stream().sorted(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orderedOrders) {
                bufferedWriter.write(order.getId() + ", " + order.getStatus() + ", " + order.getRestaurant() + ", "
                        + order.getCustomer() + ", " + order.getCreateTime() + ", " + order.getIsPayed() + ", " +
                        order.getOrderedDishes() + ", " + order.getRider() + ", " + String.format("%.4f", order.getEstimatedTime()) + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeAccounts(String fileName, List<Account> accounts) throws IOException {
        List<Account> orderedAccounts = accounts.stream().sorted(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : orderedAccounts) {
                bufferedWriter.write(account.toString() + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeDishes(String fileName, List<Dish> dishes) throws IOException {
        List<Dish> orderedDishes = dishes.stream().sorted(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Dish dish : orderedDishes) {
                bufferedWriter.write(dish.getId() + ", " + dish.getName() + ", " + dish.getDesc() + ", "
                        + dish.getPrice() + ", " + dish.getRestaurantId() + "\n");
            }
        }
    }

    /// Task 10: Implement the getTimeoutDispatchedOrders() method to get the timeout dispatched orders.
    /// Hint: Do not forget to take the current time stamp into consideration.
    public List<Order> getTimeoutDispatchedOrders() {
        List<Order> timeoutOrdersList = new ArrayList<Order>();
        for (Order o : dispatchedOrders) {
            if (o.getEstimatedTime() + currentTimestamp - o.getCreateTime() > 1800.0) {
                timeoutOrdersList.add(o);
            }
        }
        return timeoutOrdersList;
    }

    /// Do not modify the method.
    public List<Order> getAvailableOrders() {
        return availableOrders;
    }

    /// Do not modify the method.
    public List<Order> getDispatchedOrders() {
        return dispatchedOrders;
    }

    /// Do not modify the method.
    public List<Account> getAccounts() {
        Account.AccountManager manager = Account.getAccountManager();
        return manager.getRegisteredAccounts();
    }

    /// Do not modify the method.
    public List<Dish> getDishes() {
        return availableDishes;
    }

    /// Finish the main method to test your implementation.a
    public static void main(String[] args) {
        try {
            getInstance().parseAccounts("C:\\Users\\yier9\\IdeaProjects\\COMP3021_PA1\\COMP3021-PA1-2024Fall\\SampleInputAccounts.txt");
            getInstance().parseDishes("C:\\Users\\yier9\\IdeaProjects\\COMP3021_PA1\\COMP3021-PA1-2024Fall\\SampleInputDishes.txt");
            getInstance().parseOrders("C:\\Users\\yier9\\IdeaProjects\\COMP3021_PA1\\COMP3021-PA1-2024Fall\\SampleInputOrders.txt");
            getInstance().writeOrders("availableOrders.txt", getInstance().availableOrders);

            getInstance().dispatchFirstRound();

            getInstance().writeOrders("firstRoundDispatchedOrders.txt", getInstance().dispatchedOrders);
            List<Order> timeoutOrders = getInstance().getTimeoutDispatchedOrders();

            getInstance().writeOrders("timeoutDispatchedOrders.txt", timeoutOrders);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
