package model;

import java.io.IOException;

/**
 * Abstract class for loading and saving presentations
 */
public abstract class Accessor {
    public abstract void loadFile(Presentation presentation, String filename) throws IOException;
    public abstract void saveFile(Presentation presentation, String filename) throws IOException;
} 