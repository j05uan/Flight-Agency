package resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionBaseDeDatos {
    private static final String URL = "jdbc:mysql://root:vjPwlmiNZwTGRXSCsWvvQeZatsgoOiSm@roundhouse.proxy.rlwy.net:51020/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "vjPwlmiNZwTGRXSCsWvvQeZatsgoOiSm";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
