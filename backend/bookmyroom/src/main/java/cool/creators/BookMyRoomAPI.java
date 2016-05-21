package cool.creators;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import cool.creators.data.*;
import cool.creators.model.ConfRoom;
import cool.creators.model.User;

import java.util.ArrayList;
import java.util.List;

@Api(name = "bookMyRoomAPI", version = "v1", description = "Api for the backend of the BookMyRoom app.",
        scopes = { Constants.EMAIL_SCOPE },
        clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE})
public class BookMyRoomAPI {

    @ApiMethod(name = "getCurrentStatusOfAllRooms", path = "showRoomsStatus", httpMethod = ApiMethod.HttpMethod.POST)
    public List<ConfRoom> getCurrentStatusOfAllRooms(User user) throws UnauthorizedException {
        authenticate(new User(user.getEmail(), user.getHashedPassword(), user.getDesignation()));

        return OfyService.ofy().load().type(ConfRoom.class).filter("delFlag", false).list();
    }

    /*@ApiMethod(name = "getMyRooms", path = "bookingHistory", httpMethod = ApiMethod.HttpMethod.POST)
    private List<ConfRoom> getMyRooms(User user) throws UnauthorizedException {
        authenticate(user);

        String key = user.getEmail();
        Key userKey = Key.create(User.class, key);

        return OfyService.ofy().load().type(ConfRoom.class).ancestor(userKey).list();
    }*/

    @ApiMethod(name = "createRoom", path = "createRoom", httpMethod = ApiMethod.HttpMethod.POST)
    public ConfRoom createRoom(ConfRoomData data) throws Exception {
        authenticate(new User(data.getUser().getEmail(), data.getUser().getHashedPassword(), data.getUser().getDesignation()));

        ConfRoom room = OfyService.ofy().load().type(ConfRoom.class).filter("name ==", data.getName().toLowerCase()).first().now();

        if(room == null) {
            room = new ConfRoom(data.getName(), data.getCapacity(), data.getFloor(), false, false, data.getFacilities(), false);
            OfyService.ofy().save().entity(room).now();
        } else {
            throw new Exception("Room with same name already registered.");
        }
        return room;
    }

    @ApiMethod(name = "editRoom", path = "editRoom", httpMethod = ApiMethod.HttpMethod.POST)
    public ConfRoom editRoom(ConfRoomData data) throws Exception {
        authenticate(new User(data.getUser().getEmail(), data.getUser().getHashedPassword(), data.getUser().getDesignation()));

        Key<ConfRoom> key = Key.create(ConfRoom.class, data.getId());
        ConfRoom room = OfyService.ofy().load().key(key).now();

        if(room == null) {
            throw new Exception("No such room.");
        } else {
            ConfRoom existing = OfyService.ofy().load().type(ConfRoom.class).filter("name ==", data.getName().toLowerCase().trim()).filter("delFlag", false).first().now();
            if (existing == null || existing.getName().equals(room.getName())) {
                room.update(data.getName(), data.getCapacity(), data.getFloor(), data.isInactive(), data.isBooked(), data.getFacilities());
                OfyService.ofy().save().entity(room).now();
            } else {
                throw new Exception("Room with same room already exists!");
            }
        }
        return room;
    }

    @ApiMethod(name = "removeRoom", path = "removeRoom", httpMethod = ApiMethod.HttpMethod.POST)
    public ConfRoom removeRoom(ConfRoomRemoveData data) throws Exception {
        authenticate(new User(data.getUser().getEmail(), data.getUser().getHashedPassword(), data.getUser().getDesignation()));

        Key<ConfRoom> key = Key.create(ConfRoom.class, data.getId());
        ConfRoom room = OfyService.ofy().load().key(key).now();

        if(room == null) {
            throw new Exception("No such room.");
        } else {
            room.setDelFlag(true);
            OfyService.ofy().save().entity(room).now();
        }
        return room;
    }

    @ApiMethod(name = "login", path = "login", httpMethod = ApiMethod.HttpMethod.POST)
    public User login(UserLoginData data) throws UnauthorizedException {
        User user = new User(data.getEmail(), data.getPassword(), null);
        if(!user.checkIfAuthenticated()) {
            throw new UnauthorizedException("Please enter the correct email and password!");
        }
        return user;
    }

    @ApiMethod(name = "signup", path = "signup", httpMethod = ApiMethod.HttpMethod.POST)
    public User signup(UserData data) throws Exception {
        Key<User> key = Key.create(User.class, data.getEmail());
        User user = OfyService.ofy().load().key(key).now();
        if (user == null) {
            user = new User(data.getEmail(), data.getPassword(), data.getDesignation());
            OfyService.ofy().save().entity(user).now();
        } else {
            throw new Exception("You have an account already!");
        }
        return user;
    }

    @ApiMethod(name = "updateRoomStatus", path = "updateRoomStatus", httpMethod = ApiMethod.HttpMethod.POST)
    public ConfRoom updateRoomStatus(ConfRoomUpdateStatusData data) throws Exception {
        authenticate(new User(data.getUser().getEmail(), data.getUser().getHashedPassword(), data.getUser().getDesignation()));

        return updateRoomStatusInDB(data);
    }

    @ApiMethod(name = "bookRoom", path = "bookRoom", httpMethod = ApiMethod.HttpMethod.POST)
    public ConfRoomsWrapper bookRoom(BookingCriteria criteria) throws UnauthorizedException {
        authenticate(new User(criteria.getUser().getEmail(), criteria.getUser().getHashedPassword(), criteria.getUser().getDesignation()));

        List<ConfRoom> availableRooms = OfyService.ofy().load().type(ConfRoom.class).filter("isBooked", false)
                .filter("capacity >=", criteria.getCapacity()).list();

        String[] facilityList = criteria.getFacilities().split(Constants.COMMA_SEPERATOR);
        List<ConfRoom> qualifiedRooms = new ArrayList<>();

        for (ConfRoom room : availableRooms) {
            boolean qualified = true;
            for (String facility : facilityList) {
                if(!room.getFacilities().contains(facility)) {
                    qualified = false;
                }
            }
            if(qualified) {
                qualifiedRooms.add(room);
            }
        }

        if (qualifiedRooms.size() == 0) {
            //Get all rooms (booked rooms)
            //Calculate priority scores
            //find the current booking priority
            //return the least priority one than the current booking priority
        }

        return null;
    }

    /**
     * This method needs to be called on booking a room successfully and
     * when the booking time expires
     * @param data The status of the current room thats passed in a wrapper
     * @return ConfRoom that is updated
     * @throws Exception
     */
    private ConfRoom updateRoomStatusInDB(ConfRoomUpdateStatusData data) throws Exception {
        Key<ConfRoom> key = Key.create(ConfRoom.class, data.getId());
        ConfRoom room = OfyService.ofy().load().key(key).now();

        if(room == null) {
            throw new Exception("No such room.");
        } else {
            room.updateStatus(data.isBooked());
            OfyService.ofy().save().entity(room).now();
        }
        return room;
    }

    /**
     * Checks if user is authenticated
     * @param user requesting user
     * @return True if authorized, False if not.
     * @throws UnauthorizedException
     */
    private void authenticate(User user) throws UnauthorizedException {
        if(!user.checkIfAuthenticated()) {
            throw new UnauthorizedException("You should be a registered user.");
        }
    }
}
