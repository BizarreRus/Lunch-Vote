package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    User getByEmail(String email) throws NotFoundException;
}
