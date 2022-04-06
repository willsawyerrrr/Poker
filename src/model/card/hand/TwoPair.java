package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class TwoPair extends FiveCardHand {
    private Value topPair;
    private Value bottomPair;
    private Card kicker;

    public TwoPair(List<Card> hand) {
        super(hand, Rank.OnePair);

        for (int i = 0; i < 5; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                if (this.topPair == null) {
                    this.topPair = hand.get(i).getValue();
                    break;
                }
                this.bottomPair = hand.get(i).getValue();
            } else if (hand.get(i).getValue() != hand.get(i - 1).getValue()) {
                this.kicker = hand.get(i);
            }
        }
    }

    public Value getTopPair() {
        return topPair;
    }

    public Value getBottomPair() {
        return bottomPair;
    }

    public Card getKicker() {
        return kicker;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        TwoPair ot = (TwoPair) o;
        if (topPair.compareTo(ot.getTopPair()) != 0) {
            return topPair.compareTo(ot.getTopPair());
        } else if (bottomPair.compareTo(ot.getBottomPair()) != 0) {
            return bottomPair.compareTo(ot.getBottomPair());
        }

        return kicker.compareTo(ot.getKicker());
    }
}