package mvp.tinder.dao;

import mvp.tinder.entity.Message;
import mvp.tinder.entity.User;
import mvp.tinder.jdbc.DbConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MessagesDao implements Dao<Message> {

    private final Connection connect;

    public MessagesDao() {
        this.connect = DbConnection.getConnection();
    }


    @Override
    public List<Message> getAll(int sender, int receiver) throws SQLException {

        String SQL = "SELECT * FROM messages Where sender_id=? AND receiver_id=? OR sender_id =? AND receiver_id =? ORDER BY time";
        PreparedStatement preparedStatement = connect.prepareStatement(SQL);
        preparedStatement.setInt(1, sender);
        preparedStatement.setInt(2, receiver);
        preparedStatement.setInt(3, receiver);
        preparedStatement.setInt(4, sender);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Message> messages = new ArrayList<>();
        while (resultSet.next()) {
            int who = resultSet.getInt("sender_id");
            int whom = resultSet.getInt("receiver_id");
            String text = resultSet.getString("text");

            Timestamp date  = resultSet.getTimestamp("time");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Message m = new Message(who, whom, text, simpleDateFormat.format(date));
            messages.add(m);
        }

        return messages;
    }

    @Override
    public Message get(int id)  {
        return null;
    }


    @Override
    public void put(int actualId, Message data) throws SQLException {
        String SQL = "INSERT INTO messages(sender_id, receiver_id,message_id,text,time) VALUES(?,?,Default,?,DEFAULT)";
        PreparedStatement stmt = connect.prepareStatement(SQL);
        stmt.setInt(1, actualId);
        stmt.setInt(2, data.getWhom());
        stmt.setString(3, data.getText());
        stmt.execute();

    }

    @Override
    public Optional<User> getBy(Predicate<Message> p) {
        return Optional.empty();
    }

    @Override
    public void update(int id) {

    }
}
