package servises;

import domain.Game.RootLib.Location;
import domain.Game.RootLib.NPC;
import domain.Game.RootLib.Quest;
import repository.entities.LocationRepository;
import repository.entities.NPCRepository;
import repository.entities.QuestRepository;

import java.util.List;

public class GameServer {
    private final NPCRepository npcRepo = new NPCRepository();
    private final LocationRepository locationRepo = new LocationRepository();
    private final QuestRepository questRepo = new QuestRepository();

    public List<NPC> getNPCByRootId(long root_id) {
        List<NPC> npcs;
        String sql = "SELECT * FROM NPC WHERE root_id = " + root_id;
        npcs = npcRepo.query(sql);
        return npcs;
    }

    public List<Location> getLocationByRootId(long root_id) {
        List<Location> locations;
        String sql = "SELECT* FROM Location WHERE root_id = " + root_id;
        locations = locationRepo.query(sql);
        return locations;
    }

    public List<Quest> getQuestByRootId(long root_id) {
        List<Quest> quests;
        String sql = "SELECT * FROM Quest WHERE root_id = " + root_id;
        quests = questRepo.query(sql);
        return quests;
    }
}
