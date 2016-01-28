package pl.wasat.smarthma.ui.widgets;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.NewsArticle;

public class RemoteFetchService extends Service {

    public static ArrayList<ListItem> listItemList;
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    /*
     * Retrieve appwidget id from intent it is needed to update widget later
     * initialize our AQuery class
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID))
            appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        fetchDataFromWeb();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /**
     * method which fetches data(json) from web aquery takes params
     * remoteJsonUrl = from where data to be fetched String.class = return
     * format of data once fetched i.e. in which format the fetched data be
     * returned AjaxCallback = class to notify with data once it is fetched
     */
    @SuppressWarnings("unchecked")
    private void fetchDataFromWeb() {
        RSSTask task = new RSSTask();
        task.execute(Const.URL_ESA_NEWS_1, this);
    }

    public void refreshList(List<NewsArticle> articles) {
        listItemList = new ArrayList<>();
        for (NewsArticle article : articles) {
            String title = article.getTitle();
            String date = article.getPubDate();
            String author = article.getAuthor();
            if (title != null && date != null) {
                ListItem listItem = new ListItem();
                listItem.heading = title;
                listItem.content = date;
                if (author != null) {
                    listItem.author = author;
                } else {
                    listItem.author = "NullAuthor";
                }
                listItemList.add(listItem);
            }
        }
        populateWidget();
    }

    /**
     * Method which sends broadcast to RSSWidgetProvider
     * so that widget is notified to do necessary action
     * and here action == RSSWidgetProvider.DATA_FETCHED
     */
    private void populateWidget() {
        Intent widgetUpdateIntent = new Intent();
        widgetUpdateIntent.setAction(RSSWidgetProvider.DATA_FETCHED);
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                appWidgetId);
        sendBroadcast(widgetUpdateIntent);

        this.stopSelf();
    }
}
