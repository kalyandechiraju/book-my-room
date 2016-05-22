package cool.creators;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import cool.creators.model.Booking;
import cool.creators.model.ConfRoom;
import cool.creators.model.User;


public class OfyService {
	/**
	 * This static block ensure the entity registration.
	 */
	static {
		factory().register(ConfRoom.class);
		factory().register(User.class);
		factory().register(Booking.class);
	}

	/**
	 * Use this static method for getting the Objectify service object in order to make sure the
	 * above static block is executed before using Objectify.
	 * @return Objectify service object.
	 */
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	/**
	 * Use this static method for getting the Objectify service factory.
	 * @return ObjectifyFactory.
	 */
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
