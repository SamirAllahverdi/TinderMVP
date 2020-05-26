package mvp.tinder.dao;

import mvp.tinder.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Dao<A> {

    List<A> getAll(int index, int id) throws SQLException;

    A get(int id) throws SQLException;

    void put(int actualId, A data) throws SQLException;

    Optional<User> getBy(Predicate<A> p) throws SQLException;

    void update(int id) throws SQLException;
}
