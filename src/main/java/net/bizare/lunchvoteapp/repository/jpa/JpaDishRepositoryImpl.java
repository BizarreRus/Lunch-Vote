package net.bizare.lunchvoteapp.repository.jpa;

import net.bizare.lunchvoteapp.model.Dish;
import net.bizare.lunchvoteapp.repository.DishRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
                return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int menuId) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .setParameter("menu_id", menuId)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public boolean deleteAll(int menuId) {
        return em.createNamedQuery(Dish.DELETE_ALL)
                .setParameter("menu_id", menuId)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id, int menuId) {
        return DataAccessUtils.singleResult(
                em.createNamedQuery(Dish.GET, Dish.class)
                        .setParameter("id", id)
                        .setParameter("menu_id", menuId)
                        .getResultList()
        );
    }

    @Override
    public Collection<Dish> getAll(int menuId) {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).setParameter("menu_id", menuId).getResultList();
    }
}
