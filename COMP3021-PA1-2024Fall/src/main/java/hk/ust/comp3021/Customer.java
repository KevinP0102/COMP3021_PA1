package hk.ust.comp3021;


public class Customer extends Account {

    private Integer customerType;
    private String gender;
    private String email;

    public Customer(Long id,
                    String name,
                    String contactNumber,
                    Location location,
                    Integer customerType,
                    String gender,
                    String email) {

        this.id = id;
        this.accountType = Constants.ACCOUNT_CUSTOMER;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
        this.customerType = customerType;
        this.gender = gender;
        this.email = email;
    }

    /// Do not modify this method.
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", customerType=" + customerType +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void register() {
        Account.accountManager.addAccount(this);
        Account.accountManager.addCustomer(this);
    }

    public static Customer getCustomerById(Long id) {
        return Account.accountManager.getCustomerById(id);
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
