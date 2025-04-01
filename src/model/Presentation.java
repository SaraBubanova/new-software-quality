package model;

import java.util.ArrayList;

/**
 * Presentation maintains the slides in the presentation.
 * There is only instance of this class.
 */
public class Presentation {
    private String showTitle; // title of the presentation
    private ArrayList<Slide> showList; // an ArrayList with Slides
    private int currentSlideNumber = 0; // the slidenummer of the current Slide
    private SlideViewerComponent slideViewComponent = null; // the viewcomponent of the Slides

    public Presentation() {
        clear();
    }

    public Presentation(SlideViewerComponent slideViewerComponent) {
        this.slideViewComponent = slideViewerComponent;
        clear();
    }

    public int getSize() {
        return showList.size();
    }

    public String getTitle() {
        return showTitle;
    }

    public void setTitle(String nt) {
        showTitle = nt;
    }

    public void setShowView(SlideViewerComponent slideViewerComponent) {
        this.slideViewComponent = slideViewerComponent;
        if (slideViewComponent != null) {
            slideViewComponent.update(this, getCurrentSlide());
        }
    }

    // give the number of the current slide
    public int getSlideNumber() {
        return currentSlideNumber;
    }

    // change the current slide number and signal it to the window
    public void setSlideNumber(int number) {
        if (showList.isEmpty()) {
            currentSlideNumber = 0;
        } else if (number < 0) {
            currentSlideNumber = 0;
        } else if (number >= showList.size()) {
            currentSlideNumber = showList.size() - 1;
        } else {
            currentSlideNumber = number;
        }
        if (slideViewComponent != null) {
            slideViewComponent.update(this, getCurrentSlide());
        }
    }

    // go to the previous slide unless you're at the beginning of the presentation
    public void prevSlide() {
        if (currentSlideNumber > 0) {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    // go to the next slide unless you're at the end of the presentation
    public void nextSlide() {
        if (currentSlideNumber < (showList.size() - 1)) {
            setSlideNumber(currentSlideNumber + 1);
        }
    }

    // Delete the presentation to be ready for the next one
    public void clear() {
        showList = new ArrayList<>();
        setTitle("New Presentation");
        setSlideNumber(0);
    }

    // Add a slide to the presentation
    public void append(Slide slide) {
        showList.add(slide);
        if (showList.size() == 1) {
            setSlideNumber(0);
        }
    }

    // Get a slide with a specific number
    public Slide getSlide(int number) {
        if (number >= 0 && number < showList.size()) {
            return showList.get(number);
        }
        return null;
    }

    // Get the current slide
    public Slide getCurrentSlide() {
        return getSlide(currentSlideNumber);
    }

    public void exit(int n) {
        System.exit(n);
    }
} 