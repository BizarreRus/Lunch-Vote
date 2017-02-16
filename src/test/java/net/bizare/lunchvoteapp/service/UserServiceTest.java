package net.bizare.lunchvoteapp.service;

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
import net.bizare.lunchvoteapp.model.Role;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static net.bizare.lunchvoteapp.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

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
    private UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "New", "newPass", "new@gmail.com", Role.ROLE_USER);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER),  service.getAll());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(100);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(100);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        service.update(updated);
        User actual = service.get(USER_ID);
        MATCHER.assertEquals(updated, actual);
    }
}