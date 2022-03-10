import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Table {
    private List<Card> flop;
    private Card turn;
    private Card river;
    private Integer pot;

    public Table() {
        flop = new ArrayList<Card>(3);
        turn = null;
        river = null;
        pot = null;
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

    public Integer getPot() {
        return pot;
    }

    public void generateFlop(Deck deck) {
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
    }

    public void generateTurn(Deck deck) {
        turn = deck.generateCard();
    }

    public void generateRiver(Deck deck) {
        river = deck.generateCard();
    }

    public void addToPot(Integer bet) {
        pot += bet;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Flop:");
        for (Card card : flop) {
            joiner.add(String.format("  %s", card));
        }
        joiner.add(String.format("  %s", turn));
        joiner.add(String.format("  %s", river));
        joiner.add("Pot:");
        joiner.add(String.format("  %d", pot));
        return joiner.toString();
    }
}