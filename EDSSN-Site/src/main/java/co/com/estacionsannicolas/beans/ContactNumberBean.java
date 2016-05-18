package co.com.estacionsannicolas.beans;

public class ContactNumberBean extends BaseBean {

    private UserBean user;
    private String phoneNumber;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
