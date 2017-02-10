package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.model.Role;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.repository.RestaurantRepository;
import net.bizare.lunchvoteapp.repository.UserRepository;
import net.bizare.lunchvoteapp.service.UserService;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

import static net.bizare.lunchvoteapp.util.ValidationUtil.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");

        user.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)));
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");

        user.setRoles(repository.get(user.getId()).getRoles());
        repository.save(user);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) repository.getAll();
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), email);
    }
}
