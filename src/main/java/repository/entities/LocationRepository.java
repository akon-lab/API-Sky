package repository.entities;

import domain.Game.RootLib.Location;
import jakarta.ws.rs.BadRequestException;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class LocationRepository implements IEntityRepository<Location> {
    IDBRepository dbrepo;

    @Override
    public void add(Location entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Location (root_id,wayToimg,name) " +
                    "VALUES('" +
                    "" + entity.getRoot_id() + "'," +
                    "'" + entity.getWayToImg() + "'," +
                    "'" + entity.getName() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }

    }

    @Override
    public void update(Location entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Location SET  " +
                    "root_id = '" + entity.getRoot_id() + "'," +
                    "wayToimg = '" + entity.getWayToImg() + "'," +
                    "name = '" + entity.getName() + "' " +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void remove(Location entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Location WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public List<Location> query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);

            LinkedList<Location> locations = new LinkedList<>();
            while (res.next()) {
                Location location=new Location(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("wayToimg"),
                        res.getString("name")
                );
                locations.add(location);
            }
            return locations;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }
    }

    @Override
    public Location queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Location(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("wayToimg"),
                        res.getString("name")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }
}
