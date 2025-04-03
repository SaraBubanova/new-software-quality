import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JabberPointTest {
    
    @Test
    void testMainWithNoArguments() {
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{}));
    }
    
    @Test
    void testMainWithValidFile() {
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{"test.xml"}));
    }
    
    @Test
    void testMainWithInvalidFile() {
        assertDoesNotThrow(() -> JabberPoint.main(new String[]{"nonexistent.xml"}));
    }
    
    @Test
    void testMainWithNullArguments() {
        assertDoesNotThrow(() -> JabberPoint.main(null));
    }
    
    @Test
    void testConstants() {
        assertEquals("IO Error: ", JabberPoint.IOERR);
        assertEquals("Jabberpoint Error ", JabberPoint.JABERR);
        assertEquals("Jabberpoint 1.6 - OU", JabberPoint.JABVERSION);
    }
} 