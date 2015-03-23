/**
 *
 */
package pl.wasat.smarthma;

import android.app.Application;
import android.os.Build;

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
)

public class SmartHMApplication extends Application {

    public static CollectionsGroup.List GlobalEODataList = new List();
    public static ExplainData GlobalExplainData = new ExplainData();
    public static int sortingType = Const.SORT_BY_TITLE_ASCENDING;
    public static SmartHMApplication appSingleton;


    @Override
    public void onCreate() {
        super.onCreate();
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        appSingleton = this;
        deviceCheck();
    }


    private void deviceCheck() {
<<<<<<< HEAD
<<<<<<< HEAD
        Const.IS_KINDLE = Build.MANUFACTURER.equalsIgnoreCase("Amazon");
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
        if (Build.MANUFACTURER.equalsIgnoreCase("Amazon")) {
            Const.IS_KINDLE = true;

            if (Build.MODEL.equalsIgnoreCase("KFAPWA")
                    || Build.MODEL.equalsIgnoreCase("KFAPWI")
                    || Build.MODEL.equalsIgnoreCase("KFTHWA")
                    || Build.MODEL.equalsIgnoreCase("KFTHWI")
                    || Build.MODEL.equalsIgnoreCase("KFSOWI")
                    || Build.MODEL.equalsIgnoreCase("KFJWA")
                    || Build.MODEL.equalsIgnoreCase("KFJWI")) {
                // Do something if it is an HD or HDX device

            } else {
                // Do something if it not an HD or HDX device
            }
        } else {
            Const.IS_KINDLE = false;

        }
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    }
}


