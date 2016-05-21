package cool.creators.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.Date;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
@Entity
public class Booking {
    @Id
    private String bookingId;
    private String type;
    private Date fromDate;
    private Date toDate;

    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<ConfRoom> roomKey;

    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<User> userKey;

    private Booking() {}

    public Booking(String bookingId, String type, Date fromDate, Date toDate, Key<ConfRoom> roomKey, Key<User> userKey) {
        this.bookingId = bookingId;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.roomKey = roomKey;
        this.userKey = userKey;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getType() {
        return type;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Key<ConfRoom> getRoomKey() {
        return roomKey;
    }

    public Key<User> getUserKey() {
        return userKey;
    }
}
