import java.util.ArrayList;
import java.util.List;

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
        return String.format("%s\n  %s\n  %s\n  $%d", name, pocket.get(0), pocket.get(1), bank);
    }
}
