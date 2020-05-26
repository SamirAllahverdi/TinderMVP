package mvp.tinder.dao;

import mvp.tinder.entity.User;
import mvp.tinder.jdbc.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LikedDao implements Dao<User> {
    private final Connection connect;

    public LikedDao() {
        this.connect = DbConnection.getConnection();
    }


    @Override
    public List<User> getAll(int index, int id) throws SQLException {
        List<User> likedUsers = new ArrayList<>();

        String SQL = "SELECT * FROM users where id IN (select whom from liked where who = ?) and id != ?";
        PreparedStatement stmt = connect.prepareStatement(SQL);
        stmt.setInt(1, index);
        stmt.setInt(2, index);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int idx = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String url = resultSet.getString("url");
            String job = resultSet.getString("job");
            Date date = resultSet.getTimestamp("last_login_time");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            User user = new User(idx, name, surname, url, job);

            LocalDate b =  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate a = LocalDate.now();
            Period period = Period.between(b,a);

            user.setDaysAgo(period.getDays());
            user.setJob(job);
            user.setTime(simpleDateFormat.format(date));
            likedUsers.add(user);
        }

        return likedUsers;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void put(int actualId, User data) throws SQLException {
        int id = data.getId();
        String SQL = "INSERT INTO liked(who, whom) VALUES(?,?) ";
        PreparedStatement stmt = connect.prepareStatement(SQL);
        stmt.setInt(1, actualId);
        stmt.setInt(2, id);
        stmt.execute();
    }

    @Override
    public Optional<User> getBy(Predicate<User> p) {
        return Optional.empty();
    }

    @Override
    public void update(int id) {

    }
}
