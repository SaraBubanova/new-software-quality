import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Presentation;
import model.Slide;

public class DemoPresentationTest {
    
    @Test
    void testLoadDemoPresentation() {
        Presentation presentation = new Presentation();
        DemoPresentation.loadDemoPresentation(presentation);
        
        // Check if presentation was created
        assertNotNull(presentation);
        assertTrue(presentation.getSize() > 0);
        
        // Check first slide
        Slide firstSlide = presentation.getSlide(0);
        assertNotNull(firstSlide);
        assertEquals("JabberPoint", firstSlide.getTitle());
        
        // Check second slide
        Slide secondSlide = presentation.getSlide(1);
        assertNotNull(secondSlide);
        assertEquals("Demonstration of levels and styles", secondSlide.getTitle());
        
        // Check third slide
        Slide thirdSlide = presentation.getSlide(2);
        assertNotNull(thirdSlide);
        assertEquals("The third slide", thirdSlide.getTitle());
    }
    
    @Test
    void testDemoPresentationContent() {
        Presentation presentation = new Presentation();
        DemoPresentation.loadDemoPresentation(presentation);
        
        // Check first slide content
        Slide firstSlide = presentation.getSlide(0);
        assertEquals(10, firstSlide.getSize()); // Number of items in first slide
        
        // Check second slide content
        Slide secondSlide = presentation.getSlide(1);
        assertEquals(7, secondSlide.getSize()); // Number of items in second slide
        
        // Check third slide content
        Slide thirdSlide = presentation.getSlide(2);
        assertEquals(5, thirdSlide.getSize()); // Number of items in third slide
    }
    
    @Test
    void testDemoPresentationTitle() {
        Presentation presentation = new Presentation();
        DemoPresentation.loadDemoPresentation(presentation);
        assertEquals("Demo Presentation", presentation.getTitle());
    }
} 