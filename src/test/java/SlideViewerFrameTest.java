import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import model.Presentation;
import model.Slide;

public class SlideViewerFrameTest {
    private SlideViewerFrame viewerFrame;
    private Presentation presentation;
    private Slide slide1;
    private Slide slide2;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.setTitle("Test Presentation");
        
        slide1 = new Slide();
        slide1.setTitle("Slide 1");
        slide1.append(1, "Text 1");
        
        slide2 = new Slide();
        slide2.setTitle("Slide 2");
        slide2.append(1, "Text 2");
        
        presentation.append(slide1);
        presentation.append(slide2);
        
        viewerFrame = new SlideViewerFrame("Test Title", presentation);
    }

    @Test
    void testSlideViewerFrameCreation() {
        assertNotNull(viewerFrame);
        assertEquals("Test Title", viewerFrame.getTitle());
    }

    @Test
    void testFrameDimensions() {
        assertEquals(SlideViewerFrame.WIDTH, viewerFrame.getWidth());
        assertEquals(SlideViewerFrame.HEIGHT, viewerFrame.getHeight());
    }

    @Test
    void testFramePosition() {
        assertEquals(SlideViewerFrame.XPOS, viewerFrame.getX());
        assertEquals(SlideViewerFrame.YPOS, viewerFrame.getY());
    }

    @Test
    void testFrameTitle() {
        assertEquals("Test Title", viewerFrame.getTitle());
    }

    @Test
    void testFrameVisibility() {
        assertTrue(viewerFrame.isVisible());
    }

    @Test
    void testFrameMenuBar() {
        assertNotNull(viewerFrame.getJMenuBar());
    }

    @Test
    void testFrameKeyListener() {
        assertTrue(viewerFrame.getKeyListeners().length > 0);
    }

    @Test
    void testFrameWindowListener() {
        assertTrue(viewerFrame.getWindowListeners().length > 0);
    }

    @Test
    void testFrameContent() {
        assertNotNull(viewerFrame.getContentPane());
        assertTrue(viewerFrame.getContentPane().getComponentCount() > 0);
    }

    @Test
    void testFrameDefaultCloseOperation() {
        assertEquals(JFrame.EXIT_ON_CLOSE, viewerFrame.getDefaultCloseOperation());
    }
} 