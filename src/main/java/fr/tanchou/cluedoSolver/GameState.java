package fr.tanchou.cluedoSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameState {
    private List<Card> allCards;
    private List<Player> players;
    private final Map<Card, Boolean> cardPossibilities;

    public GameState(List<Card> allCards, List<Player> players) {
        this.allCards = allCards;
        this.players = players;
        this.cardPossibilities = new HashMap<>();
        for (Card card : allCards) {
            cardPossibilities.put(card, true); // All cards possible initially
        }
    }

    public void markCardAsNotPossible(Card card) {
        cardPossibilities.put(card, false);
    }

    public void markCardAsHeldByPlayer(Card card, Player player) {
        player.addCard(card);
        cardPossibilities.put(card, false);
    }

    public List<Card> getPossibleCards() {
        return cardPossibilities.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}