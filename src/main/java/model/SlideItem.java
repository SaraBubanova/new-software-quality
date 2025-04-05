package model;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * The abstract class for an item on a slide
 * All SlideItems have drawing functionality.
 */
public abstract class SlideItem {
    private int level = 0; // level of the slideitem

    public SlideItem(int lev) {
        level = lev;
    }

    public int getLevel() {
        return level;
    }

    public abstract Rectangle getBoundingBox(Graphics g, 
            ImageObserver observer, float scale, Style style);

    public abstract void draw(int x, int y, float scale, 
            Graphics g, Style style, ImageObserver observer);

    public abstract String getText();
} 