package repository.entities;

import domain.Game.RootLib.Quest;
import jakarta.ws.rs.BadRequestException;
import repository.interfaces.IDBRepository;
import repository.interfaces.IEntityRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class QuestRepository implements IEntityRepository<Quest> {
    IDBRepository dbrepo;

    @Override
    public void add(Quest entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "INSERT INTO Quest (root_id,task,name,prize) " +
                    "VALUES('" +
                    "" + entity.getRoot_id() + "'," +
                    "'" + entity.getTask() + "'," +
                    "'" + entity.getName() + "'," +
                    "'" + entity.getPrize() + "'" +
                    ")";
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void update(Quest entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "UPDATE Quest SET  " +
                    "root_id = '" + entity.getRoot_id() + "'," +
                    "task = '" + entity.getTask() + "'," +
                    "name = '" + entity.getName() + "'," +
                    "prize = '" + entity.getPrize() + "'" +
                    "WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public void remove(Quest entity) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            String sql = "DELETE FROM Quest WHERE id = " + entity.getId();
            statement.execute(sql);
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Override
    public List<Quest> query(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            LinkedList<Quest> quests = new LinkedList<>();
            while (res.next()) {
                Quest quest=new Quest(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("task"),
                        res.getString("name"),
                        res.getString("prize")
                );
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());
        }
    }

    @Override
    public Quest queryOne(String sql) {
        try {
            Statement statement = dbrepo.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return new Quest(
                        res.getLong("id"),
                        res.getLong("root_id"),
                        res.getString("task"),
                        res.getString("name"),
                        res.getString("prize")
                );
            }

        } catch (SQLException e) {
            throw new BadRequestException("Cannot run SQL statement " + e.getSQLState());

        }
        return null;
    }
}
