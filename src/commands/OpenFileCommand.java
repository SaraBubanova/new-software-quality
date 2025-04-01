package commands;

import model.Presentation;
import model.Accessor;
import model.XMLAccessor;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;

/**
 * Command to open a presentation file
 */
public class OpenFileCommand implements Command {
    private final Presentation presentation;
    private final Frame parent;
    private final String filename;

    public OpenFileCommand(Presentation presentation, Frame parent, String filename) {
        this.presentation = presentation;
        this.parent = parent;
        this.filename = filename;
    }

    @Override
    public void execute() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, filename);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, 
                "IO Exception: " + exc, 
                "Load Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
} 