public class Main {

	public static void main(String[] args) {
		// Initialize the game components.
		Deck deck = new Deck();
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
		game.pauseConsole(2000);
		game.runCommand("pause"); // Wait for input before continuing.
		game.runCommand("cls"); // Clear the console to prepare for the next screen.
		System.out.println("Welcome to Blackjack! The goal of the game is to get as close to 21 as possible without going over.");
		game.pauseConsole(1000); // Pause to allow the player to read the intro text.
		System.out.println("You can enter quit at any time during a round to exit the game.");
		game.pauseConsole(1000);
		System.out.println("Your balance will be saved, but you will lose any current bets.");
		game.pauseConsole(1000);
		game.runCommand("Pause");

		// The player is prompted to either load a save file or start a new game.
		if (parser.saveFileExists("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE"))
		{
			System.out.println("A save file was found, would you like to load it? (y/n)");
			game.handleInput(BlackjackGame.GameStates.LOADINGSAVE);
		}
		// Else, if a save file does not exist, a new save file is created with default values for the player's balance and name.
		else
		{
			System.out.println("No save file was found, creating a new save...");
			game.pauseConsole(1000);
			game.runCommand("cls"); // clear the console
			System.out.println("Enter player name: ");
			String chosenName = game.handleInput(BlackjackGame.GameStates.SETTINGNAME);
			parser.setProperty("balance", "1000"); // Default balance is 1000.
			parser.setProperty("name", chosenName); // Set the player's name to the chosen name.
			parser.save();


			game.pauseConsole(500);
			System.out.println("New save file created successfully.");
		}

		// After creating or loading the save file, match the player's fields to the save file's fields.
		player.setBalance(Integer.parseInt(parser.getProperty("balance", "1000")));
		player.setName(parser.getProperty("name", "Player"));

		// Breaking this loop will result in immidiate termination of the program.
		while (true) {
			System.out.println("Starting a new round...");
			game.pauseConsole(2000);
			game.runCommand("cls");
			System.out.println("Player balance: " + player.getBalance());
			System.out.println("Place your bet, " + player.getName());
			game.handleInput(BlackjackGame.GameStates.BETTING);
			game.pauseConsole(1000);

			System.out.println("Bid placed. Cards will now be drawn.");
			game.pauseConsole(1500);
			// By this point in execution, a valid bid has been made and stored in BlackjackGame.
			
			game.runCommand("cls");
			// Now the round actually starts- both the player and dealer each draw 2 initial cards.
			System.out.println("Player's drawn cards are as follows: ");
			player.addCard(deck.remove());
			player.addCard(deck.remove());
			game.pauseConsole(1000);

			System.out.println(); // 2 blank lines
			System.out.println();

			System.out.println("Dealer's drawn cards are as follows: ");
			dealer.addCard(deck.remove());
			dealer.addCard(deck.remove());
			game.pauseConsole(1000);

			// Player's turn: hit, stand, or quit.
			boolean playerBusted = false;
			boolean quitRound = false;

			while (true) {
				System.out.println("Enter your action (hit|stand|quit): ");
				String action = game.handleInput(BlackjackGame.GameStates.PLAYING);

				if (action.equals("quit")) {
					quitRound = true;
					break;
				}
				else if (action.equals("hit")) {
					player.addCard(deck.remove());
					if (player.busted()) {
						System.out.println("You busted!");
						playerBusted = true;
						break;
					}
				}
				else {
					// stand
					break;
				}
			}

			if (quitRound) {
				// Balance is kept; the current bet is forfeited per the intro text.
				parser.setProperty("balance", Integer.toString(player.getBalance()));
				parser.setProperty("name", player.getName());
				parser.save();
				System.out.println("Balance saved. Goodbye!");
				break;
			}

			if (playerBusted) {
				System.out.println("Dealer wins.");
				player.setBalance(player.getBalance() - player.getBid());
			}
			else {
				// Dealer's turn: reveal hole card, then draw until 17 or higher.
				System.out.println();
				System.out.println("Dealer reveals their hand:");
				System.out.println(dealer.toString());
				game.pauseConsole(1000);

				while (dealer.getHand().totalValue() < 17) {
					System.out.println("Dealer hits...");
					game.pauseConsole(750);
					dealer.addCard(deck.remove());
				}

				if (dealer.busted()) {
					System.out.println("Dealer busted. You win!");
					player.setBalance(player.getBalance() + player.getBid());
				}
				else if (player.getHand().totalValue() > dealer.getHand().totalValue()) {
					System.out.println("You win!");
					player.setBalance(player.getBalance() + player.getBid());
				}
				else if (player.getHand().totalValue() < dealer.getHand().totalValue()) {
					System.out.println("Dealer wins.");
					player.setBalance(player.getBalance() - player.getBid());
				}
				else {
					System.out.println("It's a tie.");
				}
			}

			System.out.println("Your balance is now: " + player.getBalance());
			player.setBid(0);
			player.getHand().clear();
			dealer.getHand().clear();
			game.pauseConsole(2000);

			parser.setProperty("balance", Integer.toString(player.getBalance()));
			parser.setProperty("name", player.getName());
			parser.save();
			deck = new Deck();
			game.runCommand("cls");

			if (player.getBalance() <= 0) {
				System.out.println("You're out of money! Game over.");
				break;
			}

		}
	}

}
