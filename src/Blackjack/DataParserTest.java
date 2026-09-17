import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link DataParser}.
 *
 * @author Group 3
 * @version 1.0
 */
public class DataParserTest {

    private DataParser parser;

    /**
     * Sets up a parser before each test.
     */
    @Before
    public void setUp() {
        parser = new DataParser();
    }

    /**
     * Verifies that a property set on the parser can be retrieved.
     */
    @Test
    public void testSetAndGetProperty() {
        parser.setProperty("wins", "5");
        assertEquals("5", parser.getProperty("wins", "0"));
    }


    /**
     * Verifies that the fallback is returned when a key is missing.
     */
    @Test
    public void testGetPropertyFallback() {
        assertEquals("0", parser.getProperty("missing", "0"));
    }


    /**
     * Verifies that properties survive a Save then Load round-trip.
     */
    @Test
    public void testSaveAndLoad() {
        parser.setProperty("playerName", "Alice");
        parser.setProperty("score", "21");
        parser.save("testSaveFile.txt");

        DataParser loaded = new DataParser();
        loaded.load("testSaveFile.txt");

        assertEquals("Alice", loaded.getProperty("playerName", ""));
        assertEquals("21", loaded.getProperty("score", "0"));
    }

    /**
     * Verifies that the parameter-less save and load methods function.
     */
    @Test 
    public void testSaveAndLoadParameterLess() {
        parser.setProperty("playerName", "Alice");
        parser.setProperty("score", "21");
        parser.save();

        parser.load();

        assertEquals("Alice", parser.getProperty("playerName", ""));
        assertEquals("21", parser.getProperty("score", "0"));
    }


    /**
     * Verifies that Save with a null file name does nothing.
     */
    @Test
    public void testSaveNullFileName() {
        parser.setProperty("key", "value");
        parser.save(null);
        assertEquals("value", parser.getProperty("key", ""));
    }


    /**
     * Verifies that Save with an empty file name does nothing.
     */
    @Test
    public void testSaveEmptyFileName() {
        parser.setProperty("key", "value");
        parser.save("");
        assertEquals("value", parser.getProperty("key", ""));
    }


    /**
     * Verifies that Load with a null file name leaves properties unchanged.
     */
    @Test
    public void testLoadNullFileName() {
        parser.setProperty("key", "value");
        parser.load(null);
        assertEquals("value", parser.getProperty("key", ""));
    }


    /**
     * Verifies that Load with an empty file name leaves properties unchanged.
     */
    @Test
    public void testLoadEmptyFileName() {
        parser.setProperty("key", "value");
        parser.load("");
        assertEquals("value", parser.getProperty("key", ""));
    }


    /**
     * Verifies that loading a missing file does not crash and leaves keys unset.
     */
    @Test
    public void testLoadMissingFile() {
        parser.load("this-file-should-not-exist-12345.properties");
        assertEquals("fallback", parser.getProperty("anyKey", "fallback"));
    }


    /**
     * Verifies that overwriting a property updates the stored value.
     */
    @Test
    public void testOverwriteProperty() {
        parser.setProperty("chips", "100");
        parser.setProperty("chips", "250");
        assertEquals("250", parser.getProperty("chips", "0"));
    }


    /**
     * Verifies that getProperty returns null when the key does not exist and the
     * fallback is null.
     */
    @Test
    public void testGetPropertyNullFallback() {
        assertNull(parser.getProperty("I don't exist", null));
    }
}
