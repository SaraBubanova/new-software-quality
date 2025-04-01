package factory;

import model.SlideItem;
import model.TextItem;
import model.BitmapItem;

/**
 * Default implementation of the SlideItemFactory
 */
public class DefaultSlideItemFactory implements SlideItemFactory {
    @Override
    public SlideItem createTextItem(int level, String text) {
        return new TextItem(level, text);
    }

    @Override
    public SlideItem createBitmapItem(int level, String name) {
        return new BitmapItem(level, name);
    }
} 