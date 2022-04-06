package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class Straight extends FiveCardHand {
    private Value highestValue;

    public Straight(List<Card> hand) {
        super(hand, Rank.Straight);
        this.highestValue = hand.get(4).getValue();
    }

    public Value getHighestValue() {
        return highestValue;
    }

    @Override
    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        Straight ot = (Straight) o;
        return this.highestValue.compareTo(ot.getHighestValue());
    }
}