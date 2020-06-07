package domain.Game;

public class Notification {
    private long id;
    private long playerId;
    private String message;
    private boolean read;

    public Notification() {

    }

    public Notification(long playerId, String message, boolean read) {

        this.playerId = playerId;
        this.message = message;
        this.read = read;
    }

    public Notification(long id, long playerId, String message, boolean read) {
        this.id = id;
        this.playerId = playerId;
        this.message = message;
        this.read = read;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    //getter
    public long getId() {
        return id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }
}
