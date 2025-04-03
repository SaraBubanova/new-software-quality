import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class SlideItemTest {
    private TestSlideItem slideItem;
    private Graphics graphics;
    private ImageObserver observer;
    private Style style;
    private static final int TEST_LEVEL = 2;

    // Concrete implementation of SlideItem for testing
    private class TestSlideItem extends SlideItem {
        public TestSlideItem(int level) {
            super(level);
        }

        @Override
        public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
            return new Rectangle(0, 0, 100, 100);
        }

        @Override
        public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
            // Do nothing, just for testing
        }
    }

    @BeforeEach
    void setUp() {
        slideItem = new TestSlideItem(TEST_LEVEL);
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        observer = new ImageObserver() {
            @Override
            public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                return true;
            }
        };
        Style.createStyles();
        style = Style.getStyle(TEST_LEVEL);
    }

    @Test
    void testSlideItemCreation() {
        assertNotNull(slideItem);
        assertEquals(TEST_LEVEL, slideItem.getLevel());
    }

    @Test
    void testGetBoundingBox() {
        Rectangle boundingBox = slideItem.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(boundingBox);
        assertEquals(0, boundingBox.x);
        assertEquals(0, boundingBox.y);
        assertEquals(100, boundingBox.width);
        assertEquals(100, boundingBox.height);
    }

    @Test
    void testDraw() {
        // Just verify that draw doesn't throw any exceptions
        assertDoesNotThrow(() -> 
            slideItem.draw(0, 0, 1.0f, graphics, style, observer));
    }
} 