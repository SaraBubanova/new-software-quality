package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;

/**
 * <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 */
public class TextItem extends SlideItem {
    private String text;

    public TextItem(int level, String string) {
        super(level);
        text = string;
    }

    public String getText() {
        return text;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
        Graphics2D g2d = (Graphics2D) g;
        AttributedString attrStr = new AttributedString(text);
        attrStr.addAttribute(java.awt.font.TextAttribute.FONT, style.getFont(scale));
        
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout(text, style.getFont(scale), frc);
        Rectangle2D bounds = layout.getBounds();
        
        return new Rectangle(
            0,
            0,
            (int) Math.ceil(bounds.getWidth()),
            (int) Math.ceil(bounds.getHeight())
        );
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
        if (text == null || text.isEmpty()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(style.getColor());
        g2d.setFont(style.getFont(scale));

        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout(text, style.getFont(scale), frc);
        
        // Draw the text at the baseline
        layout.draw(g2d, x, y + (float)layout.getAscent());
    }

    public String toString() {
        return "TextItem[" + getLevel() + "," + text + "]";
    }
} 