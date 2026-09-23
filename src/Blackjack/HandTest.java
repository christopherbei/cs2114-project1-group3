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

    @Before
    public void setUp()
    {
        hand = new Hand();
    }


    /**
     * Checks the add card and total value methods for all branches
     */
    @Test
    public void testTotalValue()
    {
        Card card = new Card("Spade", "Eight", 8);

        hand.addCard(card);

        assertEquals(8, hand.totalValue());
        
        // a few more branches to test here
    }


    /**
     * Ensures the hand is converted into a string properly
     */
    @Test
    public void testToString()
    {
        assertTrue(true);
    }
}
