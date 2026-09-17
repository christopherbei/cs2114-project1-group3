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
    public void Save(String fileName) {
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
     * @param fileName
     * @throws IOException
     */
    public void Load(String fileName) {
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
