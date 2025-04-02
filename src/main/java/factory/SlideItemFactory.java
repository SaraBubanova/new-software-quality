package factory;

import model.SlideItem;

/**
 * Factory interface for creating slide items
 */
public interface SlideItemFactory {
    /**
     * Creates a text item
     * @param level The level of the text item
     * @param text The text content
     * @return A new TextItem instance
     */
    SlideItem createTextItem(int level, String text);

    /**
     * Creates a bitmap item
     * @param level The level of the bitmap item
     * @param name The name/path of the bitmap
     * @return A new BitmapItem instance
     */
    SlideItem createBitmapItem(int level, String name);
} 