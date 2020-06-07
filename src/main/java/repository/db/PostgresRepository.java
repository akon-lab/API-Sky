package repository.db;

import repository.interfaces.IDBRepository;

import java.sql.*;

public class PostgresRepository implements IDBRepository {

    public Connection getConnection(){
        try {
            String conStr="jdbc:postgresql://localhost:5433/web_servise";
            return DriverManager.getConnection(conStr,"postgres","19091978");

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return null;
    }
}
