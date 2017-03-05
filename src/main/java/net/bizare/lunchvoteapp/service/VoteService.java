package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote save(Vote vote);

    List<Vote> getAll();

    Integer getCount(int restaurantId, LocalDate voteDate);

    Vote getVoteOfUser(int userId, LocalDate voteDate);

    void update(Vote vote);
}
