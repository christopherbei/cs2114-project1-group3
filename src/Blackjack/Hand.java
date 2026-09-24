import java.util.*;

// -------------------------------------------------------------------------
/**
 * Stores cards for a player / dealer with methods to total and print them
 * 
 * @author Chris
 * @version 17 Sep 2026
 */
public class Hand
{
    private ArrayList<Card> hand;

    /**
     * Constructor for a new hand object
     */
    public Hand()
    {
        hand = new ArrayList<Card>();
    }


    /**
     * @param card to add
     */
    public void addCard(Card card)
    {
        hand.add(card);
    }


    /**
     * @return ArrayList of the hand object
     */
    public ArrayList<Card> getCards()
    {
        return hand;
    }


    /**
     * @return number of cards in hand
     */
    public int size()
    {
        return hand.size();
    }


    /**
     * @return total value of cards in hand
     */
    public int totalValue()
    {
        int aces = 0;
        int total = 0;

        for (Card card : hand)
        {
            total += card.getValue();

            if (card.getValue() == 11)
            {
                aces++;
            }
        }

        while (total > 21 && aces > 0)
        {
            aces--;
            total -= 10;
        }

        return total;
    }


    /**
     * resets entire hand
     */
    public void clear()
    {
        hand.clear();
    }


    /**
     * @return string representation of hand
     */
    @Override
    public String toString()
    {
        String result = "";

        for (Card card : hand)
        {
            result += card + "\n";
        }

        return result;
    }
}
