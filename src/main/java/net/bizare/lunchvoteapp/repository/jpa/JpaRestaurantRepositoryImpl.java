package net.bizare.lunchvoteapp.repository.jpa;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.repository.RestaurantRepository;
import net.bizare.lunchvoteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;

        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        return em.createNamedQuery(Restaurant.DELETE_ALL).executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return DataAccessUtils.singleResult(em.createNamedQuery(Restaurant.GET, Restaurant.class).setParameter("id", id).getResultList());
    }

    @Override
    public Collection<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }

    @Override
    public Collection<Restaurant> getAll(LocalDate date) {
        return em.createNamedQuery(Restaurant.GET_ALL_BY_DATE, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }
}
