package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.util.exception.OnlyOneVoteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant, int userId);

    Restaurant update(Restaurant restaurant, int userId);

    //return an id of unvoted restaurant if exist
    Integer vote(int id, int userId, LocalDateTime localDateTime) throws OnlyOneVoteException;

    void delete(int id, int userId);

    void deleteAll(int userId);

    Restaurant get(int id, int userId);

    Collection<Restaurant> getAll(int userId);

    Collection<Restaurant> getAllOfToday(LocalDate date);

}
