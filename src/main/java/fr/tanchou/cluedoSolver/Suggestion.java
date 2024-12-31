package fr.tanchou.cluedoSolver;

public class Suggestion {
    private final Card person;
    private final Card weapon;
    private final Card room;

    public Suggestion(Card person, Card weapon, Card room) {
        this.person = person;
        this.weapon = weapon;
        this.room = room;
    }

    public Card getPerson() {
        return person;
    }

    public Card getWeapon() {
        return weapon;
    }

    public Card getRoom() {
        return room;
    }
}