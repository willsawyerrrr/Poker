import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Game {
    private List<Player> players;
    private Table table;
    private Deck deck;

    public Game(Integer numDecks) {
        players = new ArrayList<Player>();
        table = new Table();
        deck = new Deck(numDecks);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Table getTable() {
        return table;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void deal() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.dealIn(deck.generateCard());
            }
        }
    }

    public void bettingRound(Scanner scanner) {
        // Ask for bets
        Boolean canCheck = true;
        Integer currentBet = 0;
        String checkPrompt = "check, fold, bet or all-in? [C/F/B/A]";
        String otherPrompt = "call, fold, bet or all-in? [C/F/B/A]";
        for (Player player : players) {
            if (player.getInHand()) {
                String name = player.getName();
                String prompt = canCheck ? checkPrompt : otherPrompt;
                System.out.println(String.format("%s, %s", name, prompt));
                String action = scanner.nextLine().toUpperCase();
                switch (action) {
                    case "F":
                        player.fold();
                        break;
                    case "C":
                        if (!canCheck) {
                            Integer bet = currentBet - player.getCurrentBet();
                            if (player.bet(bet)) {
                                table.addToPot(bet);
                            }
                        }
                        break;
                    case "B":
                        System.out.print(String.format("%s, enter your bet: ", name));
                        String amount = scanner.nextLine();
                        Integer bet = Integer.parseInt(amount);
                        if (player.bet(bet)) {
                            table.addToPot(bet);
                            currentBet = player.getCurrentBet();
                        }
                        canCheck = false;
                        break;
                    case "A":
                        bet = player.allIn();
                        table.addToPot(bet);
                        currentBet = (bet > currentBet) ? bet : currentBet;
                        canCheck = false;
                }
            }
        }
        // set minimum to call based off previous bets
        // exit when even
    }

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

    public void newRound(Integer numDecks) {
        table = new Table();
        deck = new Deck(numDecks);
        for (Player player : players) {
            player.reset();
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

        while (newHand.equals("Y") || newHand.equals("y")) {
            game.newRound(numDecks);
            Table table = game.getTable();
            Deck deck = game.getDeck();

            game.deal();
            System.out.println(game + "\n\n");
            game.bettingRound(scanner);

            table.generateFlop(deck);
            System.out.println(game + "\n\n");
            game.bettingRound(scanner);

            table.generateTurn(deck);
            System.out.println(game + "\n\n");
            game.bettingRound(scanner);

            table.generateRiver(deck);
            System.out.println(game + "\n\n");
            game.bettingRound(scanner);

            // Determine and pay winner
            Player winner = game.determineWinner(scanner);
            table.payWinner(winner);

            System.out.print("Want to play another hand? [Y/N] ");
            newHand = scanner.nextLine();
        }
        scanner.close();
    }
}