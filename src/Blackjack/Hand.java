import java.util.*;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public int totalValue() {
		int aces = 0;
		int total = 0;
		
		for (Card card : hand) {
			total += card.getValue();
			
			if (card.getName() == "Ace") {
				aces++;
			}
		}
		
		while (total > 21 && aces > 0) {
			aces--;
			total -= 10;
		}
		
		return total;
	}
	
	public void clear() {
		hand = new ArrayList<Card>();
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (Card card : hand) {
			result += card + "\n";
		}
		
		return result;
	}
}
