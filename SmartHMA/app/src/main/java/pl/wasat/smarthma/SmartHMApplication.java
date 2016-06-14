/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * The type Smart hm application.
 *
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

    /**
     * The constant GlobalEODataList.
     */
    public static CollectionsGroup.List GlobalEODataList = new List();
    /**
     * The constant GlobalExplainData.
     */
    public static ExplainData GlobalExplainData = new ExplainData();
    /**
     * The constant sortingType.
     */
    public static int sortingType = Const.SORT_BY_TITLE_ASCENDING;
    /**
     * The constant appSingleton.
     */
    public static SmartHMApplication appSingleton;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //ACRA.init(this);
        appSingleton = this;
        deviceCheck();
    }

    private void deviceCheck() {
        Const.IS_KINDLE = Build.MANUFACTURER.equalsIgnoreCase("Amazon");
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        ACRA.init(this);
    }

    /**
     * Gets app context.
     *
     * @return the app context
     */
    public static Context getAppContext() {
        return SmartHMApplication.context;
    }
}


