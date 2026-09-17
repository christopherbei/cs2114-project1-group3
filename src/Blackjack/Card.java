
public class Card {
	private String suit;
	private String name;
	private int value;
	
	public Card(String suit, String name, int value) {
		this.suit = suit;
		this.name = name;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + " of " + suit;
	}
}
