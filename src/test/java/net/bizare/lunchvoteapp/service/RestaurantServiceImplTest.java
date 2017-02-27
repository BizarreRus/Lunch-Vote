package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.ActiveDbProfileResolver;
import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import net.bizare.lunchvoteapp.util.exception.OnlyOneVoteException;
import net.bizare.lunchvoteapp.util.exception.PermissibleTimeException;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static net.bizare.lunchvoteapp.RestaurantTestData.*;
import static net.bizare.lunchvoteapp.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populate.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImplTest.class);

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
    private RestaurantService restaurantService;

    @Test
    public void testSave() throws Exception {
        Restaurant created = getCreated();
        restaurantService.save(created);
        MATCHER.assertCollectionEquals(getSortedRestaurants(Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT22, RESTAURANT23,created)),
                restaurantService.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        restaurantService.update(updated);
        MATCHER.assertEquals(updated, restaurantService.get(RESTAURANT1_ID));
    }

    @Test
    public void testAddVote() throws Exception {
        restaurantService.vote(RESTAURANT1_ID + 1, USER_ID, PERMISSIBLE_DATE_TIME);

        Restaurant voted = new Restaurant(RESTAURANT2);
        Restaurant unVoted = new Restaurant(RESTAURANT1);
        voted.setNumOfVotes(RESTAURANT2.getNumOfVotes() + 1);
        unVoted.setNumOfVotes(RESTAURANT1.getNumOfVotes() - 1);

        Restaurant votedDB = restaurantService.get(RESTAURANT1_ID + 1);
        MATCHER.assertEquals(voted, votedDB);
        Restaurant unVotedDB = restaurantService.get(RESTAURANT1_ID);
        MATCHER.assertEquals(unVoted, unVotedDB);
    }

    @Test(expected = OnlyOneVoteException.class)
    public void testAddVoteOnlyOneVote() throws Exception {
        restaurantService.vote(RESTAURANT1_ID, USER_ID, PERMISSIBLE_DATE_TIME);
    }

   @Test(expected = PermissibleTimeException.class)
    public void testAddVotePermissibleTime() throws Exception {
        restaurantService.vote(RESTAURANT1_ID, USER_ID, NON_PERMISSIBLE_DATE_TIME);
    }

    @Test
    public void testDelete() throws Exception {
        restaurantService.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(getSortedRestaurants(Arrays.asList(RESTAURANT2, RESTAURANT3, RESTAURANT22, RESTAURANT23)),
                restaurantService.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        restaurantService.delete(RESTAURANT1_ID + 100);
    }

    @Test
    public void testDeleteAll() throws Exception {
        restaurantService.deleteAll();
        MATCHER.assertCollectionEquals(new ArrayList<>(),
                restaurantService.getAll());
    }

    @Test
    public void testGet() throws Exception {
        Restaurant actual = restaurantService.get(RESTAURANT1_ID);
        MATCHER.assertEquals(actual, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        restaurantService.get(RESTAURANT1_ID + 100);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> restaurants = restaurantService.getAll();
        MATCHER.assertCollectionEquals(restaurants, getSortedRestaurants(Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT22, RESTAURANT23)));
    }

    @Test
    public void testGetAllOfToday() throws Exception {
        Collection<Restaurant> restaurants = restaurantService.getAllOfToday(LocalDate.now());
        MATCHER.assertCollectionEquals(restaurants, getSortedRestaurants(Arrays.asList(RESTAURANT22, RESTAURANT23)));
    }

    @Test
    public void testThrowExpectedExceptionException() throws Exception {
        thrown.expect(Exception.class);
        throw new Exception();
    }
}