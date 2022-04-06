package src.model.card.hand;

import src.model.card.Card;
import src.model.card.Value;

import java.util.List;

public class FullHouse extends FiveCardHand {
    private Value trips;
    private Value pair;

    public FullHouse(List<Card> hand) {
        super(hand, Rank.OnePair);
        this.trips = hand.get(2).getValue();
        if (hand.get(0).getValue() == this.trips) {
            this.pair = hand.get(4).getValue();
        } else {
            this.pair = hand.get(0).getValue();
        }
    }

    public Value getTrips() {
        return trips;
    }

    public Value getPair() {
        return pair;
    }

    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }
        
        FullHouse ot = (FullHouse) o;
        if (trips.compareTo(ot.getTrips()) != 0) {
            return trips.compareTo(ot.getTrips());
        } else if (pair.compareTo(ot.getPair()) != 0) {
            return pair.compareTo(ot.getPair());
        }

        return 0;
    }
}