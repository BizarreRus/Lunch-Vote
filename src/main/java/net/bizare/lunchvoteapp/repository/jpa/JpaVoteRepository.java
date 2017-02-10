package net.bizare.lunchvoteapp.repository.jpa;

import net.bizare.lunchvoteapp.model.Vote;
import net.bizare.lunchvoteapp.repository.VoteRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;

        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public Collection<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).getResultList();
    }

    @Override
    public Integer getCount(int restaurantId, LocalDate voteDate) {
        return em.createNamedQuery(Vote.GET_COUNT)
                .setParameter("restaurantId", restaurantId)
                .setParameter("voteDate", voteDate)
                .executeUpdate();
    }

    @Override
    public Vote getVoteOfUser(int userId, LocalDate voteDate) {
        return DataAccessUtils.singleResult(em.createNamedQuery(Vote.GET_VOTE_OF_USER, Vote.class)
                .setParameter("userId", userId)
                .setParameter("voteDate", voteDate)
                .getResultList());
    }
}
