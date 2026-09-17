import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DataParser {
    private Properties propertiesInstance;

    public DataParser() {
        propertiesInstance = new Properties();
    }

    /** 
     * @param fileName
     * @throws IOException
     */
    public void Save(String fileName) throws IOException {
        if (fileName == null || fileName == "") {
            return;
        }

        propertiesInstance.store(new FileOutputStream(fileName), null);
    }

    /** 
     * @param fileName
     * @throws IOException
     */
    public void Load(String fileName) throws IOException {
        if (fileName == null || fileName == "") {
            return;
        }

        propertiesInstance.load(new FileInputStream(fileName));
    }

    /** 
     * @param key
     * @param value
     */
    public void setProperty(String key, String value) {
        propertiesInstance.setProperty(key, value);
    }

    /** 
     * @param key
     * @param fallback
     * @return String
     */
    public String getProperty(String key, String fallback) {
        return propertiesInstance.getProperty(key, fallback);
    }


}
