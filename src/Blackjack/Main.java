
public class Main {

	public static void main(String[] args) {
		// Begin initialization.
		Player player = new Player();
		Dealer dealer = new Dealer();
		DataParser parser = new DataParser();
		BlackjackGame game = new BlackjackGame(player, dealer);

		// Breaking this loop will result in immidiate termination of the program and is the intended way to exit the game.
		while (true) {
			
		}
	}

}
