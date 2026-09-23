
import java.util.Scanner;

public class BlackjackGame
{
    private Scanner input;
    private int currentBid;
    private boolean inGame;
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private DataParser parser;

    // Possible states of the game, which are used to determine what kind of input is expected from the player.
    public enum GameStates
    {
        // In this state, calling handleInput() will prompt the player to load a save file, or start a new game.
        LOADINGSAVE,
        // In this state, calling handleInput() will prompt the player to enter a bid amount.
        BETTING,
        // In this state, calling handleInput() will prompt the player to enter an action (hit|stand).
        PLAYING
    }

    public BlackjackGame(Player player, Dealer dealer, DataParser parser)
    {
        input = new Scanner(System.in);
        currentBid = 1000;
        inGame = false;

        deck = new Deck();
        this.player = player;
        this.dealer = dealer;
        this.parser = parser;
    }

    /* 
    public String handleInput()
    {

        boolean complete = false;
        
        if (inGame) {
            System.out.println("please enter your action (hit|stand): ");
        }else {
            System.out.println("please enter your bid: ");
        }
        
        while (!complete)
        {
            String action = input.nextLine();
            action = action.toLowerCase();
            if (inGame) {
                // check bid amount
            }else {
                switch (action)
                {
                    case "hit":
                        player.addCard(deck.remove());
                        complete = true;
                        break;
                    case "stand":
                        // implement
                        complete = true;
                        break;
                    case "stop":
                        // implement
                        complete = true;
                        break;
                    default:
                        System.out.println("invalid input, try again: ");
                }   
            }
        }
    }
    */

    public String handleInput(GameStates gameState)
    {
        switch (gameState)
        {
            // Preconditions for this branch: A save file exists.
            case LOADINGSAVE:
                String userInput = input.nextLine();
                // Brief delay before responding to the user input.
                pauseConsole(500);

                if (userInput.equals("y"))
                {
                    parser.load();
                    player.balance = Integer.parseInt(parser.getProperty("balance", "1000"));
                    player.name = parser.getProperty("name", "Player");
                    System.out.println("Save file loaded successfully.");
                    return userInput;
                }
                else if (userInput.equals("n"))
                {
                    System.out.println("Creating a new save...");
                    pauseConsole(500);
                    parser.setProperty("balance", "1000");
                    parser.setProperty("name", "Player");
                    parser.save();
                    System.out.println("New save file created successfully.");
                    return userInput;
                }
                else
                {
                    System.out.println("Invalid input, please enter 'y' or 'n': ");
                    handleInput(gameState);
                }
                break;

            case BETTING:
                // implement
                break;

            case PLAYING:
                // implement
                break;

            default:
                // as long as a GameState is passed to this method, this exception should never be thrown.
                throw new IllegalArgumentException("Invalid game state: " + gameState);
        }
        return null;
    }


    /** 
     * Runs a command in the console, and waits for it to finish before continuing execution.
     * @param action
     *          The command to run in the console.
     *          Some useful commands are:
     *              *  pause - pauses the console until a key is pressed.
     *              *  cls - clears the console. (useful for clearing junk)
     *              *  echo [text] - equivalent to System.out.println(text)
     */
    public void runCommand(String action)
    {
        // Build a new cmd process with the given command, with /c closing the process after
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
     * Pauses execution by blocking the meain thread for the given duration in milliseconds.
     * @param duration
     *        The duration to pause execution for, in milliseconds.
     */
    public void pauseConsole(long duration)
    {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
