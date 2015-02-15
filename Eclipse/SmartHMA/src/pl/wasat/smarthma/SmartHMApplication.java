/**
 *
 */
package pl.wasat.smarthma;

import android.app.Application;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.CollectionsGroup.List;
import pl.wasat.smarthma.model.explaindoc.ExplainData;

/**
 * @author Daniel
 */

@ReportsCrashes(
        formKey = "",
        formUri = "https://geodoplaty.cloudant.com/acra-smarthma/_design/acra-storage/_update/report",
        reportType = org.acra.sender.HttpSender.Type.JSON,
        httpMethod = org.acra.sender.HttpSender.Method.POST,
        formUriBasicAuthLogin = "apabyetionedishouresseri",
        formUriBasicAuthPassword = "1koM7DkJ13AdkJFB2teSrtLJ"
)

public class SmartHMApplication extends Application {

    public static CollectionsGroup.List GlobalEODataList = new List();
    public static ExplainData GlobalExplainData = new ExplainData();


    @Override
    public void onCreate() {
        super.onCreate();
        // The following line triggers the initialization of ACRA
        ACRA.init(this);

    }


}


