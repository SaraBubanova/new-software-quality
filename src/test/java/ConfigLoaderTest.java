import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigLoaderTest {

    @TempDir
    Path tempDir;
    private String originalEnv;

    @BeforeEach
    void setUp() {
        originalEnv = System.getProperty("env");
        resetConfigLoader();
    }

    @AfterEach
    void tearDown() {
        if (originalEnv != null) {
            System.setProperty("env", originalEnv);
        } else {
            System.clearProperty("env");
        }
        resetConfigLoader();
    }

    private void resetConfigLoader() {
        ConfigLoader.loaded = false;
        ConfigLoader.properties.clear();
    }

    @Test
    void loadConfig_shouldNotReloadWhenAlreadyLoaded() {
        ConfigLoader.loaded = true;
        ConfigLoader.properties.setProperty("test.key", "test.value");
        ConfigLoader.loadConfig();
        assertEquals("test.value", ConfigLoader.properties.getProperty("test.key"));
    }

    @Test
    void loadConfig_shouldHandleMissingConfigFileGracefully() {
        System.setProperty("env", "nonexistent");
        ConfigLoader.loadConfig();
        assertFalse(ConfigLoader.loaded);
    }

    @Test
    void getProperty_shouldReturnNullForNonexistentKey() {
        ConfigLoader.loaded = true;
        assertNull(ConfigLoader.getProperty("nonexistent.key"));
    }

    // Helper class to simulate different class loader behaviors
    private static class TestClassLoader extends ClassLoader {
        private final Path configFile;
        private final boolean throwIOException;

        public TestClassLoader(Path configFile) {
            this(configFile, false);
        }

        public TestClassLoader(Path configFile, boolean throwIOException) {
            this.configFile = configFile;
            this.throwIOException = throwIOException;
        }

        @Override
        public InputStream getResourceAsStream(String name) {
            if (throwIOException) {
                return new InputStream() {
                    @Override
                    public int read() throws IOException {
                        throw new IOException("Simulated IO Exception");
                    }
                };
            }
            try {
                if (name.equals("config-test.properties")) {
                    return Files.newInputStream(configFile);
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}