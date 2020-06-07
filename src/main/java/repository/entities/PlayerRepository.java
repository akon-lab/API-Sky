package repository.entities;

import domain.Login.PlayerAuth;
import domain.RealPerson.Player;
import jakarta.ws.rs.BadRequestException;
import repository.db.PostgresRepository;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PlayerRepository implements IEntityRepository<Player> {

    private final IDBRepository dbrepo;

    public PlayerRepository() {
        dbrepo = new PostgresRepository();
    }

    @Override
    public void add(Player entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Player (fname,sname,password,birthday,username) " +
                    "VALUES('" +
                    "" + entity.getFname() + "'," +
                    "'" + entity.getSname() + "'," +
                    "'" + entity.getPassword() + "'," +
                    "'" + entity.getBirthday() + "'," +
                    "'" + entity.getUsername() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Player entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Player SET  " +
                    "fname = '" + entity.getFname() + "'," +
                    "sname = '" + entity.getSname() + "'," +
                    "password = '" + entity.getPassword() + "'," +
                    "birthday = '" + entity.getBirthday() + "'," +
                    "username = '" + entity.getUsername() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void remove(Player entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Player WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public Player getPlayerById(long id) {
        String sql = "SELECT * FROM Player WHERE id =" + id + " LIMIT 1";
        return queryOne(sql);
    }

    public Player findUserByLogin(Player data) {
        try {
            String sql = "SELECT * FROM Player WHERE username = ? AND password = ?";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, data.getUsername());
            statement.setString(2, data.getPassword());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Player(
                        result.getLong("id"),
                        result.getString("fname"),
                        result.getString("sname"),
                        result.getString("password"),
                        result.getDate("birthday"),
                        result.getString("username")
                );
            }

        } catch (SQLException ex) {
            throw new BadRequestException("Cannoy reun SQL Statement");
        }
        return null;
    }

    public Player findUserByLogin(PlayerAuth data) {
        try {
            String sql = "SELECT * FROM Player WHERE username = ? AND password = ?";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, data.getUsername());
            statement.setString(2, data.getPassword());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Player(
                        result.getLong("id"),
                        result.getString("fname"),
                        result.getString("sname"),
                        result.getString("password"),
                        result.getDate("birthday"),
                        result.getString("username")
                );
            }

        } catch (SQLException ex) {
            throw new BadRequestException("Cannoy reun SQL Statement");
        }
        return null;
    }

    public Player getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM Player WHERE username = ?";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Player(
                        result.getLong("id"),
                        result.getString("fname"),
                        result.getString("sname"),
                        result.getString("password"),
                        result.getDate("birthday"),
                        result.getString("username")
                );
            }
        } catch (SQLException exception) {
            throw new BadRequestException("Cannot run SQL statement");
        }
        return null;
    }

    @Override
    public Player queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Player(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getDate("birthday"),
                        res.getString("username")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }

    @Override
    public List<Player> query(String sql) {

        try {
            Statement st = dbrepo.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            LinkedList<Player> players = new LinkedList<>();
            while (res.next()) {
                Player player = new Player(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getDate("birthday"),
                        res.getString("username")
                );
                players.add(player);
            }
            return players;

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return null;
    }
}
