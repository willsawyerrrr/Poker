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

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Player player : players) {
            joiner.add(player.toString());
        }
        joiner.add(table.toString());
        return joiner.toString();
    }

    public static void main(String[] args) {
        // args include number of players and number of decks
    }
}