package net.bizare.lunchvoteapp.repository;

import net.bizare.lunchvoteapp.model.Vote;

import java.time.LocalDate;
import java.util.Collection;

public interface VoteRepository {

    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    Collection<Vote> getAll();

    Integer getCount(int restaurantId, LocalDate voteDate);

    Vote getVoteOfUser(int userId, LocalDate voteDate);
}
