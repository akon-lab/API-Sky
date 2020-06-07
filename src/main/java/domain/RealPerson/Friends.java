package domain.RealPerson;

public class Friends {
    long id;
    long firstPlayerId;
    long secondPlayerId;

    public Friends() {
    }

    public Friends(long firstPlayerId, long secondPlayerId) {
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
    }

    public Friends(long id, long firstPlayerId, long secondPlayerId) {
        this.id = id;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
    }

    //getter
    public long getId() {
        return id;
    }

    public long getFirstPlayerId() {
        return firstPlayerId;
    }

    public long getSecondPlayerId() {
        return secondPlayerId;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setFirstPlayerId(long firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public void setSecondPlayerId(long secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }
}
