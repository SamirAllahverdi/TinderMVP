package mvp.tinder.dao;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.entity.User;
import mvp.tinder.jdbc.DbConnection;

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

@Log4j2
public class UserDao implements Dao<User> {

    @Override
    public List<User> getAll(int index, int id) {
        return null;
    }

    @Override
    public User get(int id) {
        String SQL = "SELECT * FROM users where id NOT IN (select whom from liked where who = ?) and id != ? ORDER BY RANDOM ()  LIMIT 1";
        User user = null;

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String url = resultSet.getString("url");
                String job = resultSet.getString("job");

                user = new User(userId, name, surname, url, job);
            }
        } catch (SQLException e) {
            log.error("SQL exception");
        }
        return user;
    }

    @Override
    public void put(int actualId, User data) {
        String SQL = "INSERT INTO users (login, password, url, name, surname,job) values ( ?, ?, ?, ?, ?,?)";

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            stmt.setString(1, data.getUsername());
            stmt.setString(2, data.getPassword());
            stmt.setString(3, data.getPicture());
            stmt.setString(4, data.getName());
            stmt.setString(5, data.getSurname());
            stmt.setString(6, data.getJob());
            stmt.execute();
        } catch (SQLException e) {
            log.error("SQL exception");
        }
    }

    @Override
    public Optional<User> getBy(Predicate<User> p) {
        List<User> allUsers = new ArrayList<>();
        String SQL = "SELECT * FROM users";

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String url = resultSet.getString("url");
                String username = resultSet.getString("login");
                String password = resultSet.getString("password");
                String job = resultSet.getString("job");
                Date date = resultSet.getTimestamp("last_login_time");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                LocalDate b = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate a = LocalDate.now();
                Period period = Period.between(b, a);
                User user = new User(id, name, surname, url, job, username, password, simpleDateFormat.format(date), period.getDays());
                allUsers.add(user);
            }
        } catch (SQLException e) {
            log.error("SQL exception");
        }
        return allUsers.stream().filter(p).findFirst();
    }

    @Override
    public void update(int id) {
        String SQL = "UPDATE users SET last_login_time = Default WHERE id = ?";

        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            log.error("SQL exception");
        }
    }
}
