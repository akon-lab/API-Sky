package repository.entities;

import domain.Game.Notification;
import jakarta.ws.rs.BadRequestException;
import logs.LogWriter;
import repository.interfaces.IDBRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class NotificationRepository  {
    private IDBRepository dbrepo;
    private LogWriter logWriter;

    public void add(long playerId, String message) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Notification (player_id,message,read) " +
                    "VALUES('" +
                    "'" + playerId + "'," +
                    "'" + message + "'," +
                    "'" + false + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());

        }
    }

    public void update(Notification entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Notification SET  " +
                    "player_id = '" + entity.getPlayerId() + "'," +
                    "message = '" + entity.getMessage() + "'," +
                    "read = '" + entity.isRead() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void remove(Notification entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Notification WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public List<Notification> query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            LinkedList<Notification> notifications = new LinkedList<>();
            while (res.next()) {
                Notification notification = new Notification(
                        res.getLong("id"),
                        res.getLong("player_id"),
                        res.getString("message"),
                        res.getBoolean("read")
                );
                notifications.add(notification);
            }
            return notifications;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }
    }

    public Notification queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Notification(
                        res.getLong("id"),
                        res.getLong("player_id"),
                        res.getString("message"),
                        res.getBoolean("read")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }

    public List<Notification> getNotificationsByPlayerId(long id) {

        List<Notification> notifications;

        String sql = "SELECT message FROM Notifications WHERE player_id =" + id;

        notifications = query(sql);
        return notifications;
    }

}
