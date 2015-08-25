package pl.wasat.smarthma.ui.menus;

import android.view.View;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.activities.StartActivity;

/**
 * Used for handling menu in the StartActivity class.
 */
class SampleMenuHandler extends MenuHandler {
    public SampleMenuHandler(View rootView, StartActivity activity, int menuButtonID) {
        super(rootView, activity, menuButtonID);
    }

    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu, null);
    }


}
