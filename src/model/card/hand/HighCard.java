package src.model.card.hand;

import src.model.card.Card;

import java.util.List;

public class HighCard extends FiveCardHand {

    public HighCard(List<Card> hand) {
        super(hand, Rank.HighCard);
    }

    public Card getNthCard(int n) {
        return getHand().get(4 - n);
    }

    @Override
    public int compareTo(FiveCardHand o) {
        if (super.compareTo(o) != 0) {
            return super.compareTo(o);
        }

        HighCard ot = (HighCard) o;
        for (int i = 1; i <= 5; i++) {
            int comparison = this.getNthCard(i).compareTo(ot.getNthCard(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        
        return 0;
    }
}
