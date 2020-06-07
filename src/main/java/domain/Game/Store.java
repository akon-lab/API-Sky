package domain.Game;

public class Store {
    private int id;
    private String name;
    private String definition;
    private int cost;

    //getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public int getCost() {
        return cost;
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    //another

    @Override
    public String toString() {
        return  name + '\'' + "definition= " + definition + '\'' +  "cost= " + cost + '}';
    }
}
