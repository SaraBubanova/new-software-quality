import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static boolean loaded = false;

    public static void loadConfig() {
        if (loaded) {
            return;
        }

        String env = System.getProperty("env", "dev");
        String configFile = "config-" + env + ".properties";

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + configFile);
                return;
            }

            properties.load(input);
            loaded = true;
            System.out.println("Loaded configuration for environment: " + env);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (!loaded) {
            loadConfig();
        }
        return properties.getProperty(key);
    }
}
