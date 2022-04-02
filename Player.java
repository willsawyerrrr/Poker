import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    /** The player's name. */
    private String name;
    /** The cards in the player's pocket. */
    private List<Card> pocket;
    /** The player's best five-card hand. */
    private List<Card> hand;
    /** The rank of the player's best five-card hand. */
    public Rank rank;
    /** The player's money. */
    private Integer bank;
    /** Whether the player is in the current hand. */
    private Boolean inHand;
    /** The number of hands the player has won. */
    private Integer handsWon;
    /** The amount of money the player has bet in the current game. */
    private Integer currentBet;

    /**
     * Creates a new player with the given name.
     * 
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        pocket = new ArrayList<Card>();
        bank = 100;
        inHand = true;
        handsWon = 0;
        currentBet = 0;
    }

    /**
     * Returns the player's name.
     * 
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cards in the player's pocket.
     * 
     * @return the cards in the player's pocket
     */
    public List<Card> getPocket() {
        return pocket;
    }

    /**
     * Returns the player's best five-card hand.
     * 
     * @return the player's best five-card hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Evaluates the player's best five-card hand.
     * 
     * @param table the table of the current game
     */
    public void evaluateHand(Table table) {
        Hand sevenCardHand = new Hand(this, table);
        hand = sevenCardHand.getBestHand();
        rank = sevenCardHand.getRank();
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
     * Returns the player's money.
     * 
     * @return the player's money
     */
    public Integer getBank() {
        return bank;
    }

    /**
     * Returns whether the player is in the current hand.
     * 
     * @return whether the player is in the current hand
     */
    public Boolean getInHand() {
        return inHand;
    }

    /**
     * Returns the number of hands the player has won.
     * 
     * @return the number of hands the player has won
     */
    public Integer getHandsWon() {
        return handsWon;
    }

    /**
     * Returns the amount of money the player has bet in the current game.
     * 
     * @return the amount of money the player has bet in the current game
     */
    public Integer getCurrentBet() {
        return currentBet;
    }

    /**
     * Returns whether the player has bet all their money in the current game.
     * 
     * @return whether the player has bet all their money in the current game
     */
    public Boolean getAllIn() {
        return bank == 0;
    }

    /**
     * Adds the given card to the player's pocket.
     * 
     * @param card card to add to the player's pocket
     */
    public void dealIn(Card card) {
        pocket.add(card);
    }

    /**
     * Attemps to take the given amount of money from the player's bank and
     * returns whether it was successful. The attempt is unsuccessful if the
     * amount is greater than the player's bank
     * 
     * @param amount amount of money to remove from the player's bank
     * 
     * @return whether the money was successfully taken
     */
    public Boolean bet(Integer amount) {
        if (amount <= bank) {
            bank -= amount;
            currentBet += amount;
            return true;
        }
        return false;
    }

    /**
     * Bets the player's entire bank. Sets the player's bank to 0.
     * Returns the amount of money the player bet.
     * 
     * @return the amount of money the player bet
     */
    public Integer allIn() {
        Integer result = bank;
        bank = 0;
        currentBet += result;
        return result;
    }

    /**
     * Takes the player out of the current hand.
     */
    public void fold() {
        inHand = false;
    }

    /**
     * Adds game winnings to the player's bank. Increments the number of hands
     * the player has won.
     * 
     * @param winnings amount of money won from the game
     */
    public void collectWinnings(Integer winnings) {
        bank += winnings;
        handsWon++;
    }

    /**
     * Resets the player's bet for a new betting round.
     */
    public void resetBet() {
        currentBet = 0;
    }

    /**
     * Resets the player for a new game.
     */
    public void resetAll() {
        pocket.clear();
        inHand = true;
        currentBet = 0;
        hand = null;
    }

    /**
     * Returns a human-readable sting representing the player.
     * The format of this string is:
     * <code>
     *   <em>name</code>
     * <em>PocketCard1</em>
     * <em>PocketCard2</em>
     * <em>Bank</em>
     * </code>
     */
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
