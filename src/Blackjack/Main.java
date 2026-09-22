
public class Main {

	public static void main(String[] args) {
		// Begin initialization.
		Player player = new Player();
		Dealer dealer = new Dealer();
		DataParser parser = new DataParser();
		BlackjackGame game = new BlackjackGame(player, dealer);

		// When the game is first started, a title screen is displayed in an introduction sequence.
		System.out.println("==================================================================");
		System.out.println("__________.__                 __         __               __    \r\n" + //
						"\\______   \\  | _____    ____ |  | __    |__|____    ____ |  | __\r\n" + //
						" |    |  _/  | \\__  \\ _/ ___\\|  |/ /    |  \\__  \\ _/ ___\\|  |/ /\r\n" + //
						" |    |   \\  |__/ __ \\\\  \\___|    <     |  |/ __ \\\\  \\___|    < \r\n" + //
						" |______  /____(____  /\\___  >__|_ \\/\\__|  (____  /\\___  >__|_ \\\r\n" + //
						"        \\/          \\/     \\/     \\/\\______|    \\/     \\/     \\/");
		System.out.println("==================================================================");


		if (parser.saveFileExists("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE")) {
			
		}

		// Breaking this loop will result in immidiate termination of the program and is the intended way to exit the game.
		while (true) {
			
		}
	}

}
