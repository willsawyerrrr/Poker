import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    private String name;
    private List<Card> pocket;
    private Integer bank;
    private Boolean inHand;
    private Integer handsWon;
    private Integer currentBet;

    public Player(String name) {
        this.name = name;
        pocket = new ArrayList<Card>();
        bank = 100;
        inHand = true;
        handsWon = 0;
        currentBet = 0;
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

    public Integer getCurrentBet() {
        return currentBet;
    }

    public Boolean getAllIn() {
        return bank == 0;
    }

    public void dealIn(Card card) {
        pocket.add(card);
    }

    public Boolean bet(Integer bet) {
        if (bet <= bank) {
            bank -= bet;
            currentBet += bet;
            return true;
        }
        return false;
    }

    public Integer allIn() {
        Integer result = bank;
        bank = 0;
        currentBet += result;
        return result;
    }

    public void fold() {
        inHand = false;
    }

    public void collectWinnings(Integer winnings) {
        bank += winnings;
        handsWon++;
    }

    public void resetBet() {
        currentBet = 0;
    }

    public void resetAll() {
        pocket.clear();
        inHand = true;
        currentBet = 0;
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
