package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.UserDao;
import mvp.tinder.entity.User;

import java.util.Optional;
import java.util.function.Predicate;


public class UserService {
    private final Dao<User> dao;

    public UserService() {
        this.dao = new UserDao();
    }

    public void put(int actualId, User user) {
        dao.put(actualId, user);
    }

    public Optional<User> getBy(Predicate<User> p) {
        return dao.getBy(p);
    }

    public void update(int id) {
        dao.update(id);
    }

    public User get(int id) {
        return dao.get(id);
    }

}
