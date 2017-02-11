package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.model.Vote;
import net.bizare.lunchvoteapp.repository.RestaurantRepository;
import net.bizare.lunchvoteapp.repository.UserRepository;
import net.bizare.lunchvoteapp.service.RestaurantService;
import net.bizare.lunchvoteapp.service.VoteService;
import net.bizare.lunchvoteapp.util.exception.OnlyOneVoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

import static net.bizare.lunchvoteapp.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private VoteService voteService;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkRights(checkNotFoundWithId(userRepository.get(userId), userId));

        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public Restaurant update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkRights(userRepository.get(userId));

        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public Integer vote(int id, int userId, LocalDateTime today) {
        Integer unvotedId = null;
        checkPermissionedTime(LocalTime.from(today));

        Vote vote = voteService.getVoteOfUser(userId, LocalDate.from(today));

        if (vote != null){
            checkAvailableVote(vote, id);

            Restaurant unvoted = restaurantRepository.get(vote.getRestaurant().getId());
            unvoted.setNumOfVotes(unvoted.getNumOfVotes() - 1);
            restaurantRepository.save(unvoted);
            unvotedId = unvoted.getId();
        }
        Restaurant voted = restaurantRepository.get(id);
        voted.setNumOfVotes(voted.getNumOfVotes() + 1);
        restaurantRepository.save(voted);

        Vote newVote = new Vote(vote == null ? null : vote.getId(), voted, userRepository.get(userId), LocalDate.from(today));
        if (newVote.isNew()){
            voteService.save(newVote);
        } else {
            voteService.update(newVote);
        }
        return unvotedId;
    }

    @Override
    public void delete(int id, int userId) {
        checkRights(userRepository.get(userId));
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public void deleteAll(int userId) {
        checkRights(userRepository.get(userId));
        restaurantRepository.deleteAll();
    }

    @Override
    public Restaurant get(int id, int userId) {
        checkRights(userRepository.get(userId));
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Override
    public Collection<Restaurant> getAll(int userId) {
        checkRights(userRepository.get(userId));
        return checkNotFound(restaurantRepository.getAll(), "No entries");
    }

    @Override
    public Collection<Restaurant> getAllOfToday(LocalDate date) {
        return restaurantRepository.getAll(date);
    }
}
