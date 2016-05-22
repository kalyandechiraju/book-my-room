package cool.creators.data;

import cool.creators.model.User;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class ConfRoomData {
    private Long id;
    private String name;
    private int capacity;
    private int floor;
    private String facilities;
    private boolean isInactive;
    private User user;

    private ConfRoomData() { }

    public ConfRoomData(Long id, String name, int capacity, int floor, String facilities, boolean isInactive, User user) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.floor = floor;
        this.facilities = facilities;
        this.isInactive = isInactive;
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public String getFacilities() {
        return facilities;
    }

    public boolean isInactive() {
        return isInactive;
    }

    public User getUser() {
        return user;
    }
}
