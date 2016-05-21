package cool.creators.data;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class UserLoginData {
    private String email;
    private String password;

    private UserLoginData() {}

    public UserLoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
