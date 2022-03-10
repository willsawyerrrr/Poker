import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Game {
    private List<Player> players;
    private Table table;
    private Deck deck;

    public Game() {
        players = new ArrayList<Player>();
        table = new Table();
        deck = new Deck();
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

    public void newRound() {
        System.out.println("New game!");
        table = new Table();
        deck = new Deck();
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

    public static void main(String[] arg) {
        // work out how to utilise command line arguments
        // args should include number of players and number of decks
        Game game = new Game();
        String[] args = { "3" };

        Scanner scanner = new Scanner(System.in);
        for (Integer i = 1; i <= Integer.parseInt(args[0]); i++) {
            System.out.print(String.format("Enter player %d's name: ", i));
            String name = scanner.nextLine();
            game.addPlayer(new Player(name));
        }
        scanner.close();

        for (int i = 0; i < 10; i++) {
            game.newRound();
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