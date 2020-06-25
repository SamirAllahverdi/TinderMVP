package mvp.tinder.jdbc;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.helper.HerokuEnv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class DbConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(HerokuEnv.jdbc_url());
            } catch (SQLException e) {
                log.error("connection to database failed");
                throw new RuntimeException("something went wrong while connecting to db", e);
            }
        }
        return connection;
    }
}
