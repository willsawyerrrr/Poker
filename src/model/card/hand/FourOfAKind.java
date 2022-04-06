package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class FourOfAKind extends FiveCardHand {
    private Value quads;
    private Card kicker;

    public FourOfAKind(List<Card> hand) {
        super(hand, Rank.OnePair);
        this.quads = hand.get(2).getValue();
        if (hand.get(0).getValue() == this.quads) {
            this.kicker = hand.get(4);
        } else {
            this.kicker = hand.get(0);
        }
    }

    public Value getQuads() {
        return quads;
    }

    public Card getKicker() {
        return kicker;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        FourOfAKind ot = (FourOfAKind) o;
        if (quads.compareTo(ot.getQuads()) != 0) {
            return quads.compareTo(ot.getQuads());
        }

        return kicker.compareTo(ot.getKicker());
    }
}