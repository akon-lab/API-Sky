package servises;

import domain.Game.Notification;
import domain.RealPerson.Friends;
import domain.RealPerson.Player;
import repository.entities.FriendRepository;
import repository.entities.NotificationRepository;
import repository.entities.PlayerRepository;
import servises.interfaces.IPersonServices;

import java.util.LinkedList;
import java.util.List;

public class PlayerServices implements IPersonServices<Player> {
    private final PlayerRepository playerRepo = new PlayerRepository();
    private final FriendRepository friendRepo = new FriendRepository();
    private final NotificationRepository noteRepo = new NotificationRepository();

    @Override
    public void update(Player player) {
        playerRepo.update(player);
    }

    @Override
    public void delete(long id) {
        playerRepo.remove(getById(id));
    }

    @Override
    public Player getById(long id) {
        return playerRepo.getPlayerById(id);
    }

    @Override
    public Player getByUsername(String username) {
        return playerRepo.getUserByUsername(username);
    }

    @Override
    public Player findByLogin(Player person) {
        return playerRepo.findUserByLogin(person);
    }

    @Override
    public void add(Player person) {
        playerRepo.add(person);
    }

    public void addFriend(long id, long friendId) {

        Friends newFriends = new Friends(id, friendId);
        friendRepo.add(newFriends);
    }

    public List<Player> getFriendsByPlayerId(long id) {

        PlayerRepository playerRepo = new PlayerRepository();
        List<Player> players = new LinkedList<>();
        List<Player> players_first;
        List<Player> players_second;


        String sql_first = "SELECT first_id FROM Friends WHERE second_id =" + id;
        String sql_second = "SELECT second_id FROM Friends WHERE first_id =" + id;


        players_first = playerRepo.query(sql_first);
        players_second = playerRepo.query(sql_second);


        players.addAll(players_first);
        players.addAll(players_second);

        return players;
    }

    //notifications
    public void addMessageToPLayerNotifications(Player player, String message) {
        noteRepo.add(player.getId(), message);
    }

    public Notification getFirstNotificationFromAnotherPlayer(long id) {
        List<Notification> notes =  noteRepo.getNotificationsByPlayerId(id);
        return notes.get(0);
    }
}
