package model;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * A bitmap item on a slide
 */
public class BitmapItem extends SlideItem {
    private String imageName;
    private Image image;
    private static final String DEFAULT_IMAGE = "JabberPoint.gif";

    public BitmapItem(int level, String name) {
        super(level);
        imageName = name;
        try {
            image = ImageIO.read(new File(imageName));
        } catch (Exception e) {
            try {
                image = ImageIO.read(new File(DEFAULT_IMAGE));
            } catch (Exception ex) {
                System.err.println("File " + imageName + " not found");
                image = null;
            }
        }
    }

    @Override
    public String getText() {
        return imageName;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
        if (image == null) {
            return new Rectangle(0, 0, 0, 0);
        }
        return new Rectangle(0, 0, 
            (int) (image.getWidth(observer) * scale),
            (int) (image.getHeight(observer) * scale));
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
        if (image == null) {
            return;
        }
        int width = x + (int) (image.getWidth(observer) * scale);
        int height = y + (int) (image.getHeight(observer) * scale);
        g.drawImage(image, x, y, width, height, observer);
    }
} 