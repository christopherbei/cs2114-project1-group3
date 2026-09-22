
import java.util.Scanner;

public class BlackjackGame
{
    private Scanner input;
    private int currentBid;
    private boolean inGame;

    private Deck deck;
    private Player player;
    private Dealer dealer;

    public BlackjackGame(Player player, Dealer dealer)
    {
        input = new Scanner(System.in);
        currentBid = 1000;
        inGame = false;

        deck = new Deck();
        this.player = player;
        this.dealer = dealer;
    }


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


    public void runCommand(String action)
    {
        // unused
    }


    public void pauseConsole(long duration)
    {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
