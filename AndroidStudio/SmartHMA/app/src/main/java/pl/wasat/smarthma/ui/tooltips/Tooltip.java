package pl.wasat.smarthma.ui.tooltips;

/**
 * Created by Dark Mark on 2015-11-04.
 */
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import pl.wasat.smarthma.R;

public class Tooltip
{
    private Context context;
    private PopupWindow popup;
    private View contentView;
    private LayoutInflater inflater;
    private View anchorView;
    private int type;
    private TextView textView;
    private String text;
    public static final int TYPE_BELOW = 0;
    public static final int TYPE_ABOVE = 1;
    public static final int TYPE_RIGHT_OF = 2;
    public static final int TYPE_LEFT_OF = 3;

    public Tooltip(Context context, int type, String text)
    {
        this.context = context;
        this.type = type;
        this.text = text;
        popup = new PopupWindow(context);

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (type == TYPE_BELOW)
        {
            contentView = inflater.inflate(R.layout.tooltip_layout_below, null);
        }
        else if (type == TYPE_ABOVE)
        {
            contentView = inflater.inflate(R.layout.tooltip_layout_above, null);
        }
        else if (type == TYPE_RIGHT_OF)
        {
            contentView = inflater.inflate(R.layout.tooltip_layout_right, null);
        }
        else if (type == TYPE_LEFT_OF)
        {
            contentView = inflater.inflate(R.layout.tooltip_layout_left, null);
        }
    }

    public void show(View anchorView)
    {
        this.anchorView = anchorView;
        popup.setHeight(LayoutParams.WRAP_CONTENT);
        popup.setWidth(LayoutParams.WRAP_CONTENT);

        popup.setOutsideTouchable(true);
        popup.setTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        popup.setContentView(contentView);

        textView = (TextView)popup.getContentView().findViewById(R.id.tooltip_text);

        setText(text);
        //positionPopup();
    }

    private void positionPopup()
    {
        int screen_pos[] = new int[2];
        anchorView.getLocationOnScreen(screen_pos);

        Rect anchor_rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0]
                + anchorView.getWidth(), screen_pos[1] + anchorView.getHeight());

        contentView.measure(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        int contentViewHeight = contentView.getMeasuredHeight();
        int contentViewWidth = contentView.getMeasuredWidth();

        int xPos = 0;
        int yPos = 0;

        if (type == TYPE_BELOW)
        {
            xPos = anchor_rect.centerX() - (contentViewWidth / 2);
            yPos = anchor_rect.bottom - (anchor_rect.height() / 2);
        }
        else if (type == TYPE_ABOVE)
        {
            xPos = anchor_rect.centerX() - (contentViewWidth / 2);
            yPos = anchor_rect.top - contentViewHeight;
        }
        else if (type == TYPE_RIGHT_OF)
        {
            xPos = anchor_rect.right + (contentViewWidth / 2);
            yPos = anchor_rect.centerY() - (anchor_rect.height() / 2);
        }
        else if (type == TYPE_LEFT_OF)
        {
            xPos = anchor_rect.left - contentViewWidth;
            yPos = anchor_rect.centerY() - (anchor_rect.height() / 2);
        }

        popup.showAtLocation(anchorView, Gravity.NO_GRAVITY, xPos, yPos);
    }

    public boolean isShown() {
        if (popup != null && popup.isShowing())
        {
            return true;
        }
        return false;
    }

    public void dismiss() {
        if (popup != null && popup.isShowing())
        {
            popup.dismiss();
        }
    }

    public void setText(String message)
    {
        this.text = message;
        if (textView != null)
        {
            textView.setText(message);

            positionPopup();
        }
    }

    public String getText()
    {
        if (textView != null)
        {
            return textView.getText().toString();
        }
        return null;
    }
}