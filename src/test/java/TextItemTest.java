import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class TextItemTest {
    private TextItem textItem;
    private static final int TEST_LEVEL = 1;
    private static final String TEST_TEXT = "Test Text";
    private Graphics graphics;
    private ImageObserver observer;
    private Style style;

    @BeforeEach
    void setUp() {
        textItem = new TextItem(TEST_LEVEL, TEST_TEXT);
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
    void testTextItemCreation() {
        assertNotNull(textItem);
        assertEquals(TEST_LEVEL, textItem.getLevel());
        assertEquals(TEST_TEXT, textItem.getText());
    }

    @Test
    void testGetBoundingBox() {
        Rectangle boundingBox = textItem.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }

    @Test
    void testGetBoundingBoxWithNullGraphics() {
        assertThrows(NullPointerException.class, () -> 
            textItem.getBoundingBox(null, observer, 1.0f, style));
    }

    @Test
    void testGetBoundingBoxWithNullStyle() {
        assertThrows(NullPointerException.class, () -> 
            textItem.getBoundingBox(graphics, observer, 1.0f, null));
    }

    @Test
    void testDraw() {
        assertDoesNotThrow(() -> 
            textItem.draw(0, 0, 1.0f, graphics, style, observer));
    }

    @Test
    void testDrawWithNullGraphics() {
        assertThrows(NullPointerException.class, () -> 
            textItem.draw(0, 0, 1.0f, null, style, observer));
    }

    @Test
    void testDrawWithNullStyle() {
        assertThrows(NullPointerException.class, () -> 
            textItem.draw(0, 0, 1.0f, graphics, null, observer));
    }

    @Test
    void testToString() {
        String expected = "TextItem[" + TEST_LEVEL + "," + TEST_TEXT + "]";
        assertEquals(expected, textItem.toString());
    }
} 