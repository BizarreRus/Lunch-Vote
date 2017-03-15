package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.model.Vote;
import net.bizare.lunchvoteapp.repository.VoteRepository;
import net.bizare.lunchvoteapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository voteRepository;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        return voteRepository.save(vote);
    }

    @Override
    public Vote getVoteOfUser(int userId, LocalDate voteDate) {
        return voteRepository.getVoteOfUser(userId, voteDate);
    }

    @Override
    @Transactional
    public void update(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        voteRepository.save(vote);
    }
}
