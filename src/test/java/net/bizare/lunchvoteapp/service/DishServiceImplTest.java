package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Dish;
import net.bizare.lunchvoteapp.util.exception.NotEnoughRigthsException;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static net.bizare.lunchvoteapp.DishTestData.*;
import static net.bizare.lunchvoteapp.MenuTestData.MENU_ID1;
import static net.bizare.lunchvoteapp.RestaurantTestData.RESTAURANT1_ID;
import static net.bizare.lunchvoteapp.MenuTestData.NONEXISTED_MENU_ID;
import static net.bizare.lunchvoteapp.UserTestData.ADMIN_ID;
import static net.bizare.lunchvoteapp.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(MenuServiceImplTest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Rule
    public TestName name = new TestName();
    @Rule
    public TestWatcher watcher = new TestWatcher() {
        long startTime;

        @Override
        protected void starting(Description description) {
            startTime = System.currentTimeMillis();
        }

        @Override
        protected void finished(Description description) {
            long result = System.currentTimeMillis() - startTime;
            LOG.info("Executed test: " + name.getMethodName() + ". In  " + result + " ms.");
        }
    };

    @Autowired
    private DishService dishService;

    @Test
    public void testSave() throws Exception {
        Dish created = getCreated();
        dishService.save(created, RESTAURANT1_ID, MENU_ID1, ADMIN_ID);
        MATCHER.assertCollectionEquals(getSortedDishes(Arrays.asList(created, DISH1, DISH2, DISH3)),
                dishService.getAll(MENU_ID1, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testSaveNotEnoughRights() throws Exception {
        Dish created = getCreated();
        dishService.save(created, RESTAURANT1_ID, MENU_ID1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveNotFound() throws Exception {
        Dish created = getCreated();
        dishService.save(created, RESTAURANT1_ID, NONEXISTED_MENU_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        dishService.update(updated, RESTAURANT1_ID, MENU_ID1, ADMIN_ID);
        MATCHER.assertEquals(updated, dishService.get(DISH1_ID, MENU_ID1, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testUpdateNotEnoughRights() throws Exception {
        Dish updated = getUpdated();
        dishService.update(updated, RESTAURANT1_ID, MENU_ID1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFount() throws Exception {
        Dish updated = getUpdated();
        updated.setId(NONEXISTED_DISH_ID);
        dishService.update(updated, RESTAURANT1_ID, MENU_ID1, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        dishService.delete(DISH1_ID, MENU_ID1, ADMIN_ID);
        MATCHER.assertCollectionEquals(getSortedDishes(Arrays.asList(DISH2, DISH3)),
                dishService.getAll(MENU_ID1, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testDeleteNotEnoughRights() throws Exception {
        dishService.delete(DISH1_ID, MENU_ID1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFount() throws Exception {
        dishService.delete(NONEXISTED_MENU_ID, MENU_ID1, ADMIN_ID);
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testDeleteAllNotEnoughRights() throws Exception {
        dishService.deleteAll(MENU_ID1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteAllNotFount() throws Exception {
        dishService.deleteAll(NONEXISTED_MENU_ID, ADMIN_ID);
    }

    @Test
    public void testGet() throws Exception {
        Dish actual = dishService.get(DISH1_ID, MENU_ID1, ADMIN_ID);
        MATCHER.assertEquals(actual, DISH1);
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testGetNotEnoughRights() throws Exception {
        dishService.get(DISH1_ID, MENU_ID1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFount() throws Exception {
        dishService.get(NONEXISTED_DISH_ID, MENU_ID1, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> dishes = dishService.getAll(MENU_ID1, ADMIN_ID);
        MATCHER.assertCollectionEquals(dishes, getSortedDishes(Arrays.asList(DISH1, DISH2, DISH3)));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testGetAllNotEnoughRights() throws Exception {
        dishService.getAll(MENU_ID1, USER_ID);
    }

    @Test
    public void testThrowExpectedExceptionException() throws Exception {
        thrown.expect(Exception.class);
        throw new Exception();
    }
}