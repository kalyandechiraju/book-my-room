package cool.creators.data;

import cool.creators.model.User;

/**
 * Created by kalyandechiraju on 22/05/16.
 */
public class BookingData {
    private Long id;
    private User user;

    private BookingData() {}

    public BookingData(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
