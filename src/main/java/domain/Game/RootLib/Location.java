package domain.Game.RootLib;

import domain.Game.Body;

public class Location  extends Body {

    //id
    //root_id
    //wayToImg
    //name
    private String name;

    public Location(long id, long root_id, String wayToImg,String name) {
        super(id, root_id, wayToImg);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
