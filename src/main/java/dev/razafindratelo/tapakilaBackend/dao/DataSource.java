package dev.razafindratelo.tapakilaBackend.dao;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Repository
public class DataSource {
    private final String url;
    private final String username;
    private final String password;

    public DataSource() {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("DB_URL");
        this.username = dotenv.get("DB_USERNAME");
        this.password = dotenv.get("DB_PASSWORD");
    }

    public Connection getConnection(String className){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            log.info("{} :: Connection established !", className);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
