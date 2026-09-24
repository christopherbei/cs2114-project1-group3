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

    /**
     * Creates a Player object with a default balance of 1000
     */
    public Player()
    {
        this(1000);
    }


    /**
     * @param balance
     *            creates a player object and sets the balance equal to the int
     *            parameter
     */
    public Player(int balance)
    {
        playerHand = new Hand();
        this.balance = balance;
        this.name = "Player";
        this.bid = 0;
    }


    /**
     * @return returns the bid in an int
     */
    public int getBid()
    {
        return bid;
    }


    /**
     * @param bid
     *            sets the bid to the int parameter
     */
    public void setBid(int bid)
    {
        this.bid = bid;
    }


    /**
     * @return the balance in the form of an int
     */
    public int getBalance()
    {
        return balance;
    }


    /**
     * @param balance
     *            sets the balance to the int parameter
     */
    public void setBalance(int balance)
    {
        this.balance = balance;
    }


    /**
     * @return name in the form of a string
     */
    public String getName()
    {
        return name;
    }


    /**
     * @param name
     *            in the form of a string, sets the name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * @return true if player busted and false if not
     */
    public boolean busted()
    {
        return playerHand.totalValue() > 21;
    }


    /**
     * @return the ArrayList of the hand object created in the player class
     */
    public Hand getHand()
    {
        return playerHand;
    }


    /**
     * @return the cards in the players hand in the form of a string
     */
    @Override
    public String toString()
    {
        String result = "Player:" + "/n";
        result += playerHand.toString();
        return result;
    }


    /**
     * @param card
     *            to be added to the player hand
     */
    public void addCard(Card card)
    {
        playerHand.addCard(card);
        System.out.println(this.toString());
    }

}
