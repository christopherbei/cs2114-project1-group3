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
public class DeckTest
{
    private Deck deck;

    @Before
    public void setUp()
    {
        deck = new Deck();
    }


    /**
     * Checks that no repeat cards are drawn in the first 52
     */
    @Test
    public void testRemove()
    {
        String[] cards = new String[52];

        for (int i = 0; i < 51; i++)
        {
            String newCard = deck.remove().toString();

            for (int j = i - 1; j > 0; j--)
            {
                assertFalse(cards[j].equals(newCard));
            }

            cards[i] = newCard;
        }
    }

}
