import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Controls Blackjack game flow: reading player input, validating it by phase,
 * and coordinating the {@link Player}, {@link Dealer}, {@link Deck}, and
 * {@link DataParser}.
 * <p>
 * Call {@link #handleInput(GameStates)} with the current {@link GameStates}
 * value so the game knows what kind of input to expect (load save, set name,
 * place a bet, or play a hand). Invalid input is rejected and the player is
 * prompted again until a valid value is entered.
 * </p>
 *
 * @author Isaac Tsung
 * @version 23 Sep 2026
 */
public class BlackjackGame {
    /**
     * Reads lines from standard input for {@link #handleInput(GameStates)}.
     */
    private Scanner input;

    /**
     * Shared deck used when dealing cards during play.
     */
    private Deck deck;

    /**
     * The human player whose balance, bid, and hand this game updates.
     */
    private Player player;

    /**
     * The dealer opponent for the current session.
     */
    private Dealer dealer;

    /**
     * Loads and saves player data such as name and balance.
     */
    private DataParser parser;

    /**
     * Phases of the game that decide how {@link #handleInput(GameStates)}
     * interprets the next line of player input.
     */
    public enum GameStates {
        /**
         * Expects {@code y} or {@code n} to load an existing save or create a
         * new one. Preconditions: a save file already exists.
         */
        LOADINGSAVE,

        /**
         * Expects a player name of 1–10 characters and returns the validated
         * name.
         */
        SETTINGNAME,

        /**
         * Expects a positive whole-number bet that does not exceed the
         * player's balance; stores the bet on the player when valid.
         */
        BETTING,

        /**
         * Expects a play action such as hit or stand during a round.
         */
        PLAYING
    }

    /**
     * Creates a game wired to the given player, dealer, and save parser.
     * <p>
     * A new {@link Deck} is created and input is read from {@code System.in}.
     * </p>
     *
     * @param player
     *            the player taking part in this session
     * @param dealer
     *            the dealer for this session
     * @param parser
     *            the parser used to load and save game data
     */
    public BlackjackGame(Player player, Dealer dealer, DataParser parser) {
        input = new Scanner(System.in);

        deck = new Deck();
        this.player = player;
        this.dealer = dealer;
        this.parser = parser;
    }

    /*
     * public String handleInput()
     * {
     * 
     * boolean complete = false;
     * 
     * if (inGame) {
     * System.out.println("please enter your action (hit|stand): ");
     * }else {
     * System.out.println("please enter your bid: ");
     * }
     * 
     * while (!complete)
     * {
     * String action = input.nextLine();
     * action = action.toLowerCase();
     * if (inGame) {
     * // check bid amount
     * }else {
     * switch (action)
     * {
     * case "hit":
     * player.addCard(deck.remove());
     * complete = true;
     * break;
     * case "stand":
     * // implement
     * complete = true;
     * break;
     * case "stop":
     * // implement
     * complete = true;
     * break;
     * default:
     * System.out.println("invalid input, try again: ");
     * }
     * }
     * }
     * }
     */

    /**
     * Reads one line from the console and processes it for the given game
     * phase.
     * <p>
     * Behavior depends on {@code gameState}:
     * </p>
     * <ul>
     * <li>{@link GameStates#LOADINGSAVE} — {@code y} loads the default save;
     * {@code n} prompts for a name and creates a new save; anything else
     * re-prompts.</li>
     * <li>{@link GameStates#SETTINGNAME} — rejects empty names and names
     * longer than 10 characters; returns the validated name.</li>
     * <li>{@link GameStates#BETTING} — requires a positive integer less than
     * or equal to the player's balance; stores the bet with
     * {@link Player#setBid(int)}.</li>
     * <li>{@link GameStates#PLAYING} — currently returns the raw input
     * (hit/stand logic not yet implemented).</li>
     * </ul>
     * Invalid input for a phase re-prompts by calling this method again until
     * a valid value is obtained.
     *
     * @param gameState
     *            the current phase that defines how input is validated
     * @return the accepted input string for that phase (for example the
     *         chosen name or bet amount as text)
     * @throws IllegalArgumentException
     *             if {@code gameState} is not a recognized {@link GameStates}
     *             value
     */
    public String handleInput(GameStates gameState) {
        // First we cache the user Input to be returned later.
        String userInput = input.nextLine();

        switch (gameState) {

            // Preconditions for this branch: A save file exists.
            case LOADINGSAVE:
                // Brief delay before responding to the user input.
                pauseConsole(500);

                if (userInput.equals("y")) {
                    parser.load();
                    System.out.println("Save file loaded successfully.");
                } else if (userInput.equals("n")) {
                    System.out.println("Creating a new save...");
                    pauseConsole(1000);

                    runCommand("cls");
                    System.out.println("Enter player name: ");
                    String playerChosenName = handleInput(GameStates.SETTINGNAME);
                    parser.setProperty("balance", "1000");
                    parser.setProperty("name", playerChosenName);
                    parser.save();

                    pauseConsole(500);
                    System.out.println("New save file created successfully.");
                } else {
                    System.out.println("Invalid input, please enter 'y' or 'n': ");
                    handleInput(gameState);
                }
                break;

            case SETTINGNAME:
                if (userInput.length() > 10) {
                    runCommand("cls");
                    System.out.println("Player name must be 10 characters or less.");
                    System.out.println("Enter player name: ");
                    userInput = handleInput(GameStates.SETTINGNAME);
                } else if (userInput.length() == 0) {
                    runCommand("cls");
                    System.out.println("Player name cannot be empty!");
                    System.out.println("Enter player name: ");
                    userInput = handleInput(GameStates.SETTINGNAME);
                }
                break;

            case BETTING:
                // Validate that the bet is a clean int, then check it against the player's balance.
                int bet;
                try {
                    bet = Integer.parseInt(userInput.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Player balance: " + player.getBalance());
                    System.out.println("Please enter a valid whole number:");
                    userInput = handleInput(GameStates.BETTING);
                    break;
                }

                if (bet <= 0) {
                    System.out.println("Player balance: " + player.getBalance());
                    System.out.println("Bet must be greater than 0:");
                    userInput = handleInput(GameStates.BETTING);
                    break;
                }

                if (bet > player.getBalance()) {
                    System.out.println("You cannot bet more than you have!");
                    System.out.println("Player balance: " + player.getBalance());
                    System.out.println("Place your bet, " + player.getName() + ":");
                    userInput = handleInput(GameStates.BETTING);
                    break;
                }

                if (bet == player.getBalance()) {
                    System.out.println("Going all in!");
                }

                player.setBid(bet);
                break;

            case PLAYING:
                // implement
                break;

            default:
                // as long as a GameState is passed to this method, this exception should never
                // be thrown.
                throw new IllegalArgumentException("Invalid game state: " + gameState);
        }
        return userInput;
    }

    /**
     * Runs a Windows console command and waits for it to finish before
     * continuing.
     * <p>
     * Useful commands include:
     * </p>
     * <ul>
     * <li>{@code pause} — waits until a key is pressed</li>
     * <li>{@code cls} — clears the console</li>
     * <li>{@code echo [text]} — prints text (like
     * {@link System#out}{@code println})</li>
     * </ul>
     *
     * @param action
     *            the command string passed to {@code cmd.exe /c}
     */
    public void runCommand(String action) {
        // Build a new cmd process with the given command, with /c closing the process
        // after
        // the command is executed.
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", action);
        // Bind the new process' I/O streams to the current .jar java process'
        // streams. (Ensure they use the same console.)
        processBuilder = processBuilder.inheritIO();
        // Starting the process runs the command.
        try {
            Process newProcess = processBuilder.start();
            // Waits for the process to finish it's command before continuing.
            newProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Blocks the current thread for the given duration so console text can be
     * read before the next output appears.
     *
     * @param duration
     *            pause length in milliseconds
     */
    public void pauseConsole(long duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
