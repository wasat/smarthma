package pl.wasat.smarthma.ui.menus;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import pl.wasat.smarthma.R;

/**
 * Used for handling menu in the FragmentActivity class.
 */
public class CommonMenuHandler extends MenuHandler {
    public CommonMenuHandler(FragmentActivity activity, int menuButtonID) {
        super(activity, menuButtonID);
    }

    public CommonMenuHandler(View rootView, FragmentActivity activity, int menu_button) {
        super(rootView, activity, menu_button);
    }

    /**
     * Inflates the popup window layout.
     */
    protected void inflateLayout() {
        layout = layoutInflater.inflate(R.layout.popup_menu_common, null);
    }

    /**
     * Creates on click listeners for specific buttons.
     */
    protected void addListeners() {
        addCommonListeners();
    }
}
