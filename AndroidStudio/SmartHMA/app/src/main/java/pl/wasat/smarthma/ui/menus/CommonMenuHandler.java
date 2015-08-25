package pl.wasat.smarthma.ui.menus;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.ui.activities.GlobalSettingsActivity;

/**
 * Used for handling menu in the SearchActivity class.
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
        LinearLayout settingsLayout = (LinearLayout) layout.findViewById(R.id.popup_menu_item_settings);
        clickableViews.add(settingsLayout);
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity, GlobalSettingsActivity.class);
                activity.startActivityForResult(intent, Const.REQUEST_CODE_GLOBAL_SETTINGS);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });

    }
}
