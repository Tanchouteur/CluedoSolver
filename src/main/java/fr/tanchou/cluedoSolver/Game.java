package fr.tanchou.cluedoSolver;

import java.util.*;

public class Game {
    private List<Card> allCards;
    private List<Player> players;
    private final GameState gameState;
    private final Solver solver;

    public Game() {
        initializeCards();
        initializePlayers();
        this.gameState = new GameState(allCards, players);
        this.solver = new Solver(gameState);
    }

    // Initialisation des cartes
    private void initializeCards() {
        allCards = new ArrayList<>();

        // Ajouter les cartes de type PERSON
        allCards.add(new Card("Scarlet", CardType.PERSON));
        allCards.add(new Card("Mustard", CardType.PERSON));
        allCards.add(new Card("Plum", CardType.PERSON));
        allCards.add(new Card("Peacock", CardType.PERSON));
        allCards.add(new Card("Green", CardType.PERSON));
        allCards.add(new Card("White", CardType.PERSON));

        // Ajouter les cartes de type WEAPON
        allCards.add(new Card("Knife", CardType.WEAPON));
        allCards.add(new Card("Revolver", CardType.WEAPON));
        allCards.add(new Card("Rope", CardType.WEAPON));
        allCards.add(new Card("Candlestick", CardType.WEAPON));
        allCards.add(new Card("Lead Pipe", CardType.WEAPON));
        allCards.add(new Card("Wrench", CardType.WEAPON));

        // Ajouter les cartes de type ROOM
        allCards.add(new Card("Kitchen", CardType.ROOM));
        allCards.add(new Card("Ballroom", CardType.ROOM));
        allCards.add(new Card("Conservatory", CardType.ROOM));
        allCards.add(new Card("Dining Room", CardType.ROOM));
        allCards.add(new Card("Billiard Room", CardType.ROOM));
        allCards.add(new Card("Library", CardType.ROOM));
        allCards.add(new Card("Lounge", CardType.ROOM));
        allCards.add(new Card("Hall", CardType.ROOM));
        allCards.add(new Card("Study", CardType.ROOM));
    }

    // Initialisation des joueurs
    private void initializePlayers() {
        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
    }

    // Démarrer le jeu
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Cluedo Solver!");
        System.out.println("The game has started with " + players.size() + " players.");

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("\n--- New Turn ---");

            // Générer une suggestion
            List<Suggestion> possibleSuggestions = solver.generateSuggestions();
            if (!possibleSuggestions.isEmpty()) {
                System.out.println("Suggested move:");
                Suggestion bestSuggestion = possibleSuggestions.get(0);
                System.out.println("Person: " + bestSuggestion.getPerson().getName());
                System.out.println("Weapon: " + bestSuggestion.getWeapon().getName());
                System.out.println("Room: " + bestSuggestion.getRoom().getName());
            } else {
                System.out.println("No suggestions available at the moment.");
            }

            // Demander une suggestion de l'utilisateur
            System.out.println("Enter your suggestion:");
            System.out.print("Person: ");
            String personName = scanner.nextLine();
            System.out.print("Weapon: ");
            String weaponName = scanner.nextLine();
            System.out.print("Room: ");
            String roomName = scanner.nextLine();

            Card person = findCardByName(personName, CardType.PERSON);
            Card weapon = findCardByName(weaponName, CardType.WEAPON);
            Card room = findCardByName(roomName, CardType.ROOM);

            if (person == null || weapon == null || room == null) {
                System.out.println("Invalid suggestion. Please try again.");
                continue;
            }

            Suggestion userSuggestion = new Suggestion(person, weapon, room);

            // Réponse du joueur
            System.out.println("Which player responded to your suggestion?");
            for (int i = 0; i < players.size(); i++) {
                System.out.println(i + 1 + ": " + players.get(i).getName());
            }
            int playerIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consommer la ligne restante

            if (playerIndex < 0 || playerIndex >= players.size()) {
                System.out.println("Invalid player. Please try again.");
                continue;
            }
            Player responder = players.get(playerIndex);

            System.out.print("What card did they show you (leave blank if no card)? ");
            String revealedCardName = scanner.nextLine();

            if (!revealedCardName.isEmpty()) {
                Card revealedCard = findCardByName(revealedCardName, null);
                if (revealedCard != null) {
                    solver.processResponse(responder, revealedCard);
                    System.out.println(responder.getName() + " revealed the card: " + revealedCard.getName());
                } else {
                    System.out.println("Card not found. Please try again.");
                }
            } else {
                System.out.println(responder.getName() + " revealed no card.");
            }

            // Vérification si toutes les cartes possibles sont résolues
            if (gameState.getPossibleCards().isEmpty()) {
                System.out.println("\nCongratulations! The solution has been found!");
                gameOver = true;
            }

            // Afficher les cartes possibles restantes
            System.out.println("Remaining possible cards:");
            for (Card card : gameState.getPossibleCards()) {
                System.out.println("- " + card.getName() + " (" + card.getType() + ")");
            }

            System.out.println("\nDo you want to continue? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("no")) {
                gameOver = true;
            }
        }

        scanner.close();
        System.out.println("Game over. Thanks for playing!");
    }

    private Card findCardByName(String name, CardType type) {
        for (Card card : allCards) {
            if (card.getName().equalsIgnoreCase(name) && (type == null || card.getType() == type)) {
                return card;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}