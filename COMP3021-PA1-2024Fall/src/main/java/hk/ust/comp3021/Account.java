package hk.ust.comp3021;

import java.util.*;

public abstract class Account {

    protected Long id;

    protected String accountType;

    protected String name;

    protected String contactNumber;

    protected Location location;

    /// This is where the registered accounts are stored.
    protected static class AccountManager {

        private List<Account> registeredAccounts;

        private List<Customer> registeredCustomers;

        private List<Restaurant> registeredRestaurants;

        private List<Rider> registeredRiders;

        public AccountManager() {
            registeredAccounts = new ArrayList<Account>();
            registeredCustomers = new ArrayList<Customer>();
            registeredRestaurants = new ArrayList<Restaurant>();
            registeredRiders = new ArrayList<Rider>();
        }

        /// Do not modify this method.
        public List<Account> getRegisteredAccounts() {
            return registeredAccounts;
        }

        public List<Rider> getRegisteredRiders() { return registeredRiders; }

        public Account getAccountById(Long id) {
            for (Account account : registeredAccounts) {
                if (account.getId().equals(id)) {return account; }
            }
            return null;
        }

        public void addAccount(Account account) {
            registeredAccounts.add(account);
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addCustomer(Customer customer) {
            registeredCustomers.add(customer);
        }

        public Customer getCustomerById(Long id) {
            for (Customer customer : registeredCustomers) {
                if (customer.getId().equals(id)) {return customer; }
            }
            return null;
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRestaurant(Restaurant restaurant) {
            registeredRestaurants.add(restaurant);
        }

        public Restaurant getRestaurantById(Long id) {
            for (Restaurant restaurant : registeredRestaurants) {
                if (restaurant.getId().equals(id)) {return restaurant; }
            }
            return null;
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRider(Rider rider) {
            registeredRiders.add(rider);
        }

        public Rider getRiderById(Long id) {
            for (Rider rider : registeredRiders) {
                if (rider.getId().equals(id)) {return rider; }
            }
            return null;
        }

    }

    protected static AccountManager accountManager = new AccountManager();

    /// Task 2: Implement the register method.
    public abstract void register();

    public static Account getAccountById(Long id) {
        return accountManager.getAccountById(id);
    }

    /// Do not modify this method.
    public static AccountManager getAccountManager() {
        return accountManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
