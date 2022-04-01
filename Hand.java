import java.util.List;

public class Hand {
    /** The player's pocket cards and the community cards. */
    private List<Card> hand;
    /** The rank of the player's best five-card hand. */
    private Rank rank;

    /**
     * Creates a new seven-card hand for the given player.
     * 
     * @param player player whose hand to evaluate
     * @param table  table of current game
     */
    public Hand(Player player, Table table) {
        hand.addAll(player.getPocket());
        hand.addAll(table.getFlop());
        hand.add(table.getTurn());
        hand.add(table.getRiver());
        rank = Rank.HighCard;
    }

    /**
     * Returns the rank of the player's best five-card hand.
     * 
     * @return the rank of the player's best five-card hand
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Evaluates the rank of the player's best five-card hand.
     */
    public void setRank() {
        // Iterate through 5-card hands -> 7C5 = 21 combinations
        // Rank current 5-card hand *** hard part
        // If current 5-card hand ranks best so far, store rank and 5-card hand
        // Save best 5-card hand and its rank
    }
}
