package src.poker.game;

import src.poker.card.Card;
import src.poker.card.Deck;
import src.poker.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Table {
    /** The first three community cards. */
    private List<Card> flop;
    /** The fourth community card. */
    private Card turn;
    /** The fifth and final community card. */
    private Card river;
    /** The amount of money to be won. */
    private Integer pot;

    /**
     * Creates a new table for a new hand.
     */
    public Table() {
        flop = new ArrayList<Card>(3);
        turn = null;
        river = null;
        pot = 0;
    }

    /**
     * Returns the flop.
     * 
     * @return the flop
     */
    public List<Card> getFlop() {
        return flop;
    }

    /**
     * Returns the turn.
     * 
     * @return the turn
     */
    public Card getTurn() {
        return turn;
    }

    /**
     * Returns the river.
     * 
     * @return the river
     */
    public Card getRiver() {
        return river;
    }

    /**
     * Returns the pot.
     * 
     * @return the pot
     */
    public Integer getPot() {
        return pot;
    }

    /**
     * Returns three cards from the given deck and adds them to the flop.
     * 
     * @param deck the deck from which to obtain cards
     */
    public void generateFlop(Deck deck) {
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
        flop.add(deck.generateCard());
    }

    /**
     * Returns one card from the given deck and assigns it to the turn.
     * 
     * @param deck the deck from which to obtain cards
     */
    public void generateTurn(Deck deck) {
        turn = deck.generateCard();
    }

    /**
     * Returns one card from the given deck and assigns it to the river.
     * 
     * @param deck the deck from which to obtain cards
     */
    public void generateRiver(Deck deck) {
        river = deck.generateCard();
    }

    /**
     * Increases the pot by the given amount.
     * 
     * @param bet the amount of money to add to the pot
     */
    public void addToPot(Integer bet) {
        pot += bet;
    }

    /**
     * Gives the money in the pot to the winner.
     * 
     * @param winner the player to give the money to
     */
    public void payWinner(Player winner) {
        winner.collectWinnings(pot);
    }

    /**
     * Returns a human-readable string representing the table.
     * This string is formatted as:
     * <code>
     *   Table:
     *     <em>FlopCard1</em>
     *     <em>FlopCard2</em>
     *     <em>FlopCard3</em>
     *     <em>TurnCard</em>
     *     <em>RiverCard</em>
     *   Pot:
     *     <em>Pot</em>
     * </code>.
     * 
     * @return the human-readable string representation of the table
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        if (!flop.isEmpty()) {
            joiner.add("Table:");
            for (Card card : flop) {
                joiner.add(String.format("  %s", card));
            }
        }
        if (turn != null) {
            joiner.add(String.format("  %s", turn));
        }
        if (river != null) {
            joiner.add(String.format("  %s", river));
        }
        joiner.add("Pot:");
        joiner.add(String.format("  %d", pot));
        return joiner.toString();
    }
}
