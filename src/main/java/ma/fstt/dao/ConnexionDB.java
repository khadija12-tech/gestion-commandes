package ma.fstt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    private static final String URL =
        "jdbc:mysql://localhost:3306/gestion_commandes?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "atelier";
    private static final String PASSWORD = "atelier123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL introuvable", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
