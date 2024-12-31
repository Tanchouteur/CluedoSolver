package fr.tanchou.cluedoSolver;

public class Card {
    private final String name;
    private final CardType type;

    public Card(String name, CardType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }
}

