import java.util.*;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 * 
 * @author adambergmaier
 * @version Sep 21, 2026
 */
public class Player
{

    private int balance;
    private int bid;
    private Hand playerHand;

    public Player(int balance)
    {
        playerHand = new Hand();
        this.balance = balance;
    }


    public int getBalance()
    {
        return balance;
    }


    public boolean busted()
    {
        return playerHand.totalValue() > 21;
    }


    public void addCard(Card card)
    {
        playerHand.addCard(card);
        System.out.println(playerHand.toString());
    }

}
