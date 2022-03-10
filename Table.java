import java.util.ArrayList;
import java.util.List;

public class Table {
    private Deck deck;
    private List<Player> players;
    private List<Card> flop;
    private Card turn;
    private Card river;

    public Table(Deck deck) {
        this.deck = deck;
        players = new ArrayList<Player>();
        flop = new ArrayList<Card>(3);
        turn = null;
        river = null;
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public List<Card> getFlop() {
        return flop;
    }

    public Card getTurn() {
        return turn;
    }

    public Card getRiver() {
        return river;
    }

    public void generateFlop() {
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
    }

    public void generateTurn() {
        turn = deck.generateCard();
    }

    public void generateRiver() {
        river = deck.generateCard();
    }
}