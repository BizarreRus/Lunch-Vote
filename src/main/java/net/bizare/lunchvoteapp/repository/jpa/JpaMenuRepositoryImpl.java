package net.bizare.lunchvoteapp.repository.jpa;

import net.bizare.lunchvoteapp.model.Menu;
import net.bizare.lunchvoteapp.repository.MenuRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepositoryImpl implements MenuRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("restaurant_id", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public boolean deleteAll(int restaurantId) {
        return em.createNamedQuery(Menu.DELETE_ALL)
                .setParameter("restaurant_id", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return DataAccessUtils.singleResult(
                em.createNamedQuery(Menu.GET, Menu.class)
                        .setParameter("id", id)
                        .setParameter("restaurant_id", restaurantId)
                        .getResultList()
        );
    }

    @Override
    public Collection<Menu> getAll(int restaurantId) {
        return em.createNamedQuery(Menu.GET_ALL, Menu.class).setParameter("restaurant_id", restaurantId).getResultList();
    }
}
