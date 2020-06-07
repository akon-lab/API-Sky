package domain.Game.RootLib;

import domain.Game.Body;

public class NPC extends Body {

    //id
    //root_id
    //wayToImg
    private boolean type;//if true==kind. else dangerous

    public NPC() {
    }

    public NPC(long id, long root_id, String wayToImg, boolean type) {
        super(id, root_id, wayToImg);
        setType(type);
    }

    //getter
    public boolean isType() {
        return type;
    }

    //setter
    public void setType(boolean type) {
        this.type = type;
    }
}
