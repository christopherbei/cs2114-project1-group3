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
    private String name;
    private Hand playerHand;

    public Player()
    {
        this(1000);
    }

    public Player(int balance)
    {
        playerHand = new Hand();
        this.balance = balance;
        this.name = "Player";
        this.bid = 0;
    }

    public int getBid()
    {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }


    public int getBalance()
    {
        return balance;
    }


    public void setBalance(int balance)
    {
        this.balance = balance;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
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
