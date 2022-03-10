import java.util.List;

public class Hand {
    private List<Card> hand;
    private Rank rank;

    public Hand(Player player, Table table) {
        hand.addAll(player.getPocket());
        hand.addAll(table.getFlop());
        hand.add(table.getTurn());
        hand.add(table.getRiver());
        rank = Rank.HighCard;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank() {
        // Iterate through 5-card hands -> 7C5 = 21 combinations
        // Rank current 5-card hand *** hard part
        // If current 5-card hand ranks best so far, store rank and 5-card hand
        // Save best 5-card hand and its rank
    }
}
