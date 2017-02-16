package net.bizare.lunchvoteapp.util;

import net.bizare.lunchvoteapp.model.Vote;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import net.bizare.lunchvoteapp.util.exception.OnlyOneVoteException;
import net.bizare.lunchvoteapp.util.exception.PermissibleTimeException;

import java.time.LocalTime;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg + ".");
        }
    }

    public static void checkAvailableVote(Vote vote, Integer restaurantId) {
        Integer voted = vote.getRestaurant().getId();
        if (voted != null && voted.equals(restaurantId)) {
            throw new OnlyOneVoteException("You can vote only one time per day.");
        }
    }

    public static void checkPermissibleTime(LocalTime time) {
        if (time.isAfter(LocalTime.of(11, 0))) {
            throw new PermissibleTimeException("You can vote only before 11:00 AM.");
        }
    }
}
