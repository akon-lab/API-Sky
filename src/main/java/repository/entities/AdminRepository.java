package repository.entities;

import domain.Login.AdminAuth;
import domain.RealPerson.Administrator;
import jakarta.ws.rs.BadRequestException;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AdminRepository implements IEntityRepository<Administrator> {
    IDBRepository dbrepo;

    @Override
    public void add(Administrator entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Admin (fname,sname,password,birthday,rang) " +
                    "VALUES('" +
                    "" + entity.getFname() + "'," +
                    "'" + entity.getSname() + "'," +
                    "'" + entity.getPassword() + "'," +
                    "'" + entity.getBirthday() + "'," +
                    "'" + entity.getRang() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Administrator entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Admin SET  " +
                    "fname = '" + entity.getFname() + "'," +
                    "sname = '" + entity.getSname() + "'," +
                    "password = '" + entity.getPassword() + "'," +
                    "birthday = '" + entity.getBirthday() + "'," +
                    "rang = '" + entity.getRang() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void remove(Administrator entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Admin WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public Administrator getAdminById(long id) {
        String sql = "SELECT * FROM Admin WHERE id =" + id + " LIMIT 1";
        return queryOne(sql);
    }

    public Administrator findUserByLogin(AdminAuth data) {
        try {
            String sql = "SELECT * FROM Admin WHERE identity_name = ? AND password= ? LIMIT 1";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, data.getUsername());
            statement.setString(2, data.getPassword());

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return new Administrator(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getString("rang"),
                        res.getString("identity_name")
                );
            }

        } catch (SQLException ex) {
            throw new BadRequestException("Cannoy reun SQL Statement");
        }
        return null;
    }

    public Administrator findUserByLogin(Administrator data) {
        try {
            String sql = "SELECT * FROM Admin WHERE identity_name = ? AND password= ? LIMIT 1";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, data.getIdentityName());
            statement.setString(2, data.getPassword());

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return new Administrator(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getString("rang"),
                        res.getString("identity_name")
                );
            }

        } catch (SQLException ex) {
            throw new BadRequestException("Cannoy reun SQL Statement");
        }
        return null;
    }

    public Administrator getUserByIdentityName(String identity_name) {
        try {
            String sql = "SELECT * FROM Admin WHERE identity_name = ? LIMIT 1";
            PreparedStatement statement = dbrepo.getConnection().prepareStatement(sql);
            statement.setString(1, identity_name);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return new Administrator(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getString("rang"),
                        res.getString("identity_name")
                );
            }
        } catch (SQLException exception) {
            throw new BadRequestException("Cannot run SQL statement");
        }
        return null;
    }

    @Override
    public Administrator queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Administrator(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getString("rang"),
                        res.getString("identity_name")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }

    @Override
    public List<Administrator> query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            LinkedList<Administrator> admins = new LinkedList<>();
            while (res.next()) {
                Administrator admin= new Administrator(
                        res.getLong("id"),
                        res.getString("fname"),
                        res.getString("sname"),
                        res.getString("password"),
                        res.getString("rang"),
                        res.getString("identity_name")
                );
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }

    }
}
