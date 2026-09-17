import java.util.*;

public class Deck {
	private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static final String[] NAMES = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
		
	private int deckSize;
	private int activeCards;
	
	private ArrayList<Card> deck;
	
	public Deck() {
		deckSize = 52;
		activeCards = 0;
		
		deck = new ArrayList<Card>();
		
		for (int i = 0; i < SUITS.length; i++) {
			for (int j = 0; j < NAMES.length; j++) {
				deck.add(new Card(SUITS[i], NAMES[j], VALUES[i]));
			}
		}
	}
	
	public Card remove() {
		if (deckSize - activeCards == 0) {
			deckSize = 52;
		}
		
		Random rand = new Random();
		int random = rand.nextInt(deckSize - activeCards);
				
		Card card = deck.get(random);
		deck.set(random, deck.get(deckSize - activeCards - 1));
		deck.set(deckSize - activeCards - 1, card);
		
		activeCards++;
		
		return card;
	}
	
	public void turnOver() {
		deckSize -= activeCards;
		
		activeCards = 0;
	}
}
