package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.LikedDao;
import mvp.tinder.entity.User;

import java.util.List;

public class LikedService {
    private final Dao<User> dao;

    public LikedService() {
        this.dao = new LikedDao();
    }

    public List<User> getAll(int actualId, int idx) {
        return dao.getAll(actualId, idx);
    }

    public void put(int actualId, User data) {
        dao.put(actualId, data);
    }
}
