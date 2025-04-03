import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.KeyEvent;
import model.Presentation;
import model.Slide;

public class KeyControllerTest {
    private KeyController keyController;
    private Presentation presentation;
    private Slide slide1;
    private Slide slide2;
    private Slide slide3;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide1 = new Slide();
        slide1.setTitle("Slide 1");
        slide1.append(1, "Text 1");
        
        slide2 = new Slide();
        slide2.setTitle("Slide 2");
        slide2.append(1, "Text 2");
        
        slide3 = new Slide();
        slide3.setTitle("Slide 3");
        slide3.append(1, "Text 3");
        
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);
        
        keyController = new KeyController(presentation);
    }

    @Test
    void testKeyControllerCreation() {
        assertNotNull(keyController);
    }

    @Test
    void testKeyPressedNextSlide() {
        // Simulate Page Down key
        KeyEvent pageDownEvent = new KeyEvent(
            new java.awt.Frame(), 
            KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            KeyEvent.VK_PAGE_DOWN, 
            KeyEvent.CHAR_UNDEFINED
        );
        
        int initialSlideNumber = presentation.getSlideNumber();
        keyController.keyPressed(pageDownEvent);
        assertEquals(initialSlideNumber + 1, presentation.getSlideNumber());
    }

    @Test
    void testKeyPressedPreviousSlide() {
        // Move to second slide first
        presentation.setSlideNumber(1);
        
        // Simulate Page Up key
        KeyEvent pageUpEvent = new KeyEvent(
            new java.awt.Frame(), 
            KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            KeyEvent.VK_PAGE_UP, 
            KeyEvent.CHAR_UNDEFINED
        );
        
        int initialSlideNumber = presentation.getSlideNumber();
        keyController.keyPressed(pageUpEvent);
        assertEquals(initialSlideNumber - 1, presentation.getSlideNumber());
    }

    @Test
    void testKeyPressedQuit() {
        // Simulate 'q' key
        KeyEvent quitEvent = new KeyEvent(
            new java.awt.Frame(), 
            KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            KeyEvent.VK_Q, 
            'q'
        );
        
        // Note: We can't directly test System.exit(0)
        // Instead, we'll verify that the key event is handled
        assertDoesNotThrow(() -> keyController.keyPressed(quitEvent));
    }

    @Test
    void testKeyPressedAtFirstSlide() {
        // Ensure we're at the first slide
        presentation.setSlideNumber(0);
        
        // Simulate Page Up key
        KeyEvent pageUpEvent = new KeyEvent(
            new java.awt.Frame(), 
            KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            KeyEvent.VK_PAGE_UP, 
            KeyEvent.CHAR_UNDEFINED
        );
        
        keyController.keyPressed(pageUpEvent);
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testKeyPressedAtLastSlide() {
        // Move to last slide
        presentation.setSlideNumber(presentation.getSize() - 1);
        
        // Simulate Page Down key
        KeyEvent pageDownEvent = new KeyEvent(
            new java.awt.Frame(), 
            KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 
            0, 
            KeyEvent.VK_PAGE_DOWN, 
            KeyEvent.CHAR_UNDEFINED
        );
        
        keyController.keyPressed(pageDownEvent);
        assertEquals(presentation.getSize() - 1, presentation.getSlideNumber());
    }
} 