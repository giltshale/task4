public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean realEstateAgent;
    private int userAddAmount;

public User(){
    this.realEstateAgent=true;
}
    public void setUserAddAmount(int userAddAmount) {
        this.userAddAmount = userAddAmount;
    }

    public int getUserAddAmount() {
        return userAddAmount;
    }

    public boolean isRealEstateAgent() {
        return realEstateAgent;
    }

    public User(User u) {
        userName = u.userName;
        password = u.password;
        phoneNumber = u.phoneNumber;
        realEstateAgent = u.realEstateAgent;
    }

    public User(String userName, String password, String phoneNumber, boolean realEstateAgent) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.realEstateAgent = realEstateAgent;
    }

    public String getUserName() {
        if (userName != null) {

            return this.userName;
        }
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean isRegularUser(boolean regular) {
        return this.realEstateAgent = regular;
    }

    public String toString() {
        return  this.userName +" "+
                 this.phoneNumber;
    }
}
