import java.util.*;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 * 
 * @author adambergmaier
 * @version Sep 24, 2026
 */
public class Dealer
{
    private Hand dealerHand;

    /**
     * creates the dealer object and dealerHand object
     */
    public Dealer()
    {
        dealerHand = new Hand();
    }


    /**
     * @param card
     *            to be added to dealers hand
     */
    public void addCard(Card card)
    {

        dealerHand.addCard(card);
        if (dealerHand.size() <= 1)
        {
            System.out.println(this.toString() + "Hole Card" + "\n" + "\n");
        }
        else if (dealerHand.size() > 2)
        {
            System.out.println(this.toString());
        }

    }


    /**
     * @return the ArrayList of the hand object created in the Dealer class.
     */
    public Hand getHand()
    {
        return dealerHand;
    }


    /**
     * @return true if dealer busted and false if not
     */
    public boolean busted()
    {
        return dealerHand.totalValue() > 21;
    }


    @Override
    public String toString()
    {
        String result = new String("");

        result += dealerHand.toString();

        return result;
    }


    /**
     * @return the dealers hand after the player stands
     */
    public String reveal()
    {
        String result = new String("");
        result += dealerHand.toString();
        return result;
    }
}
