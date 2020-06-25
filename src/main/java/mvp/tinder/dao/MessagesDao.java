package mvp.tinder.dao;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.entity.Message;
import mvp.tinder.entity.User;
import mvp.tinder.jdbc.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Log4j2
public class MessagesDao implements Dao<Message> {

    @Override
    public List<Message> getAll(int sender, int receiver) {
        List<Message> messages = new ArrayList<>();
        String SQL = "SELECT * FROM messages Where sender_id=? AND receiver_id=? OR sender_id =? AND receiver_id =? ORDER BY time";

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, sender);
            stmt.setInt(2, receiver);
            stmt.setInt(3, receiver);
            stmt.setInt(4, sender);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int who = resultSet.getInt("sender_id");
                int whom = resultSet.getInt("receiver_id");
                String text = resultSet.getString("text");
                Timestamp date = resultSet.getTimestamp("time");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Message m = new Message(who, whom, text, simpleDateFormat.format(date));
                messages.add(m);
            }
        } catch (SQLException e) {
            log.error("SQL exception");
        }
        return messages;
    }

    @Override
    public Message get(int id) {
        return null;
    }

    @Override
    public void put(int actualId, Message data) {
        String SQL = "INSERT INTO messages(sender_id, receiver_id,message_id,text,time) VALUES(?,?,Default,?,DEFAULT)";

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, actualId);
            stmt.setInt(2, data.getWhom());
            stmt.setString(3, data.getText());
            stmt.execute();
        } catch (SQLException e) {
            log.error("SQL exception");
        }
    }

    @Override
    public Optional<User> getBy(Predicate<Message> p) {
        return Optional.empty();
    }

    @Override
    public void update(int id) {

    }
}
