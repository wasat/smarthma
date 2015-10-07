package pl.wasat.smarthma.helper;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.interfaces.OnSlideElementListener;

public class SwipeListEventThrower {
    //list of catchers & corresponding function to add/remove them in the list
    List<OnSlideElementListener> listeners = new ArrayList<OnSlideElementListener>();

    public void addThrowListener(OnSlideElementListener toAdd) {
        listeners.add(toAdd);
    }

    //Set of functions that Throw Events.
    public void Throw(boolean swipeRight, int position) {
        for (OnSlideElementListener hl : listeners) hl.Catch(swipeRight, position);
    }
}