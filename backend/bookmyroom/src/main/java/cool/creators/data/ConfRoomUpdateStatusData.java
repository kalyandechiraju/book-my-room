package cool.creators.data;

import cool.creators.model.User;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class ConfRoomUpdateStatusData {
    private Long id;
    private boolean isBooked;
    private User user;

    private ConfRoomUpdateStatusData() {
    }

    public ConfRoomUpdateStatusData(Long id, boolean isBooked, User user) {
        this.id = id;
        this.isBooked = isBooked;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public User getUser() {
        return user;
    }
}
