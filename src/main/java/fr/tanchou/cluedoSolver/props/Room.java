package fr.tanchou.cluedoSolver.props;

public abstract class Room {
    private final String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}