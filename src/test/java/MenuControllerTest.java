import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.Presentation;
import model.Slide;
import java.awt.event.ActionEvent;

import model.TextItem;

public class MenuControllerTest {
    private MenuController menuController;
    private Presentation presentation;
    private JFrame frame;
    private Slide slide1;
    private Slide slide2;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        presentation = new Presentation();
        presentation.setTitle("Test Presentation");
        
        slide1 = new Slide();
        slide1.setTitle("Slide 1");
        slide1.append(1, "Text 1");
        
        slide2 = new Slide();
        slide2.setTitle("Slide 2");
        slide2.append(1, "Text 2");
        
        presentation.append(slide1);
        presentation.append(slide2);
        
        menuController = new MenuController(frame, presentation);
        frame.setJMenuBar(menuController);
    }

    @Test
    void testMenuControllerCreation() {
        assertNotNull(menuController);
        assertNotNull(frame.getJMenuBar());
    }

    @Test
    void testMenuBarStructure() {
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar);
        assertEquals(3, menuBar.getMenuCount()); // File, View, and Help menus
    }

    @Test
    void testFileMenuItems() {
        JMenuBar menuBar = frame.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0); // File menu
        assertNotNull(fileMenu);
        assertEquals(5, fileMenu.getItemCount()); // New, Open, Save, Separator, Exit
    }

    @Test
    void testHelpMenuItems() {
        JMenuBar menuBar = frame.getJMenuBar();
        JMenu helpMenu = menuBar.getMenu(2); // Help menu
        assertNotNull(helpMenu);
        assertEquals(1, helpMenu.getItemCount()); // About
    }

    @Test
    void testPresentationTitle() {
        assertEquals("Test Presentation", presentation.getTitle());
    }

    @Test
    void testPresentationSlides() {
        assertEquals(2, presentation.getSize());
        assertEquals(slide1, presentation.getSlide(0));
        assertEquals(slide2, presentation.getSlide(1));
    }

    @Test
    void testFrameTitle() {
        frame.setTitle("Jabberpoint 1.6 - OU");
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
    }

    @Test
    void testNewMenuItemAction() {
        JMenu fileMenu = frame.getJMenuBar().getMenu(0);
        JMenuItem newItem = fileMenu.getItem(1); // New is second item

        // Trigger action
        newItem.getActionListeners()[0].actionPerformed(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        assertEquals(1, presentation.getSize());
        assertEquals("New Slide", presentation.getSlide(0).getTitle());
        assertEquals(2, presentation.getSlide(0).getSlideItems().size());
    }

    @Test
    void testNextMenuItemAction() {
        JMenu viewMenu = frame.getJMenuBar().getMenu(1);
        JMenuItem nextItem = viewMenu.getItem(0); // Next is first item

        assertEquals(0, presentation.getSlideNumber());
        nextItem.getActionListeners()[0].actionPerformed(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
        assertEquals(1, presentation.getSlideNumber());
    }

} 