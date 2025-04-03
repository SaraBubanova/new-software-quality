import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlideViewerComponentTest {
    private SlideViewerComponent viewerComponent;
    private Presentation presentation;
    private JFrame frame;
    private Slide testSlide;
    private Graphics graphics;

    @BeforeEach
    void setUp() {
        Style.createStyles(); // Initialize styles before creating components
        presentation = new Presentation();
        frame = new JFrame();
        viewerComponent = new SlideViewerComponent(presentation, frame);
        testSlide = new Slide();
        testSlide.setTitle("Test Slide");
        presentation.append(testSlide);
        
        // Create a BufferedImage for testing graphics operations
        BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
    }

    @Test
    void testConstructor() {
        assertNotNull(viewerComponent);
        assertEquals(new Dimension(Slide.WIDTH, Slide.HEIGHT), viewerComponent.getPreferredSize());
    }

    @Test
    void testUpdate() {
        viewerComponent.update(presentation, testSlide);
        // Verify that the frame title was updated
        assertEquals(presentation.getTitle(), frame.getTitle());
    }

    @Test
    void testUpdateWithNullSlide() {
        viewerComponent.update(presentation, null);
        // Should not throw exception
        assertDoesNotThrow(() -> viewerComponent.paintComponent(graphics));
    }

    @Test
    void testPaintComponentWithNoSlides() {
        presentation = new Presentation();
        viewerComponent.update(presentation, null);
        assertDoesNotThrow(() -> viewerComponent.paintComponent(graphics));
    }

    @Test
    void testPreferredSize() {
        Dimension expectedSize = new Dimension(Slide.WIDTH, Slide.HEIGHT);
        assertEquals(expectedSize, viewerComponent.getPreferredSize());
    }
} 