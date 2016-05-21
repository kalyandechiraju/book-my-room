package cool.creators.data;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class UserData {
    private String email;
    private String password;
    private String designation;

    private UserData() {}

    public UserData(String email, String password, String designation) {
        this.email = email;
        this.password = password;
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDesignation() {
        return designation;
    }
}
