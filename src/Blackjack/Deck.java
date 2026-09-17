import java.util.*;

// -------------------------------------------------------------------------
/**
 * Keeps track and draws from all available cards throughout the game
 * 
 * @author Chris
 * @version 17 Sep 2026
 */
public class Deck
{
    private static final String[] SUITS =
        { "Spades", "Hearts", "Diamonds", "Clubs" };
    private static final String[] NAMES =
        { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Jack", "Queen", "King", "Ace" };
    private static final int[] VALUES =
        { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

    private int deckSize;
    private int activeCards;

    private ArrayList<Card> deck;

    /**
     * 
     */
    public Deck()
    {
        deckSize = 52;
        activeCards = 0;

        deck = new ArrayList<Card>();

        for (int i = 0; i < SUITS.length; i++)
        {
            for (int j = 0; j < NAMES.length; j++)
            {
                deck.add(new Card(SUITS[i], NAMES[j], VALUES[i]));
            }
        }
    }


    /**
     * @return a random card that isn't currently in play
     */
    public Card remove()
    {
        if (deckSize - activeCards == 0)
        {
            deckSize = 52;
        }

        int random = (int)(Math.random() * (deckSize - activeCards));

        Card card = deck.remove(random);
        deck.add(card);

        activeCards++;

        return card;
    }


    /** 
     * 
     */
    public void turnOver()
    {
        deckSize -= activeCards;

        activeCards = 0;
    }
}
