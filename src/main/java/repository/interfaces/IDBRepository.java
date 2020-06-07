package repository.interfaces;

import java.sql.Connection;

public interface IDBRepository {
    Connection getConnection();
}
