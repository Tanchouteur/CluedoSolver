package fr.tanchou.cluedoSolver;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private final GameState gameState;

    public Solver(GameState gameState) {
        this.gameState = gameState;
    }

    public void processResponse(Player responder, Card revealedCard) {
        gameState.markCardAsHeldByPlayer(revealedCard, responder);
    }

    public List<Suggestion> generateSuggestions() {
        // Logic to create optimal suggestions
        return new ArrayList<>();
    }
}