package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Suit;

import java.util.List;

public class Flush extends FiveCardHand {
    private Suit suit;

    public Flush(List<Card> hand) {
        super(hand, Rank.Straight);
        this.suit = hand.get(0).getSuit();
    }

    public Suit getSuit() {
        return suit;
    }

    public Card getNthCard(int n) {
        return getHand().get(4 - n);
    }

    @Override
    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        Flush ot = (Flush) o;
        for (int i = 1; i <= 5; i++) {
            int comparison = this.getNthCard(i).compareTo(ot.getNthCard(i));
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }
}