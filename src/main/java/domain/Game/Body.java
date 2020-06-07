package domain.Game;

public abstract class Body {

    private long id;
    private long root_id;
    private String wayToImg;
    private String name;
    private String task;

    //constructor
    public Body(long id, String name, long root_id, String task) {//for Quest
        setId(id);
        setName(name);
        setRoot_id(root_id);
        setTask(task);
    }

    public Body(long id, String name,   String task) {//for Root
        setId(id);
        setName(name);
        setTask(task);
    }

    public Body(long id, long root_id, String wayToImg) {//for Location and NPC
        setId(id);
        setRoot_id(root_id);
        setWayToImg(wayToImg);
    }

    protected Body() {
    }

    //getter
    public long getId() {
        return id;
    }

    public long getRoot_id() {
        return root_id;
    }

    public String getWayToImg() {
        return wayToImg;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setRoot_id(long root_id) {
        this.root_id = root_id;
    }

    public void setWayToImg(String wayToImg) {
        this.wayToImg = wayToImg;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setName(String name) {
        this.name = name;
    }
}
