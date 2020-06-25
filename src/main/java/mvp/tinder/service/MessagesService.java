package mvp.tinder.service;

import mvp.tinder.dao.Dao;
import mvp.tinder.dao.MessagesDao;
import mvp.tinder.entity.Message;

import java.util.List;

public class MessagesService {
    private final Dao<Message> dao;

    public MessagesService() {
        this.dao = new MessagesDao();
    }

    public List<Message> getAll(int index, int idx) {
        return dao.getAll(index, idx);
    }

    public Message get(int id) {
        return dao.get(id);
    }

    public void put(int actualId, Message data) {
        dao.put(actualId, data);
    }
}
