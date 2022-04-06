package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class ThreeOfAKind extends FiveCardHand {
    private Value trips;
    private List<Card> kickers;

    public ThreeOfAKind(List<Card> hand) {
        super(hand, Rank.OnePair);
        this.trips = hand.get(2).getValue();
    }

    public Value getTrips() {
        return trips;
    }

    public List<Card> getKickers() {
        return kickers;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        ThreeOfAKind ot = (ThreeOfAKind) o;
        if (trips.compareTo(ot.getTrips()) != 0) {
            return trips.compareTo(ot.getTrips());
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