import java.util.ArrayList;
import java.util.List;
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
        // ask for bets
        // set minimum to call based off previous bets
        // exit when even
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
        // args should include number of players and number of decks
        Game game = new Game();
        Table table = game.getTable();
        Deck deck = game.getDeck();

        Player logan = new Player("Logan");
        Player will = new Player("Will");
        Player zayne = new Player("Zayne");

        game.addPlayer(logan);
        game.addPlayer(will);
        game.addPlayer(zayne);

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
    }
}