package fr.tanchou.cluedoSolver.props;

public abstract class Weapon {
    private final String name;

    public Weapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}