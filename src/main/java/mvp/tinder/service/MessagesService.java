package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.MessagesDao;
import mvp.tinder.entity.Message;

import java.sql.SQLException;
import java.util.List;

public class MessagesService {
    private final Dao<Message> dao;

    public MessagesService() {
        this.dao = new MessagesDao();
    }

    public List<Message> getAll(int index, int idx) {
        try {
            return dao.getAll(index,idx);
        } catch (SQLException e) {
            throw new RuntimeException("SqlException");
        }

    }

    public Message get(int id) throws SQLException {
        return dao.get(id);
    }

    public void put(int actualId, Message data) {
        try {
            dao.put(actualId, data);
        } catch (SQLException e) {
            throw new RuntimeException("SqlException");
        }
    }

}
