
import java.util.Scanner;

public class BlackjackGame
{
    private Scanner input;
    private int currentBid;

    private Deck deck;
    private Player player;
    private Dealer dealer;

    public BlackjackGame()
    {
        input = new Scanner(System.in);
        currentBid = 1000;

        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }


    public void runCommand(String action)
    {
        // unused
    }


    public void pauseConsole(double duration)
    {
        // unused
    }


    public String handleInput()
    {
        boolean complete = false;
        String validActions = "";
        
        System.out.println("please enter your action: ");
        while (!complete)
        {
            String action = input.nextLine();
            action = action.toLowerCase();
            
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


    handleInput();
}
