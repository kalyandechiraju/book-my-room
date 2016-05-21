package cool.creators.data;

import cool.creators.model.User;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class ConfRoomRemoveData {
    private Long id;
    private User user;

    private ConfRoomRemoveData() {
    }

    public ConfRoomRemoveData(Long id, User user) {
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
