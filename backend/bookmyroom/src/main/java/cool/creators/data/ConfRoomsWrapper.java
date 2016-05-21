package cool.creators.data;

import cool.creators.model.ConfRoom;

import java.util.List;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class ConfRoomsWrapper {
    private List<ConfRoom> confRooms;
    private boolean confirm;
    private String status;

    private ConfRoomsWrapper() { }

    public ConfRoomsWrapper(List<ConfRoom> confRooms, boolean confirm, String status) {
        this.confRooms = confRooms;
        this.confirm = confirm;
        this.status = status;
    }

    public List<ConfRoom> getConfRooms() {
        return confRooms;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public String getStatus() {
        return status;
    }
}
