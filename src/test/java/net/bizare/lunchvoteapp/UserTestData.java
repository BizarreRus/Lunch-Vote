package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Role;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int USER_ID = 1;
    private static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "password", "user@yandex.ru", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", "admin@gmail.com", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static List<User> getSortedUsers(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    public static User getUpdated() {
        return new User(USER_ID, "Updated", "password", "user@yandex.ru", Role.ROLE_USER);
    }

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

}