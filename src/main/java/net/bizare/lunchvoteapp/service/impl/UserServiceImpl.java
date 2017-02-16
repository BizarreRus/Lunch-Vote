package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.repository.UserRepository;
import net.bizare.lunchvoteapp.service.UserService;
import net.bizare.lunchvoteapp.to.UserTo;
import net.bizare.lunchvoteapp.util.UserUtil;
import net.bizare.lunchvoteapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

import static net.bizare.lunchvoteapp.util.ValidationUtil.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");

        user.setRoles(repository.get(user.getId()).getRoles());
        repository.save(user);
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        repository.save(UserUtil.updateFromTo(user, userTo));
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

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
