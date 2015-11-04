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
        //formKey = "",
        formUri = "https://geodoplaty.cloudant.com/acra-smarthma/_design/acra-storage/_update/report",
        reportType = org.acra.sender.HttpSender.Type.JSON,
        httpMethod = org.acra.sender.HttpSender.Method.POST,
        formUriBasicAuthLogin = "apabyetionedishouresseri",
        formUriBasicAuthPassword = "1koM7DkJ13AdkJFB2teSrtLJ"

        // NAVIN
        //formUri = "https://wasat.cloudant.com/acra-navin/_design/acra-storage/_update/report",
        //reportType = org.acra.sender.HttpSender.Type.JSON,
        //httpMethod = org.acra.sender.HttpSender.Method.POST,
        //formUriBasicAuthLogin = "llynorthclitedeshentsele",
        //formUriBasicAuthPassword = "8TH1Rph6koNQ4nA6iGHR3ies"
)

public class SmartHMApplication extends MultiDexApplication {

    public static CollectionsGroup.List GlobalEODataList = new List();
    public static ExplainData GlobalExplainData = new ExplainData();
    public static int sortingType = Const.SORT_BY_TITLE_ASCENDING;
    public static SmartHMApplication appSingleton;
    private static Context context;

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ACRA.init(this);
        appSingleton = this;
        deviceCheck();
        //SSLCertificateHandler.nuke();
    }

    private void deviceCheck() {
        Const.IS_KINDLE = Build.MANUFACTURER.equalsIgnoreCase("Amazon");
    }

    public static Context getAppContext() {
        return SmartHMApplication.context;
    }
}


