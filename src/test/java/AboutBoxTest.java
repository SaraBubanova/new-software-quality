import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;

public class AboutBoxTest {
    
    @Test
    void testShow() {
        Frame parent = new Frame();
        assertDoesNotThrow(() -> AboutBox.show(parent));
    }
    
    @Test
    void testShowWithNullParent() {
        assertDoesNotThrow(() -> AboutBox.show(null));
    }
} 