package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Menu;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static net.bizare.lunchvoteapp.MenuTestData.*;
import static net.bizare.lunchvoteapp.RestaurantTestData.RESTAURANT1_ID;
import static net.bizare.lunchvoteapp.RestaurantTestData.NONEXISTED_RESTAURANT_ID;
import static net.bizare.lunchvoteapp.UserTestData.ADMIN_ID;
import static net.bizare.lunchvoteapp.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceImplTest {
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
    private MenuService menuService;

    @Test
    public void testSave() throws Exception {
        Menu created = getCreated();
        menuService.save(created, RESTAURANT1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(getSortedMenus(Arrays.asList(created, MENU1, MENU2)),
                menuService.getAll(RESTAURANT1_ID, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testSaveNotEnoughRights() throws Exception {
        Menu created = getCreated();
        menuService.save(created, RESTAURANT1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveNotFound() throws Exception {
        Menu created = getCreated();
        menuService.save(created, NONEXISTED_RESTAURANT_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = getUpdated();
        menuService.update(updated, RESTAURANT1_ID, ADMIN_ID);
        MATCHER.assertEquals(updated, menuService.get(MENU_ID1, RESTAURANT1_ID, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testUpdateNotEnoughRights() throws Exception {
        Menu updated = getUpdated();
        menuService.update(updated, RESTAURANT1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFount() throws Exception {
        Menu updated = getUpdated();
        updated.setId(NONEXISTED_MENU_ID);
        menuService.update(updated, RESTAURANT1_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        menuService.delete(MENU_ID1, RESTAURANT1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(getSortedMenus(getSortedMenus(Collections.singletonList(MENU2))),
                menuService.getAll(RESTAURANT1_ID, ADMIN_ID));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testDeleteNotEnoughRights() throws Exception {
        menuService.delete(MENU_ID1, RESTAURANT1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFount() throws Exception {
        menuService.delete(NONEXISTED_MENU_ID, RESTAURANT1_ID, ADMIN_ID);
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testDeleteAllNotEnoughRights() throws Exception {
        menuService.deleteAll(RESTAURANT1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteAllNotFount() throws Exception {
        menuService.deleteAll(NONEXISTED_RESTAURANT_ID, ADMIN_ID);
    }

    @Test
    public void testGet() throws Exception {
        Menu actual = menuService.get(MENU_ID1, RESTAURANT1_ID, ADMIN_ID);
        MATCHER.assertEquals(actual, MENU1);
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testGetNotEnoughRights() throws Exception {
        menuService.get(MENU_ID1, RESTAURANT1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFount() throws Exception {
        menuService.get(NONEXISTED_MENU_ID, RESTAURANT1_ID, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Menu> menus = menuService.getAll(RESTAURANT1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(menus, getSortedMenus(getSortedMenus(Arrays.asList(MENU1, MENU2))));
    }

    @Test(expected = NotEnoughRigthsException.class)
    public void testGetAllNotEnoughRights() throws Exception {
        menuService.getAll(RESTAURANT1_ID, USER_ID);
    }

    @Test
    public void testThrowExpectedExceptionException() throws Exception {
        thrown.expect(Exception.class);
        throw new Exception();
    }
}