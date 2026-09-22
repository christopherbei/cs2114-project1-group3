import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

// -------------------------------------------------------------------------
/**
 * Reads and writes Blackjack game save data as key-value properties.
 * <p>
 * Values are held in memory and can be persisted to a file with
 * {@link #save()} / {@link #save(String)}, or restored with
 * {@link #load()} / {@link #load(String)}. The default save file is
 * {@code BlackjackGameSave.IDONTKNOWWHATTOPUTHERE}.
 * </p>
 *
 * @author Isaac Tsung
 * @version 1.0
 */
public class DataParser {
    /**
     * In-memory store of property keys and values used for save/load.
     */
    private Properties propertiesInstance;

    /**
     * Creates an empty parser with no properties loaded.
     */
    public DataParser() {
        propertiesInstance = new Properties();
    }

    /**
     * Saves all current properties to the default save file
     * {@code BlackjackGameSave.IDONTKNOWWHATTOPUTHERE}.
     */
    public void save() {
        save("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");
    }

    /**
     * Saves all current properties to the given file.
     * <p>
     * Does nothing if {@code fileName} is {@code null} or empty. If writing
     * fails, the exception is printed and the method returns without throwing.
     * </p>
     *
     * @param fileName
     *            the path of the file to write; ignored when null or empty
     */
    public void save(String fileName) {
        if (fileName == null || fileName == "") {
            return;
        }

        try {
                propertiesInstance.store(new FileOutputStream(fileName), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads properties from the default save file
     * {@code BlackjackGameSave.IDONTKNOWWHATTOPUTHERE}.
     */
    public void load() {
        load("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");
    }

    /**
     * Loads properties from the given file into this parser.
     * <p>
     * Does nothing if {@code fileName} is {@code null} or empty. If the file
     * is missing or cannot be read, the exception is printed and existing
     * in-memory properties are left unchanged.
     * </p>
     *
     * @param fileName
     *            the path of the file to read; ignored when null or empty
     */
    public void load(String fileName) {
        if (fileName == null || fileName == "") {
            return;
        }

        try {
            propertiesInstance.load(new FileInputStream(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets a property value for the given key, overwriting any previous value.
     *
     * @param key
     *            the property key
     * @param value
     *            the value to associate with {@code key}
     */
    public void setProperty(String key, String value) {
        propertiesInstance.setProperty(key, value);
    }

    /**
     * Returns the value for the given key, or {@code fallback} if the key is
     * not present.
     *
     * @param key
     *            the property key to look up
     * @param fallback
     *            the value returned when {@code key} has no stored value;
     *            may be {@code null}
     * @return the stored value for {@code key}, or {@code fallback} if absent
     */
    public String getProperty(String key, String fallback) {
        return propertiesInstance.getProperty(key, fallback);
    }

}
