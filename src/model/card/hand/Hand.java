package src.model.card.hand;

import src.model.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a seven-card hand formed out of a player's pocket cards and the
 * community cards on the table.
 */
public class Hand {
    /** The player's pocket cards and the community cards. */
    private List<Card> cards;
    /** The player's best five-card hand. */
    private FiveCardHand bestHand;
    /** The rank of the player's best five-card hand. */
    private Rank rank;

    /**
     * Creates a new empty hand.
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * Adds the given card to the hand.
     * 
     * @param card card to add to the hand
     */
    public void addCard(Card card) {
        cards.add(card);
        cards.sort((c1, c2) -> -1 * c1.getValue().compareTo(c2.getValue()));
    }

    /**
     * Adds the given cards to the hand.
     * 
     * @param cards cards to add to the hand
     */
    public void addAllCards(List<Card> cards) {
        cards.addAll(cards);
        cards.sort((c1, c2) -> -1 * c1.getValue().compareTo(c2.getValue()));
    }

    /**
     * Returns the player's best five-card hand.
     * 
     * @return the player's best five-card hand
     */
    public FiveCardHand getBestHand() {
        return bestHand;
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
     * Evaluates the highest five-card hand and its rank.
     */
    public void evaluateHand() {
        List<Card> temp;
        List<Card> hand = new ArrayList<Card>(cards);
        Rank bestRank = Rank.HighCard;
        for (Card card1 : cards) {
            for (Card card2 : cards) {
                if (card1 != card2) {
                    temp = new ArrayList<Card>(cards);
                    temp.remove(card1);
                    temp.remove(card2);
                    Rank rank = FiveCardHand.evaluateFiveCardHand(temp);
                    if (rank.compareTo(bestRank) > 0) {
                        bestRank = rank;
                        hand = temp;
                        System.out.println(hand);
                    }
                }
            }
        }

        System.out.println(hand);

        switch (bestRank) {
            case HighCard:
                this.bestHand = new HighCard(hand);
            case OnePair:
                this.bestHand = new OnePair(hand);
            case TwoPair:
                this.bestHand = new TwoPair(hand);
            case ThreeOfAKind:
                this.bestHand = new ThreeOfAKind(hand);
            case Straight:
                this.bestHand = new Straight(hand);
            case Flush:
                this.bestHand = new Flush(hand);
            case FullHouse:
                this.bestHand = new FullHouse(hand);
            case FourOfAKind:
                this.bestHand = new FourOfAKind(hand);
            case StraightFlush:
                this.bestHand = new StraightFlush(hand);
            case RoyalFlush:
                this.bestHand = new RoyalFlush(hand);
            case FiveOfAKind:
                this.bestHand = new FiveOfAKind(hand);
        }
        this.rank = bestRank;
    }
}
