import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;

public class SlideTest {
    private Slide slide;
    private TextItem textItem;
    private BitmapItem bitmapItem;

    @BeforeEach
    void setUp() {
        Style.createStyles(); // Initialize styles before creating slides
        slide = new Slide();
        textItem = new TextItem(1, "Test Text");
        bitmapItem = new BitmapItem(1, "JabberPoint.gif");
    }

    @Test
    void testSlideCreation() {
        assertNotNull(slide);
        assertEquals(null, slide.getTitle());
        assertEquals(0, slide.getSize());
    }

    @Test
    void testSetAndGetTitle() {
        String title = "Test Slide";
        slide.setTitle(title);
        assertEquals(title, slide.getTitle());
    }

    @Test
    void testAppendTextItem() {
        slide.append(1, "Test Text");
        assertEquals(1, slide.getSize());
        assertTrue(slide.getSlideItems().get(0) instanceof TextItem);
    }

    @Test
    void testAppendBitmapItem() {
        slide.append(new BitmapItem(1, "JabberPoint.gif"));
        assertEquals(1, slide.getSize());
        assertTrue(slide.getSlideItems().get(0) instanceof BitmapItem);
    }

    @Test
    void testGetSlideItems() {
        slide.append(1, "Text 1");
        slide.append(1, "Text 2");
        assertEquals(2, slide.getSlideItems().size());
    }

    @Test
    void testGetSize() {
        slide.append(1, "Text 1");
        slide.append(1, "Text 2");
        assertEquals(2, slide.getSize());
    }

    @Test
    void testDraw() {
        // Create a mock Graphics object
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        
        // Create a mock ImageObserver
        ImageObserver observer = new ImageObserver() {
            @Override
            public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                return true;
            }
        };
        
        // Add some items to the slide
        slide.setTitle("Test Title");
        slide.append(1, "Test Text");
        
        // Test drawing (should not throw any exceptions)
        assertDoesNotThrow(() -> slide.draw(graphics, new Rectangle(0, 0, 100, 100), observer));
    }
} 