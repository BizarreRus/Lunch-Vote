package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.model.Role;
import net.bizare.lunchvoteapp.model.User;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "password", "user@yandex.ru", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", "admin@gmail.com", Role.ROLE_ADMIN);

    public static List<User> getSortedUsers(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                    )
    );

    public static User getUpdated() {
        return new User(USER_ID, "Updated", "password", "user@yandex.ru", Role.ROLE_USER);
    }
}