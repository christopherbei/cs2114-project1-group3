import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class DataParser {
    private Properties propertiesInstance;

    public DataParser() {
        propertiesInstance = new Properties();
    }

    public void save() {
        save("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");
    }

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

    public void load() {
        load("BlackjackGameSave.IDONTKNOWWHATTOPUTHERE");
    }

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

    public void setProperty(String key, String value) {
        propertiesInstance.setProperty(key, value);
    }

    public String getProperty(String key, String fallback) {
        return propertiesInstance.getProperty(key, fallback);
    }

}
