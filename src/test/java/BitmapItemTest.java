import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;

public class BitmapItemTest {
    private BitmapItem bitmapItem;
    private static final String TEST_IMAGE = "JabberPoint.gif";
    private static final int TEST_LEVEL = 1;
    private ImageObserver mockObserver;

    @BeforeEach
    void setUp() {
        bitmapItem = new BitmapItem(TEST_LEVEL, TEST_IMAGE);
        mockObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                return true;
            }
        };
    }

    @Test
    void testBitmapItemCreation() {
        assertNotNull(bitmapItem);
        assertEquals(TEST_IMAGE, bitmapItem.getName());
        assertEquals(TEST_LEVEL, bitmapItem.getLevel());
    }

    @Test
    void testGetName() {
        assertEquals(TEST_IMAGE, bitmapItem.getName());
    }

    @Test
    void testGetLevel() {
        assertEquals(TEST_LEVEL, bitmapItem.getLevel());
    }

    @Test
    void testGetBoundingBox() {
        Graphics graphics = new java.awt.image.BufferedImage(100, 100, java.awt.image.BufferedImage.TYPE_INT_RGB).getGraphics();
        Style.createStyles();
        
        Rectangle boundingBox = bitmapItem.getBoundingBox(graphics, mockObserver, 1.0f, Style.getStyle(TEST_LEVEL));
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }

    @Test
    void testDraw() {
        Graphics graphics = new java.awt.image.BufferedImage(100, 100, java.awt.image.BufferedImage.TYPE_INT_RGB).getGraphics();
        Style.createStyles();
        
        assertDoesNotThrow(() -> bitmapItem.draw(0, 0, 1.0f, graphics, Style.getStyle(TEST_LEVEL), mockObserver));
    }

    @Test
    void testToString() {
        String expected = "BitmapItem[" + TEST_LEVEL + "," + TEST_IMAGE + "]";
        assertEquals(expected, bitmapItem.toString());
    }

    @Test
    void testLoadImage() {
        File imageFile = new File(TEST_IMAGE);
        assertTrue(imageFile.exists(), "Test image file should exist");
        
        // Test that the image can be drawn without throwing exceptions
        Graphics graphics = new java.awt.image.BufferedImage(100, 100, java.awt.image.BufferedImage.TYPE_INT_RGB).getGraphics();
        Style.createStyles();
        
        assertDoesNotThrow(() -> bitmapItem.draw(0, 0, 1.0f, graphics, Style.getStyle(TEST_LEVEL), mockObserver));
    }
} 