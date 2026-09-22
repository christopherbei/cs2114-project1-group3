
public class Main {

	public static void main(String[] args) {
		// Initialize the game components.
		// Note: this could be simplified to BlackjackGame game = new BlackjackGame(new Player(), new Dealer(), new DataParser());
		Player player = new Player();
		Dealer dealer = new Dealer();
		DataParser parser = new DataParser();
		BlackjackGame game = new BlackjackGame(player, dealer, parser);

		// When the game is first started, a title screen is displayed in an introduction sequence.
		System.out.println("==================================================================");
		System.out.println("__________.__                 __         __               __    \r\n" + //
						"\\______   \\  | _____    ____ |  | __    |__|____    ____ |  | __\r\n" + //
						" |    |  _/  | \\__  \\ _/ ___\\|  |/ /    |  \\__  \\ _/ ___\\|  |/ /\r\n" + //
						" |    |   \\  |__/ __ \\\\  \\___|    <     |  |/ __ \\\\  \\___|    < \r\n" + //
						" |______  /____(____  /\\___  >__|_ \\/\\__|  (____  /\\___  >__|_ \\\r\n" + //
						"        \\/          \\/     \\/     \\/\\______|    \\/     \\/     \\/");
		System.out.println("==================================================================");
		game.runCommand("pause"); // Wait for input before continuing.
		game.runCommand("cls"); // Clear the console to prepare for the next screen.
		System.out.println("Welcome to Blackjack! The goal of the game is to get as close to 21 as possible without going over.");
		game.pauseConsole(1000); // Pause to allow the player to read the intro text.

		// The player is prompted to either load a save file or start a new game.
		if (parser.saveFileExists("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE")) {
			System.out.println("A save file was found, would you like to load it? (y/n)");
			game.handleInput(BlackjackGame.GameStates.LOADINGSAVE);
		}
		// If a save file does not exist, a new save file is created with default values for the player's balance and name.
		else
		{
			System.out.println("No save file was found, creating a new save...");
			game.pauseConsole(500);
			parser.setProperty("balance", "1000");
			parser.setProperty("name", "Player");
			parser.save();
			System.out.println("New save file created successfully.");
		}

		// Breaking this loop will result in immidiate termination of the program and is the intended way to exit the game.
		while (true) {
			
		}
	}

}
