package net.bizare.lunchvoteapp.repository;

import net.bizare.lunchvoteapp.model.User;

import java.util.Collection;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    Collection<User> getAll();

    User getByEmail(String email);
}