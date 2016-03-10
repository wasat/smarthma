/**
 *
 */
package pl.wasat.smarthma;

import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.CollectionsGroup.List;
import pl.wasat.smarthma.model.explaindoc.ExplainData;

/**
 * @author Daniel
 */

@ReportsCrashes(
        formUri = "https://wasat.cloudant.com/acra-smarthma/_design/acra-storage/_update/report",
        reportType = org.acra.sender.HttpSender.Type.JSON,
        httpMethod = org.acra.sender.HttpSender.Method.POST,
        formUriBasicAuthLogin = "otherveastabingendledind",
        formUriBasicAuthPassword = "YwuhGQ7EdnyqxvtVYqtpldsL"
)

public class SmartHMApplication extends MultiDexApplication {

    public static CollectionsGroup.List GlobalEODataList = new List();
    public static ExplainData GlobalExplainData = new ExplainData();
    public static int sortingType = Const.SORT_BY_TITLE_ASCENDING;
    public static SmartHMApplication appSingleton;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ACRA.init(this);
        appSingleton = this;
        deviceCheck();
    }

    private void deviceCheck() {
        Const.IS_KINDLE = Build.MANUFACTURER.equalsIgnoreCase("Amazon");
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return SmartHMApplication.context;
    }
}


