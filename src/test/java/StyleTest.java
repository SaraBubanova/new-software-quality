import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Font;
import model.Style;

public class StyleTest {
    private Style style;
    private static final int TEST_INDENT = 20;
    private static final Color TEST_COLOR = new Color(0, 0, 180);
    private static final int TEST_FONT_SIZE = 40;
    private static final int TEST_LEADING = 10;
    private static final int TEST_POINTS = 10;

    @BeforeEach
    void setUp() {
        style = new Style(TEST_INDENT, TEST_COLOR, TEST_FONT_SIZE, TEST_LEADING, TEST_POINTS);
    }

    @Test
    void testStyleCreation() {
        assertNotNull(style);
        assertEquals(TEST_INDENT, style.getIndent());
        assertEquals(TEST_COLOR, style.getColor());
        assertEquals(TEST_LEADING, style.getLeading());
        assertNotNull(style.getFont(1.0f));
    }

    @Test
    void testGetFont() {
        Font font = style.getFont(1.0f);
        assertNotNull(font);
        assertEquals(TEST_FONT_SIZE, font.getSize());
    }

    @Test
    void testGetFontWithScale() {
        float scale = 1.5f;
        Font font = style.getFont(scale);
        assertNotNull(font);
        assertEquals((int)(TEST_FONT_SIZE * scale), font.getSize());
    }

    @Test
    void testStaticStyles() {
        Style.createStyles();
        Style style0 = Style.getStyle(0);
        Style style1 = Style.getStyle(1);
        Style style2 = Style.getStyle(2);
        Style style3 = Style.getStyle(3);
        Style style4 = Style.getStyle(4);

        assertNotNull(style0);
        assertNotNull(style1);
        assertNotNull(style2);
        assertNotNull(style3);
        assertNotNull(style4);

        // Test that styles are different
        assertNotEquals(style0.getIndent(), style1.getIndent());
        assertNotEquals(style1.getIndent(), style2.getIndent());
        assertNotEquals(style2.getIndent(), style3.getIndent());
        assertNotEquals(style3.getIndent(), style4.getIndent());
    }

    @Test
    void testGetStyleWithInvalidLevel() {
        Style.createStyles();
        Style style = Style.getStyle(999); // Invalid level
        assertNotNull(style);
        // Should return the last valid style
        assertEquals(80, style.getIndent());
    }
} 