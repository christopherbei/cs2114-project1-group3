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

    // represents the number of undrawn cards in the deck
    private int deckSize;
    // represents the number of cards currently in play
    private int activeCards;

    private ArrayList<Card> deck;

    /**
     * Constructor for new deck object
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
                deck.add(new Card(SUITS[i], NAMES[j], VALUES[j]));
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

        // randomly picks a card based on the deck size and active cards
        int random = (int)(Math.random() * (deckSize - activeCards));

        // most recently drawn cards are sent to the end of the arrayList
        Card card = deck.remove(random);
        deck.add(card);

        activeCards++;

        return card;
    }


    /**
     * Called when the current round ends, resets active cards and decrements
     * the deck size variable
     */
    public void turnOver()
    {
        deckSize -= activeCards;

        activeCards = 0;
    }
}
