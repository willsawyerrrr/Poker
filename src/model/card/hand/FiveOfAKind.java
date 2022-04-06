package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class FiveOfAKind extends FiveCardHand {
    private Value value;

    public FiveOfAKind(List<Card> hand) {
        super(hand, Rank.OnePair);
        this.value = hand.get(0).getValue();
    }

    public Value getvalue() {
        return value;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        FiveOfAKind ot = (FiveOfAKind) o;
        if (value.compareTo(ot.getvalue()) != 0) {
            return value.compareTo(ot.getvalue());
        }

        return 0;
    }
}