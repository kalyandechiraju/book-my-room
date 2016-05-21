package cool.creators.data;

import cool.creators.model.User;

import java.util.Date;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class BookingCriteria {
    private int capacity;
    private String facilities;
    private Date startTime;
    private Date endTime;
    private User user;

    private BookingCriteria() { }

    public BookingCriteria(int capacity, String facilities, Date startTime, Date endTime, User user) {
        this.capacity = capacity;
        this.facilities = facilities;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFacilities() {
        return facilities;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public User getUser() {
        return user;
    }
}
