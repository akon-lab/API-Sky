package domain.Game;

import domain.Game.RootLib.Location;
import domain.Game.RootLib.NPC;
import domain.Game.RootLib.Quest;
import servises.GameServer;

import java.util.LinkedList;
import java.util.List;

public class Root extends Body {

    //id
    //name
    private List<NPC> npcs;
    private List<Location> locations;
    private List<Quest> quests;

    private GameServer gameServer = new GameServer();

    public Root() {
        npcs = new LinkedList<>();
        locations = new LinkedList<>();
        quests = new LinkedList<>();
    }

    public Root(long id, String name, String task) {
        super(id, name, task);
        npcs = new LinkedList<>();
        locations = new LinkedList<>();
        quests = new LinkedList<>();
    }

    //getter
    public List<Location> getLocations() {
        return locations;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    //setter
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setNpcs(List<NPC> npcs) {
        this.npcs = npcs;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    //list
    public void addQuest() {
        //if (super.getId()==)
    }
}
