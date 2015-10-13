package pl.wasat.smarthma.ui.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;

import pl.wasat.smarthma.R;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 * here it now takes RemoteFetchService ArrayList<ListItem> for data
 * which is a static ArrayList
 * and this example won't work if there are multiple widgets and
 * they update at same time i.e they modify RemoteFetchService ArrayList at same
 * time.
 * For that use Database or other techniques
 */
class ListProvider implements RemoteViewsFactory {
    private ArrayList<ListItem> listItemList = new ArrayList<>();
    private Context context = null;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        populateListItem();
    }

    private void populateListItem() {
        if (RemoteFetchService.listItemList != null)
            listItemList = (ArrayList) RemoteFetchService.listItemList
                    .clone();
        else
            listItemList = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_list_row);
        ListItem listItem = listItemList.get(position);
        remoteView.setTextViewText(R.id.widget_list_heading, listItem.heading);
        remoteView.setTextViewText(R.id.widget_list_date_time, listItem.content);

        /*
        Intent intent = new Intent(context, SearchActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.layout.widget_list_row, pendingIntent);
        */
        /*
        Intent intent = new Intent(context, NewsActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.widget_list_button, pendingIntent2);
        */
        /*
        Intent onClickDone = new Intent(context, NewsActivity.class);
        //onClickDone.putExtra("TASK_ID", eventInfo.getTaskEvent().getTaskId());
        PendingIntent onClickPendingDone = PendingIntent.getActivity(context, 0, onClickDone, 0);
        remoteView.setOnClickPendingIntent(R.id.widget_list_button, onClickPendingDone);
        */

        // Make it possible to distinguish the individual on-click action of a given item.
        Bundle extras = new Bundle();
        extras.putInt(RSSWidgetProvider.EXTRA_ITEM, position);
        extras.putString(RSSWidgetProvider.ARTICLE_AUTHOR, listItem.author);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        //remoteView.setOnClickFillInIntent(R.id.widget_list_button, fillInIntent);
        remoteView.setOnClickFillInIntent(R.id.widget_list_heading, fillInIntent);
        remoteView.setOnClickFillInIntent(R.id.widget_list_date_time, fillInIntent);
        remoteView.setOnClickFillInIntent(R.id.widget_list_layout, fillInIntent);

        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}
