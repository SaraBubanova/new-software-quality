package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Vector;

/**
 * <p>A slide. This class has drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private static final int MARGIN = 40; // margin around the slide
    
    private String title; // The title is kept separately
    private ArrayList<SlideItem> items; // The slide items are kept in a vector
    private ArrayList<Slide> subSlides; // Nested slides for composite pattern

    public Slide() {
        items = new ArrayList<>();
        subSlides = new ArrayList<>();
    }

    // Add a slide item to the slide
    public void append(SlideItem anItem) {
        items.add(anItem);
    }

    // Add a sub-slide to the slide
    public void appendSubSlide(Slide subSlide) {
        subSlides.add(subSlide);
    }

    // Give the title of the slide
    public String getTitle() {
        return title;
    }

    // Change the title of the slide
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    // Create a TextItem out of a String and add the TextItem to the Slide
    public void append(int level, String message) {
        append(new TextItem(level, message));
    }

    // Give all the slide items in a vector
    public Vector<SlideItem> getSlideItems() {
        return new Vector<>(items);
    }

    // Give all the sub-slides
    public ArrayList<Slide> getSubSlides() {
        return subSlides;
    }

    // Returns the size of a slide
    public int getSize() {
        return items.size();
    }

    // Draws the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view) {
        float scale = getScale(area);
        int y = area.y + MARGIN; // Start with margin from top

        // Draw the title
        if (title != null && !title.isEmpty()) {
            SlideItem titleItem = new TextItem(0, getTitle());
            Style titleStyle = Style.getStyle(0);
            titleItem.draw(area.x + MARGIN, y, scale, g, titleStyle, view);
            y += titleItem.getBoundingBox(g, view, scale, titleStyle).height + 2 * titleStyle.getLeading();
        }

        // Draw all slide items
        for (SlideItem slideItem : items) {
            Style style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x + MARGIN, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height + style.getLeading();
        }

        // Draw all sub-slides
        for (Slide subSlide : subSlides) {
            Rectangle subArea = new Rectangle(
                area.x + MARGIN,
                y + MARGIN,
                area.width - 2 * MARGIN,
                area.height - y - 2 * MARGIN
            );
            subSlide.draw(g, subArea, view);
            y = subArea.y + subArea.height + MARGIN;
        }
    }

    // Returns the scale to draw a slide
    private float getScale(Rectangle area) {
        float xScale = (float) (area.width - 2 * MARGIN) / (float) WIDTH;
        float yScale = (float) (area.height - 2 * MARGIN) / (float) HEIGHT;
        return Math.min(xScale, yScale);
    }
} 