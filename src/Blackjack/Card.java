
// -------------------------------------------------------------------------
/**
 * Stores data for a singular playing card
 * 
 * @author Chris
 * @version 17 Sep 2026
 */
public class Card
{
    private String suit;
    private String name;
    private int value;

    /**
     * @param suit
     * @param name
     * @param value
     */
    public Card(String suit, String name, int value)
    {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }


    /**
     * @return the value of the card
     */
    public int getValue()
    {
        return value;
    }


    /**
     * @return string representation of this card
     */
    @Override
    public String toString()
    {
        return name + " of " + suit;
    }
}
