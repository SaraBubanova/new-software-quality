import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

public class PresentationTest {
    private Presentation presentation;
    private Slide slide1;
    private Slide slide2;
    private SlideViewerComponent mockViewer;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide1 = new Slide();
        slide1.setTitle("Slide 1");
        slide1.append(1, "Text 1");
        
        slide2 = new Slide();
        slide2.setTitle("Slide 2");
        slide2.append(1, "Text 2");
        
        frame = new JFrame();
        mockViewer = new SlideViewerComponent(presentation, frame);
    }

    @Test
    void testPresentationCreation() {
        assertNotNull(presentation);
        assertEquals("New Presentation", presentation.getTitle());
        assertEquals(0, presentation.getSize());
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testSetAndGetTitle() {
        String title = "Test Presentation";
        presentation.setTitle(title);
        assertEquals(title, presentation.getTitle());
    }

    @Test
    void testAppendSlide() {
        presentation.append(slide1);
        assertEquals(1, presentation.getSize());
        assertEquals(slide1, presentation.getSlide(0));
    }

    @Test
    void testGetSlide() {
        presentation.append(slide1);
        presentation.append(slide2);
        assertEquals(slide1, presentation.getSlide(0));
        assertEquals(slide2, presentation.getSlide(1));
    }

    @Test
    void testGetSlideWithInvalidIndex() {
        presentation.append(slide1);
        assertNull(presentation.getSlide(999));
    }

    @Test
    void testGetCurrentSlide() {
        presentation.append(slide1);
        presentation.append(slide2);
        assertEquals(slide1, presentation.getCurrentSlide());
    }

    @Test
    void testSetAndGetSlideNumber() {
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.setSlideNumber(1);
        assertEquals(1, presentation.getSlideNumber());
        assertEquals(slide2, presentation.getCurrentSlide());
    }

    @Test
    void testSetSlideNumberWithInvalidIndex() {
        presentation.append(slide1);
        presentation.setSlideNumber(999);
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testPrevSlide() {
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.setSlideNumber(1);
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testNextSlide() {
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.nextSlide();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testClear() {
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.clear();
        assertEquals(0, presentation.getSize());
        assertEquals("New Presentation", presentation.getTitle());
    }

    @Test
    void testSetShowView() {
        presentation.setShowView(mockViewer);
        presentation.append(slide1);
        assertEquals("New Presentation", presentation.getTitle());
    }
} 