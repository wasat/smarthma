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

package pl.wasat.smarthma.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import pl.wasat.smarthma.R;

/**
 * Created by Daniel on 2015-07-21 22:12.
 * Part of the project  SmartHMA
 */
public class GlobalPreferences {
    private final Context context;
    private final SharedPreferences globalSharedPreferences;

    /**
     * Instantiates a new Global preferences.
     *
     * @param context the context
     */
    public GlobalPreferences(Context context) {
        this.globalSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    /**
     * Gets map type.
     *
     * @return the map type
     */
    public Integer getMapType() {
        String key = context.getString(R.string.pref_key_global_map_list);
        String value = globalSharedPreferences.getString(key, "1");
        return Integer.valueOf(value);
    }

    /**
     * Gets is params saved.
     *
     * @return the is params saved
     */
    public Boolean getIsParamsSaved() {
        String key = context.getString(R.string.pref_key_checkbox_search_params_save);
        return globalSharedPreferences.getBoolean(key, true);
    }

    /**
     * Gets is debug mode.
     *
     * @return the is debug mode
     */
    public Boolean getIsDebugMode() {
        String key = context.getString(R.string.pref_key_checkbox_debug_mode);
        return globalSharedPreferences.getBoolean(key, false);
    }

    /**
     * Gets mission sync time.
     *
     * @return the mission sync time
     */
    public long getMissionSyncTime() {
        String key = context.getString(R.string.pref_key_list_mission_sync_time);
        return Long.valueOf(globalSharedPreferences.getString(key, "720"));
    }

    /**
     * Gets fedeo provider host.
     *
     * @return the fedeo provider host
     */
    public String getFedeoProviderHost() {
        String key = context.getString(R.string.pref_key_fedeo_provider);
        return globalSharedPreferences.getString(key, "http://fedeo.esa.int/opensearch");
    }

    /**
     * Gets fedeo max records.
     *
     * @return the fedeo max records
     */
    public String getFedeoMaxRecords() {
        String key = context.getString(R.string.pref_key_edit_max_records);
        return globalSharedPreferences.getString(key, "30");
    }

    /**
     * Gets fedeo start record.
     *
     * @return the fedeo start record
     */
    public String getFedeoStartRecord() {
        String key = context.getString(R.string.pref_key_edit_start_record);
        return globalSharedPreferences.getString(key, "1");
    }

    /**
     * Gets fedeo start page.
     *
     * @return the fedeo start page
     */
    public String getFedeoStartPage() {
        String key = context.getString(R.string.pref_key_edit_start_page);
        return globalSharedPreferences.getString(key, "1");
    }
}
