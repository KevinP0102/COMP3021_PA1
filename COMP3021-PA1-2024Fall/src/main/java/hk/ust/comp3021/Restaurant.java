package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurant extends Account {

    private String district;
    private String street;
    private List<Dish> dishes;

    public Restaurant(Long id,
                      String name,
                      String contactNumber,
                      Location location,
                      String district,
                      String street) {

        this.id = id;
        this.name = name;
        this.accountType = Constants.ACCOUNT_RESTAURANT;
        this.contactNumber = contactNumber;
        this.location = location;
        this.district = district;
        this.street = street;
        this.dishes = new ArrayList<Dish>();
    }

    public void register() {
        Account.accountManager.addAccount(this);
        Account.accountManager.addRestaurant(this);
    }

    public void addDish(Dish dish) {
        this.dishes.add(dish);
    }

    public static Restaurant getRestaurantById(Long id) {return Account.accountManager.getRestaurantById(id);}

    /// Do not modify this method.
    @Override
    public String toString() {
        List<Long> dishIds = new LinkedList<>(dishes.stream().map(Dish::getId).toList());
        dishIds.sort(Long::compareTo);
        return "Restaurant{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", dishIds='" + dishIds + '\'' +
                '}';
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

}
