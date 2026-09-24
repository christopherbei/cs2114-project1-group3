import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests the Card class
 * 
 * @author Chris
 * @version 23 Sep 2026
 */
public class CardTest
{
    private Card card;
    private Card cardTwo;

    /**
     * Sets up new cards for each test
     */
    @Before
    public void setUp()
    {
        card = new Card("Hearts", "Five", 5);
        cardTwo = new Card("Diamonds", "Ace", 11);
    }


    /**
     * Tests the value returned is correct
     */
    @Test
    public void testGetValue()
    {
        assertEquals(5, card.getValue());
        assertEquals(11, cardTwo.getValue());
    }


    /**
     * Tests that the proper string is returned for the toString method
     */
    @Test
    public void testToString()
    {
        assertEquals("Five of Hearts", card.toString());
        assertEquals("Ace of Diamonds", cardTwo.toString());
    }
}
