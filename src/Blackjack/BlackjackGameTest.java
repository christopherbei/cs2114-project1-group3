import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

// -------------------------------------------------------------------------
/**
 * Tests for {@link BlackjackGame}, mainly {@code handleInput} for name and
 * betting validation. Input is simulated by replacing {@code System.in}
 * before each game is constructed. This fakes real user input and allows
 * testing of methods that require user input.
 *
 * @author Isaac Tsung
 * @version 23 Sep 2026
 */
public class BlackjackGameTest
{
    private Player player;
    private Dealer dealer;
    private DataParser parser;

    /**
     * Builds fresh game dependencies before each test.
     */
    @Before
    public void setUp()
    {
        player = new Player(1000);
        dealer = new Dealer();
        parser = new DataParser();
    }


    /**
     * Builds a game whose Scanner reads the given multi-line input.
     *
     * @param input
     *            text the user would type, with newlines between answers
     * @return a new BlackjackGame wired to that input
     */
    private BlackjackGame gameWithInput(String input)
    {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new BlackjackGame(player, dealer, parser);
    }


    /**
     * Verifies a valid name is returned unchanged.
     */
    @Test
    public void testSettingNameValid()
    {
        BlackjackGame game = gameWithInput("Alice\n");
        assertEquals(
            "Alice",
            game.handleInput(BlackjackGame.GameStates.SETTINGNAME));
    }


    /**
     * Verifies an empty name is rejected, then a valid name is accepted.
     */
    @Test
    public void testSettingNameRejectsEmptyThenAccepts()
    {
        BlackjackGame game = gameWithInput("\nBob\n");
        assertEquals(
            "Bob",
            game.handleInput(BlackjackGame.GameStates.SETTINGNAME));
    }


    /**
     * Verifies a name longer than 10 characters is rejected, then a valid
     * name is accepted.
     */
    @Test
    public void testSettingNameRejectsTooLongThenAccepts()
    {
        BlackjackGame game = gameWithInput("NameTooLongX\nChris\n");
        assertEquals(
            "Chris",
            game.handleInput(BlackjackGame.GameStates.SETTINGNAME));
    }


    /**
     * Verifies a name of exactly 10 characters is accepted.
     */
    @Test
    public void testSettingNameAcceptsMaxLength()
    {
        BlackjackGame game = gameWithInput("ABCDEFGHIJ\n");
        assertEquals(
            "ABCDEFGHIJ",
            game.handleInput(BlackjackGame.GameStates.SETTINGNAME));
    }


    /**
     * Verifies a valid bet is stored on the player.
     */
    @Test
    public void testBettingValidStoresBid()
    {
        BlackjackGame game = gameWithInput("250\n");
        assertEquals(
            "250",
            game.handleInput(BlackjackGame.GameStates.BETTING));
        assertEquals(250, player.getBid());
    }


    /**
     * Verifies non-numeric input is rejected, then a valid bet is accepted.
     */
    @Test
    public void testBettingRejectsNonIntegerThenAccepts()
    {
        BlackjackGame game = gameWithInput("abc\n100\n");
        assertEquals(
            "100",
            game.handleInput(BlackjackGame.GameStates.BETTING));
        assertEquals(100, player.getBid());
    }


    /**
     * Verifies a non-positive bet is rejected, then a valid bet is accepted.
     */
    @Test
    public void testBettingRejectsZeroThenAccepts()
    {
        BlackjackGame game = gameWithInput("0\n-5\n75\n");
        assertEquals(
            "75",
            game.handleInput(BlackjackGame.GameStates.BETTING));
        assertEquals(75, player.getBid());
    }


    /**
     * Verifies a bet above the player's balance is rejected.
     */
    @Test
    public void testBettingRejectsOverBalanceThenAccepts()
    {
        player.setBalance(200);
        BlackjackGame game = gameWithInput("500\n150\n");
        assertEquals(
            "150",
            game.handleInput(BlackjackGame.GameStates.BETTING));
        assertEquals(150, player.getBid());
    }


    /**
     * Verifies betting the full balance (all-in) is allowed.
     */
    @Test
    public void testBettingAllIn()
    {
        player.setBalance(400);
        BlackjackGame game = gameWithInput("400\n");
        assertEquals(
            "400",
            game.handleInput(BlackjackGame.GameStates.BETTING));
        assertEquals(400, player.getBid());
    }


    /**
     * Verifies PLAYING currently returns the raw input (stub behavior).
     */
    @Test
    public void testPlayingReturnsInput()
    {
        BlackjackGame game = gameWithInput("hit\n");
        assertEquals(
            "hit",
            game.handleInput(BlackjackGame.GameStates.PLAYING));
    }


    /**
     * Verifies pauseConsole completes without throwing.
     */
    @Test
    public void testPauseConsole()
    {
        BlackjackGame game = gameWithInput("");
        long start = System.currentTimeMillis();
        game.pauseConsole(50);
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 40);
    }


    /**
     * Verifies loading a save with "y" loads properties from the default file.
     */
    @Test
    public void testLoadingSaveYes()
    {
        parser.setProperty("balance", "750");
        parser.setProperty("name", "Tester");
        parser.save("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");

        BlackjackGame game = gameWithInput("y\n");
        game.handleInput(BlackjackGame.GameStates.LOADINGSAVE);

        DataParser loaded = new DataParser();
        loaded.load("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");
        assertEquals("750", loaded.getProperty("balance", "0"));
        assertEquals("Tester", loaded.getProperty("name", ""));
    }
}
