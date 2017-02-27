package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.ActiveDbProfileResolver;
import net.bizare.lunchvoteapp.model.Dish;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static net.bizare.lunchvoteapp.DishTestData.*;
import static net.bizare.lunchvoteapp.MenuTestData.MENU_ID1;
import static net.bizare.lunchvoteapp.RestaurantTestData.RESTAURANT1_ID;
import static net.bizare.lunchvoteapp.MenuTestData.NON_EXISTED_MENU_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populate.sql", config = @SqlConfig(encoding = "UTF-8"))
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
        dishService.save(created, RESTAURANT1_ID, MENU_ID1);
        MATCHER.assertCollectionEquals(getSortedDishes(Arrays.asList(created, DISH1, DISH2, DISH3)),
                dishService.getAll(MENU_ID1));
    }

    @Test(expected = NotFoundException.class)
    public void testSaveNotFound() throws Exception {
        Dish created = getCreated();
        dishService.save(created, RESTAURANT1_ID, NON_EXISTED_MENU_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        dishService.update(updated, RESTAURANT1_ID, MENU_ID1);
        MATCHER.assertEquals(updated, dishService.get(DISH1_ID, MENU_ID1));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFount() throws Exception {
        Dish updated = getUpdated();
        updated.setId(NON_EXISTED_DISH_ID);
        dishService.update(updated, RESTAURANT1_ID, MENU_ID1);
    }

    @Test
    public void testDelete() throws Exception {
        dishService.delete(DISH1_ID, MENU_ID1);
        MATCHER.assertCollectionEquals(getSortedDishes(Arrays.asList(DISH2, DISH3)),
                dishService.getAll(MENU_ID1));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFount() throws Exception {
        dishService.delete(NON_EXISTED_MENU_ID, MENU_ID1);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteAllNotFount() throws Exception {
        dishService.deleteAll(NON_EXISTED_MENU_ID);
    }

    @Test
    public void testGet() throws Exception {
        Dish actual = dishService.get(DISH1_ID, MENU_ID1);
        MATCHER.assertEquals(actual, DISH1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFount() throws Exception {
        dishService.get(NON_EXISTED_DISH_ID, MENU_ID1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> dishes = dishService.getAll(MENU_ID1);
        MATCHER.assertCollectionEquals(dishes, getSortedDishes(Arrays.asList(DISH1, DISH2, DISH3)));
    }

    @Test
    public void testThrowExpectedExceptionException() throws Exception {
        thrown.expect(Exception.class);
        throw new Exception();
    }
}