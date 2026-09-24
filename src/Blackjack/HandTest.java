import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests the Deck class
 * 
 * @author Chris
 * @version 23 Sep 2026
 */
public class HandTest
{
    private Hand hand;

    /**
     * Sets up a new hand for each test
     */
    @Before
    public void setUp()
    {
        hand = new Hand();
    }


    /**
     * Checks the add card, total value, and size methods for all branches
     */
    @Test
    public void testAddCard()
    {
        hand.addCard(new Card("Spade", "Eight", 8));

        assertEquals(8, hand.totalValue());
        assertEquals(1, hand.size());

        hand.addCard(new Card("Spade", "Ten", 10));
        hand.addCard(new Card("Spade", "Ace", 11));

        assertEquals(19, hand.totalValue());
        assertEquals(3, hand.size());

        hand.clear();

        assertEquals(0, hand.totalValue());
        assertEquals(0, hand.size());
    }


    /**
     * Ensures the hand is converted into a string properly
     */
    @Test
    public void testToString()
    {
        Card card1 = new Card("Spade", "King", 10);
        Card card2 = new Card("Heart", "Nine", 9);

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(
            card1.toString() + "\n" + card2.toString() + "\n",
            hand.toString());
    }
}
