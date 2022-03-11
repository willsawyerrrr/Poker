import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    private String name;
    private List<Card> pocket;
    private Integer bank;
    private Boolean inHand;
    private Integer handsWon;

    public Player(String name) {
        this.name = name;
        pocket = new ArrayList<Card>();
        bank = 100;
        inHand = true;
        handsWon = 0;
    }

    public String getName() {
        return name;
    }

    public List<Card> getPocket() {
        return pocket;
    }

    public Integer getBank() {
        return bank;
    }

    public Boolean getInHand() {
        return inHand;
    }

    public Integer getHandsWon() {
        return handsWon;
    }

    public void dealIn(Card card) {
        pocket.add(card);
        inHand = true;
    }

    public Boolean bet(Integer bet) {
        if (bet <= bank) {
            bank -= bet;
            return true;
        }
        return false;
    }

    public void fold() {
        inHand = false;
    }

    public void collectWinnings(Integer winnings) {
        bank += winnings;
        handsWon++;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("%s:", name));
        joiner.add(String.format("  %s", pocket.get(0)));
        joiner.add(String.format("  %s", pocket.get(1)));
        joiner.add(String.format("  %s", bank));
        return joiner.toString();
    }
}
