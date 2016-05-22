package cool.creators.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import cool.creators.OfyService;

import java.util.Date;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
@Entity
public class Booking {
    @Id
    private Long bookingId;
    private String type;
    private Date startTime;
    private Date endTime;
    @Index
    private boolean isExpired;

    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<ConfRoom> roomKey;

    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<User> userKey;

    private Booking() {}

    public Booking(String type, Date startTime, Date endTime, Key<ConfRoom> roomKey, Key<User> userKey) {
        this.bookingId = OfyService.factory().allocateId(Booking.class).getId();
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomKey = roomKey;
        this.userKey = userKey;
        this.isExpired = false;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getType() {
        return type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Key<ConfRoom> getRoomKey() {
        return roomKey;
    }

    public Key<User> getUserKey() {
        return userKey;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
