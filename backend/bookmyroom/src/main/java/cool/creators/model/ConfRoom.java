package cool.creators.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import cool.creators.OfyService;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
@Entity
public class ConfRoom {
    @Id
    private Long roomId;
    @Index
    private String name;
    @Index
    private int capacity;
    @Index
    private int floor;
    @Index
    private boolean isInactive;
    @Index
    private boolean isBooked;
    @Index
    private String facilities;
    @Index
    private boolean delFlag;

    private ConfRoom() {
    }

    public ConfRoom(String name, int capacity, int floor, boolean isInactive, boolean isBooked, String facilities, boolean delFlag) {
        this.roomId = OfyService.factory().allocateId(ConfRoom.class).getId();;
        this.name = name.toLowerCase().trim();
        this.capacity = capacity;
        this.floor = floor;
        this.isInactive = isInactive;
        this.isBooked = isBooked;
        this.facilities = facilities;
        this.delFlag = delFlag;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isInactive() {
        return isInactive;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getFacilities() {
        return facilities;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void update(String name, int capacity, int floor, boolean isInactive, boolean isBooked, String facilities) {
        if (name != null) {
            this.name = name;
        }
        if (capacity != 0) {
            this.capacity = capacity;
        }
        this.floor = floor;
        this.isInactive = isInactive;
        this.isBooked = isBooked;
        if (facilities != null) {
            this.facilities = facilities;
        }
    }

    public void updateStatus(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
