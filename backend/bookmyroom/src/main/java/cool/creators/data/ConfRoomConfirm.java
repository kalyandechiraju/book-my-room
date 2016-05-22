package cool.creators.data;

import cool.creators.model.User;

import java.util.Date;

/**
 * Created by kalyandechiraju on 22/05/16.
 */
public class ConfRoomConfirm {
    private Long id;
    private User user;
    private Date startTime;
    private Date endTime;
    private String type;

    private ConfRoomConfirm() {}

    public ConfRoomConfirm(Long id, User user, Date startTime, Date endTime, String type) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }
}
