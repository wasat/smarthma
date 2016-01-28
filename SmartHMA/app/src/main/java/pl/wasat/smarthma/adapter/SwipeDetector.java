package pl.wasat.smarthma.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.SwipeListEventThrower;
import pl.wasat.smarthma.interfaces.OnSlideElementListener;


public class SwipeDetector implements View.OnTouchListener {

    private static final int MIN_DISTANCE = 70;
    private static final int MIN_LOCK_DISTANCE = 30; // disallow motion intercept
    private final SwipeListEventThrower thrower;
    private final RelativeLayout deleteView;
    private final LinearLayout mainView;
    private boolean motionInterceptDisallowed = false;
    private float downX;
    private int position;
    private boolean isExpandable;

    //set position -1 if list is expandable
    public SwipeDetector(View v, int position) {
        deleteView = (RelativeLayout) v.findViewById(R.id.swipe_list_deleteview);
        mainView = (LinearLayout) v.findViewById(R.id.swipe_list_mainview);
        this.position = position;
        if (position == -1) isExpandable = true;
        thrower = new SwipeListEventThrower();
    }

    public SwipeDetector(View v) {
        deleteView = (RelativeLayout) v.findViewById(R.id.swipe_list_deleteview);
        mainView = (LinearLayout) v.findViewById(R.id.swipe_list_mainview);
        thrower = new SwipeListEventThrower();
        position = -1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ListView listView = (ListView) v.getParent();
        RelativeLayout.LayoutParams params;
        if (position == -1 || isExpandable) position = listView.getPositionForView(v);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                return true; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_MOVE:
                float upX;
            {
                upX = event.getX();
                float deltaX = downX - upX;
                if (downX < upX && deltaX < -MIN_DISTANCE) deltaX = -MIN_DISTANCE;
                if (downX > upX && deltaX > MIN_DISTANCE) deltaX = MIN_DISTANCE;

                if (Math.abs(deltaX) > MIN_LOCK_DISTANCE && listView != null && !motionInterceptDisallowed) {
                    listView.requestDisallowInterceptTouchEvent(true);
                    motionInterceptDisallowed = true;
                }

                if (deltaX > 0) {
                    deleteView.setVisibility(View.GONE);
                } else {
                    // if first swiped left and then swiped right
                    deleteView.setVisibility(View.VISIBLE);
                }

                swipe(-(int) deltaX);
                return false;
            }

            case MotionEvent.ACTION_UP:
                upX = event.getX();
                float deltaX = upX - downX;
                if (deltaX >= MIN_DISTANCE) {
                    thrower.Throw(true, position);
                } else if (deltaX <= (MIN_DISTANCE * -1)) {
                    thrower.Throw(false, position);
                }

                if (listView != null) {
                    listView.requestDisallowInterceptTouchEvent(false);
                    motionInterceptDisallowed = false;
                }
                deleteView.setVisibility(View.VISIBLE);
                params = (RelativeLayout.LayoutParams) mainView.getLayoutParams();
                params.rightMargin = 0;
                params.leftMargin = 0;
                mainView.setLayoutParams(params);
                if (upX - downX < 10 && upX - downX > -10) {
                    assert listView != null;
                    listView.performItemClick(v, position, listView.getAdapter().getItemId(position));
                }
                return false;

            case MotionEvent.ACTION_CANCEL:
                downX = event.getX();
                params = (RelativeLayout.LayoutParams) mainView.getLayoutParams();
                params.rightMargin = 0;
                params.leftMargin = 0;
                mainView.setLayoutParams(params);
                deleteView.setVisibility(View.VISIBLE);
                return false;
        }
        return true;
    }

    private void swipe(int distance) {
        View animationView = mainView;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) animationView.getLayoutParams();
        params.rightMargin = -distance;
        params.leftMargin = distance;
        animationView.setLayoutParams(params);
    }

    public void setOnClickListener(OnSlideElementListener listener) {
        thrower.addThrowListener(listener);
    }
}