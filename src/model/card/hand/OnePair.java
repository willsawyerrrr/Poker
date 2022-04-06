package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.ArrayList;
import java.util.List;

public class OnePair extends FiveCardHand {
    private Value pair;
    private List<Card> kickers;

    public OnePair(List<Card> hand) {
        super(hand, Rank.OnePair);
        kickers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                this.pair = hand.get(i).getValue();
            } else if (i == 0 ||
                    hand.get(i).getValue() != hand.get(i - 1).getValue()) {
                kickers.add(hand.get(i));
            }
        }
    }

    public Value getPair() {
        return pair;
    }

    public List<Card> getKickers() {
        return kickers;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        OnePair ot = (OnePair) o;
        if (pair.compareTo(ot.getPair()) != 0) {
            return pair.compareTo(ot.getPair());
        }
        List<Card> otKickers = ot.getKickers();
        for (int i = 0; i < 3; i++) {
            if (kickers.get(i).compareTo(otKickers.get(i)) != 0) {
                return kickers.get(i).compareTo(otKickers.get(i));
            }
        }

        return 0;
    }
}