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
        System.out.println(this.toString());

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
        String result = "Dealer:";
        System.out.println();
        if (dealerHand.size() == 2)
        {
            result += dealerHand.getCards().get(0);
            System.out.println();
            result += "*";
        }
        else
        {
            result += dealerHand.toString();
        }
        return result;
    }
}
