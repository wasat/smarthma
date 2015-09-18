package pl.wasat.smarthma.ui.menus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.ui.activities.GlobalSettingsActivity;

/**
 * Used for handling ActionBar type menus.
 */
public abstract class MenuHandler {
    final FragmentActivity activity;
    private final View menu;
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    View layout;
    final ArrayList<View> clickableViews;

    MenuHandler(FragmentActivity activity, int menuButtonID) {
        this.activity = activity;
        clickableViews = new ArrayList<>();

        menu = activity.findViewById(menuButtonID);
        createMainListener();
    }

    MenuHandler(View rootView, FragmentActivity activity, int menuButtonID) {
        this.activity = activity;
        clickableViews = new ArrayList<>();

        menu = rootView.findViewById(menuButtonID);
        createMainListener();
    }

    /**
     * Creates on click listener for the menu button.
     */
    private void createMainListener() {
        try {
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMenuView(v);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a popup window for given view.
     *
     * @param view a view above the place where a new popup window will be shown
     */
    private void loadMenuView(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        Point point = new Point();
        point.x = location[0];
        point.y = location[1];

        // Some offset to align the popup a bit down, relative to button's position.
        point.y += view.getHeight();
        showMenuPopup(point);
    }

    /**
     * Inflates a new popup window.
     *
     * @param p a point on the screen
     */
    private void showMenuPopup(Point p) {
        try {
            // Inflate the popup_layout.xml
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflateLayout();

            // Creating the PopupWindow
            popupWindow = new PopupWindow();
            popupWindow.setContentView(layout);
            popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            //popupWindow.setFocusable(true);
            popupWindow.setFocusable(false);
            //popupWindow.setOutsideTouchable(true);

            //Clear the default translucent background
            //popupWindow.setBackgroundDrawable(new BitmapDrawable());

            // Displaying the popup at the specified location, + offsets.
            popupWindow.showAtLocation(layout, Gravity.NO_GRAVITY, p.x, p.y);

            addListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inflates the popup window layout intended for specific use case.
     */
    void inflateLayout() {
    }

    /**
     * Creates on click listeners for specific buttons.
     */
    void addListeners() {
    }

    /**
     * Hides the popup window.
     */
    public void dismissPopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * Indicate whether the popup window is shown on the screen.
     *
     * @return true if popup window is shown, false otherwise
     */
    public boolean isPopupWindowVisible() {
        return popupWindow != null && popupWindow.isShowing();
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public View getMenu() {
        return menu;
    }

    public ArrayList<View> getClickableViews() {
        return clickableViews;
    }

    protected void addCommonListeners() {
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
