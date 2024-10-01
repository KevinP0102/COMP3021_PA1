package hk.ust.comp3021;

public class Rider extends Account {

    private String gender;
    private Integer status;
    private Double userRating;
    private Integer monthTaskCount;

    public Rider(Long id,
                 String name,
                 String contactNumber,
                 Location location,
                 String gender,
                 Integer status,
                 Double userRating,
                 Integer monthTaskCount) {

        this.id = id;
        this.accountType = Constants.ACCOUNT_RIDER;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
        this.gender = gender;
        this.status = status;
        this.userRating = userRating;
        this.monthTaskCount = monthTaskCount;
    }

    public void register() {
        Account.accountManager.addAccount(this);
        Account.accountManager.addRider(this);
    }

    public static Rider getRiderById(Long id) {return Account.accountManager.getRiderById(id);}

    /// Do not modify this method.
    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                ", userRating=" + userRating +
                ", monthTaskCount=" + monthTaskCount +
                '}';
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public Integer getMonthTaskCount() {
        return monthTaskCount;
    }

    public void setMonthTaskCount(Integer monthTaskCount) {
        this.monthTaskCount = monthTaskCount;
    }
}
