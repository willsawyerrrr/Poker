import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Game {
    private List<Player> players;
    private Table table;
    private Deck deck;

    public Game(Integer numDecks) {
        players = new ArrayList<Player>();
        table = new Table();
        deck = new Deck(numDecks);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Table getTable() {
        return table;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void dealPocket() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.generateCard());
            }
        }
    }

    public void bettingRound() {
        System.out.println("Taking bets");
        // ask for bets
        // set minimum to call based off previous bets
        // exit when even
    }

    public void newRound(Integer numDecks) {
        System.out.println("New game!");
        table = new Table();
        deck = new Deck(numDecks);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Player player : players) {
            joiner.add(player.toString());
        }
        joiner.add("");
        joiner.add(table.toString());
        return joiner.toString();
    }

    public static void main(String[] args) {
        // work out how to utilise command line arguments
        // args should include number of players and number of decks
        Integer numDecks = Integer.parseInt(args[1]);
        Integer numHands = Integer.parseInt(args[2]);
        Game game = new Game(numDecks);

        Scanner scanner = new Scanner(System.in);
        for (Integer i = 1; i <= Integer.parseInt(args[0]); i++) {
            System.out.print(String.format("Enter player %d's name: ", i));
            String name = scanner.nextLine();
            game.addPlayer(new Player(name));
        }
        scanner.close();

        for (int i = 0; i < numHands; i++) {
            game.newRound(numDecks);
            Table table = game.getTable();
            Deck deck = game.getDeck();

            game.dealPocket();
            System.out.println(game + "\n\n");
            game.bettingRound();

            table.generateFlop(deck);
            System.out.println(game + "\n\n");
            game.bettingRound();

            table.generateTurn(deck);
            System.out.println(game + "\n\n");
            game.bettingRound();

            table.generateRiver(deck);
            System.out.println(game + "\n\n");
            game.bettingRound();

            // Determine and pay winner
            // Player winner = ???;
            // table.payWinner(winner);
        }
    }
}