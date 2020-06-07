package domain.Game.RootLib;

import domain.Game.Body;

public class Quest extends Body {

    //id
    //root_id
    //task
    //name
    private String prize;



    public Quest(long id, long root_id, String name, String task, String prize) {
        super(id, name,root_id,task);
        setPrize(prize);

    }

    //getter

    public String getPrize() {
        return prize;
    }

    //setter

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
