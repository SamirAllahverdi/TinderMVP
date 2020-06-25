package mvp.tinder.dao;

import mvp.tinder.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Dao<A> {

    List<A> getAll(int index, int id);

    A get(int id);

    void put(int actualId, A data);

    Optional<User> getBy(Predicate<A> p);

    void update(int id);
}
