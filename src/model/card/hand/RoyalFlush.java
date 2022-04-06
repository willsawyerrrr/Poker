package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Suit;

import java.util.List;

public class RoyalFlush extends FiveCardHand {
    private Suit suit;

    public RoyalFlush(List<Card> hand) {
        super(hand, Rank.RoyalFlush);
        this.suit = hand.get(0).getSuit();
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        return 0;
    }
}
