import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    private String name;
    private List<Card> pocket;
    private Integer bank;

    public Player(String name) {
        this.name = name;
        pocket = new ArrayList<Card>();
        bank = 100;
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

    public void addCard(Card card) {
        pocket.add(card);
    }

    public Boolean bet(Integer bet) {
        if (bet <= bank) {
            bank -= bet;
            return true;
        }
        return false;
    }

    public void collectWinnings(Integer winnings) {
        bank += winnings;
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
