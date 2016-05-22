package cool.creators.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import cool.creators.OfyService;
import cool.creators.util.PasswordUtil;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
@Entity
public class User {
    @Id
    private String email;
    private String hashedPassword;
    private String designation;

    private User() {}

    public User(String email, String password, String designation) {
        this.email = email;
        this.hashedPassword = PasswordUtil.getHashedPassword(password);
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getDesignation() {
        return designation;
    }

    public boolean checkIfAuthenticated() {
        Key<User> key = Key.create(User.class, this.email);
        User savedUser = OfyService.ofy().load().key(key).now();
        return savedUser != null && this.isSameAs(savedUser);
    }

    private boolean isSameAs(User savedUser) {
        return this.email.equals(savedUser.getEmail()) && savedUser.getHashedPassword().contains(this.hashedPassword);
    }

    public int getUserScore() {
        switch (designation) {
            case "CEO":
                return 4;
            case "PM":
                return 3;
            case "TL":
                return 2;
            case "E":
                return 1;
            default:
                return 0;
        }
    }
}
