package model;

import java.awt.Color;
import java.awt.Font;

/** 
 * <p>Style for items on a slide.</p>
 * <p>The style includes font, color, indent, and leading.</p>
 */
public class Style {
    private static final String FONTNAME = "Arial";
    private static Style[] styles; // Array of styles

    private int indent;
    private Color color;
    private Font font;
    private int fontSize;
    private int leading;

    public static void createStyles() {
        styles = new Style[5];    
        // Style for level 0 (Title)
        styles[0] = new Style(0, new Color(180, 0, 0), 48, 20, 20); // Dark red for title
        // Style for level 1 (Main points)
        styles[1] = new Style(20, new Color(0, 0, 180), 40, 10, 10); // Dark blue for main points
        // Style for level 2 (Sub points)
        styles[2] = new Style(40, new Color(0, 100, 0), 36, 10, 10); // Dark green for sub points
        // Style for level 3 (Sub-sub points)
        styles[3] = new Style(60, new Color(100, 0, 100), 30, 10, 10); // Purple for sub-sub points
        // Style for level 4 (Sub-sub-sub points)
        styles[4] = new Style(80, new Color(0, 100, 100), 24, 10, 10); // Teal for sub-sub-sub points
    }

    public static Style getStyle(int level) {
        if (styles == null) {
            createStyles();
        }
        if (level >= styles.length) {
            level = styles.length - 1;
        }
        return styles[level];
    }

    public Style(int indent, Color color, int fontSize, int leading, int points) {
        this.indent = indent;
        this.color = color;
        this.fontSize = fontSize;
        this.leading = leading;
        font = new Font(FONTNAME, Font.BOLD, fontSize);
    }

    public int getIndent() {
        return indent;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont(float scale) {
        return font.deriveFont(fontSize * scale);
    }

    public int getLeading() {
        return leading;
    }

    public String toString() {
        return "[Style:indent=" + indent + ",fontSize=" + fontSize + ",color=" + color + "]";
    }
} 