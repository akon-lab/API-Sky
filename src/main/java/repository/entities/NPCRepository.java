package repository.entities;

import domain.Game.RootLib.NPC;
import jakarta.ws.rs.BadRequestException;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class NPCRepository implements IEntityRepository<NPC> {
    IDBRepository dbrepo;

    @Override
    public void add(NPC entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO NPC (root_id,type,way_To_Img) " +
                    "VALUES('" +
                    "" + entity.getRoot_id() + "'," +
                    "'" + entity.isType() + "'," +
                    "'" + entity.getWayToImg() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void update(NPC entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE NPC SET  " +
                    "root_id = '" + entity.getRoot_id() + "'," +
                    "type = '" + entity.isType() + "'," +
                    "way_To_img = '" + entity.getWayToImg() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }


    @Override
    public void remove(NPC entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM NPC WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public List<NPC>query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            LinkedList<NPC> npcs = new LinkedList<>();
            while (res.next()) {
                NPC npc = new NPC(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("way_To_img"),
                        res.getBoolean("type")
                );
                npcs.add(npc);
            }
            return npcs;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }
    }

    @Override
    public NPC queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new NPC(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("way_To_img"),
                        res.getBoolean("type")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }
}
