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
    private boolean motionInterceptDisallowed = false;
    // private ListView listView;
    private float downX, upX;
    public SwipeListEventThrower thrower;
    private RelativeLayout deleteView;
    private LinearLayout mainView;
    private int position;
    boolean isExpandable;


    public SwipeDetector(View v, int position, boolean isExpandable) {
        this.isExpandable = isExpandable;
        deleteView = (RelativeLayout) v.findViewById(R.id.swipe_list_deleteview);
        mainView = (LinearLayout) v.findViewById(R.id.swipe_list_mainview);
        this.position = position;
        thrower = new SwipeListEventThrower();
    }

    public void setOnClickListener(OnSlideElementListener listener) {
        thrower.addThrowListener(listener);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ListView listView = (ListView) v.getParent();
        RelativeLayout.LayoutParams params;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
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
                    deleteView.setVisibility(View.VISIBLE);
                }

                swipe(-(int) deltaX);
                return true;
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
                    if (!isExpandable) {
                        listView.performItemClick(v, position, listView.getAdapter().getItemId(position));
                    } else {
                        position = listView.getPositionForView(v);
                        listView.performItemClick(v, position, listView.getAdapter().getItemId(position));
                    }
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
}