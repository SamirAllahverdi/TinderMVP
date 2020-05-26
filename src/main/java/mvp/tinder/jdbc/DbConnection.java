package mvp.tinder.jdbc;

import mvp.tinder.server.HerokuEnv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static final String URL = "jdbc:postgresql://ec2-46-137-84-140.eu-west-1.compute.amazonaws.com:5432/d985dfb4ttekvk";
    public static final String NAME = "weanugortldryd";
    public static final String PWD = "cc460a74f2822738b69616503698d3ecfbea6bdc9732e1b85e516f4885f3064d";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
//                connection = DriverManager.getConnection(URL, NAME, PWD);
                connection = DriverManager.getConnection(HerokuEnv.jdbc_url());
            } catch (SQLException e) {
                throw new RuntimeException("something went wrong while connecting to db", e);
            }
        }
        return connection;
    }
}
