package repository.entities;

import domain.RealPerson.Friends;
import domain.RealPerson.Player;
import jakarta.ws.rs.BadRequestException;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FriendRepository implements IEntityRepository<Friends> {
    IDBRepository dbrepo;

    @Override
    public void add(Friends entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Friends (second_id,first_id) " +
                    "VALUES('" +
                    "" + entity.getSecondPlayerId() + "'," +
                    "'" + entity.getFirstPlayerId() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Friends entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Friends SET  " +
                    "first_id = '" + entity.getFirstPlayerId() + "'," +
                    "second_id = '" + entity.getSecondPlayerId() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void remove(Friends entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Friends WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public List<Friends> query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            LinkedList<Friends> friends = new LinkedList<>();
            while (res.next()) {
                Friends friend = new Friends(
                        res.getLong("id"),
                        res.getLong("first_id"),
                        res.getLong("second_id")
                );
                friends.add(friend);
            }
            return friends;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }

    }

    @Override
    public Friends queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Friends(
                        res.getLong("id"),
                        res.getLong("first_id"),
                        res.getLong("second_id")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }



}
