import java.util.List;

public class Hand {
    private List<Card> hand;
    private Rank rank;

    private Hand(Player player, Table table) {
        hand.addAll(player.getPocket());
        hand.addAll(table.getFlop());
        hand.add(table.getTurn());
        hand.add(table.getRiver());
        rank = Rank.HighCard;
    }

    public Rank getRank() {
        return rank;
    }
}
