package src.poker.game;

import src.poker.card.Deck;
import src.poker.player.Rank;
import src.poker.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Game {
    /** The players in the current game. */
    private List<Player> players;
    /** The table corresponding to the current game. */
    private Table table;
    /** The deck being used in the current game. */
    private Deck deck;

    /**
     * Creates a new game which uses the given number of decks.
     * 
     * @param numDecks the number of decks to use during the game
     */
    public Game(Integer numDecks) {
        players = new ArrayList<Player>();
        table = new Table();
        deck = new Deck(numDecks);
    }

    /**
     * Returns the players in the current game.
     * 
     * @return the players in the current game
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the table corresponding to the current game.
     * 
     * @return the table corresponding to the current game
     */
    public Table getTable() {
        return table;
    }

    /**
     * Returns the table being used in the current game.
     * 
     * @return the table being used in the current game
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Adds the given player to the game.
     * 
     * @param player player to add to the game
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Deals two pocket cards to all players in the current hand.
     */
    public void deal() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.dealIn(deck.generateCard());
            }
        }
    }

    /**
     * Controls betting round. Returns whether all players have folded.
     * 
     * @param scanner scanner used to get terminal input
     * 
     * @return
     */
    public Boolean bettingRound(Scanner scanner) {
        Boolean canCheck = true;
        Boolean betsEven = false;
        Integer currentBet = 0;
        Integer remaining = players.size();
        Boolean allFolded = false;
        String checkPrompt = "Check, fold, bet or all-in? [C/F/B/A]";
        String otherPrompt = "Call, fold, raise or all-in? [C/F/R/A]";

        // return everyone's current bet to 0
        for (Player player : players) {
            player.resetBet();
        }
        // ask everyone for their action
        // if someone bets or goes all-in, no one after them can check
        // once everyone has checked or bets are even, end betting round

        while (!betsEven && remaining != 1) {
            for (Player player : players) {
                if (player.getInHand()) {
                    if (player.getCurrentBet() != currentBet || canCheck) {
                        String name = player.getName();
                        String prompt = canCheck ? checkPrompt : otherPrompt;
                        System.out.println(String.format("%s: %s", name, prompt));
                        String action = scanner.nextLine().toUpperCase();
                        switch (action) {
                            case "C":
                                if (!canCheck) {
                                    Integer bet = currentBet - player.getCurrentBet();
                                    if (player.bet(bet)) {
                                        table.addToPot(bet);
                                    }
                                }
                                break;
                            case "F":
                                player.fold();
                                break;
                            case "B":
                                System.out.print(String.format("%s: Enter your bet: ", name));
                                String amount = scanner.nextLine();
                                Integer bet = Integer.parseInt(amount);
                                if (player.bet(bet)) {
                                    table.addToPot(bet);
                                    currentBet = player.getCurrentBet();
                                }
                                canCheck = false;
                                break;
                            case "R":
                                System.out.print(String.format("%s: Enter your raise: ", name));
                                amount = scanner.nextLine();
                                Integer raise = Integer.parseInt(amount);
                                bet = currentBet - player.getCurrentBet() + raise;
                                if (player.bet(bet)) {
                                    table.addToPot(bet);
                                    currentBet = player.getCurrentBet();
                                }
                                canCheck = false;
                                break;
                            case "A":
                                bet = player.allIn();
                                table.addToPot(bet);
                                currentBet = player.getCurrentBet();
                                canCheck = false;
                                break;
                        }
                    } else {
                        betsEven = true;
                    }
                }
                remaining = 0;
                betsEven = true;
                for (Player x : players) {
                    if (x.getInHand()) {
                        remaining++;
                        if (x.getCurrentBet() != currentBet) {
                            betsEven = false;
                        }
                    }
                }
                if (remaining == 1) {
                    allFolded = true;
                    break;
                } else if (betsEven && !canCheck) {
                    break;
                }
            }
        }

        return allFolded;
    }

    /**
     * Asks for the player who won the hand.
     * 
     * @param scanner scanner used to get terminal input
     * 
     * @return the player who won the hand
     */
    public Player determineWinner(Scanner scanner) {
        System.out.print("Enter winner's name: ");
        String winnerName = scanner.nextLine();
        Player winner = null;
        for (Player player : players) {
            if (player.getName().equals(winnerName)) {
                winner = player;
            }
        }
        return winner;
    }

    /**
     * Initialises a new table and deck of cards for a new game.
     * Resets all players to begin a new game and rotates players
     * to share deal.
     * 
     * @param numDecks number of standard decks to use in the new game
     */
    public void newRound(Integer numDecks) {
        table = new Table();
        deck = new Deck(numDecks);
        for (Player player : players) {
            player.resetAll();
        }
        Player player = players.get(0);
        players.remove(player);
        players.add(player);
    }

    /**
     * Returns a human-readable string representing the game.
     * This string is formatted as:
     * <code>
     *   <em>Player1Name</em>:
     *     <em>Player1Card1</em>
     *     <em>Player1Card2</em>
     *   <em>Player2Name</em>:
     *     <em>Player2Card1</em>
     *     <em>Player2Card2</em>
     *   ...
     *   <em>PlayerNName</em>:
     *     <em>PlayerNCard1</em>
     *     <em>PlayerNCard2</em>
     * 
     *   Table:
     *     <em>FlopCard1</em>
     *     <em>FlopCard2</em>
     *     <em>FlopCard3</em>
     *   Pot:
     *     <em>Pot</em>
     * </code>
     * where <em>N</em> is the number of players in the game.
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Player player : players) {
            if (player.getInHand()) {
                joiner.add(player.toString());
            }
        }
        joiner.add("");
        joiner.add(table.toString());
        return joiner.toString();
    }

    public static void main(String[] args) {
        // Command line arguments should be: numPlayers numDecks
        // Defaults to 3 1
        Integer numPlayers;
        if (args.length > 0) {
            numPlayers = Integer.parseInt(args[0]);
        } else {
            numPlayers = 3;
        }

        Integer numDecks;
        if (args.length > 1) {
            numDecks = Integer.parseInt(args[1]);
        } else {
            numDecks = 1;
        }

        Game game = new Game(numDecks);
        String newHand = "Y";

        Scanner scanner = new Scanner(System.in);
        for (Integer i = 1; i <= numPlayers; i++) {
            System.out.print(String.format("Enter player %d's name: ", i));
            String name = scanner.nextLine();
            game.addPlayer(new Player(name));
        }

        Boolean allFolded = false;
        while (newHand.equals("Y") || newHand.equals("y")) {
            game.newRound(numDecks);
            Table table = game.getTable();
            Deck deck = game.getDeck();

            game.deal();
            System.out.println(game + "\n\n");
            allFolded = game.bettingRound(scanner);

            if (!allFolded) {
                table.generateFlop(deck);
                System.out.println(game + "\n\n");
                allFolded = game.bettingRound(scanner);
            }

            if (!allFolded) {
                table.generateTurn(deck);
                System.out.println(game + "\n\n");
                allFolded = game.bettingRound(scanner);
            }

            if (!allFolded) {
                table.generateRiver(deck);
                System.out.println(game + "\n\n");
                allFolded = game.bettingRound(scanner);
            }

            // Determine and pay winner
            Player winner = null;
            Rank winningRank = Rank.HighCard;
            if (!allFolded) {
                for (Player player : game.getPlayers()) {
                    if (player.getInHand()) {
                        player.evaluateHand(table);
                        if (player.getRank().ordinal() > winningRank.ordinal()) {
                            winner = player;
                            winningRank = player.getRank();
                        }
                    }
                }
            } else {
                for (Player player : game.getPlayers()) {
                    if (player.getInHand()) {
                        winner = player;
                    }
                }
            }
            table.payWinner(winner);
            System.out.println(String.format("%s wins!", winner.getName()));

            System.out.print("Want to play another hand? [Y/N] ");
            newHand = scanner.nextLine();
        }
        scanner.close();
    }
}