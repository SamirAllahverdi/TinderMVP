package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.LikedDao;
import mvp.tinder.entity.User;

import java.sql.SQLException;
import java.util.List;

public class LikedService {
    private final Dao<User> dao;

    public LikedService() {
        this.dao = new LikedDao();
    }

    public List<User> getAll(int actualId, int idx) {
        try {
            return dao.getAll(actualId,idx);
        } catch (SQLException a) {
            throw new RuntimeException("SqlException");
        }
    }

    public void put(int actualId, User data) {
        try {
            dao.put(actualId, data);
        } catch (SQLException a) {
            throw new RuntimeException("SqlException");
        }
    }

}
