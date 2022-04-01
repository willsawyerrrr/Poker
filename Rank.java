public enum Rank {
    /** Nothing. */
    HighCard,
    /** Two cards of the same value. */
    Pair,
    /** Two cards of the same value, two of another. */
    TwoPair,
    /** Three cards of the same value. */
    ThreeOfAKind,
    /** Five cards of consecutive value. */
    Straight,
    /** Five cards of the same suit. */
    Flush,
    /** Three cards of the same value, two of another. */
    FullHouse,
    /** Four cards of the same value. */
    FourOfAKind,
    /** Five cards of consecutive value, all of the same suit */
    StraightFlush,
    /** Ace, King, Queen, Jack and Ten of the same suit. */
    RoyalFlush
}