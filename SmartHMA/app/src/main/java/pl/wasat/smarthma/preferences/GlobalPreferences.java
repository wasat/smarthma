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

    public GlobalPreferences(Context context) {
        this.globalSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public Integer getMapType() {
        String key = context.getString(R.string.pref_key_global_map_list);
        String value = globalSharedPreferences.getString(key, "1");
        return Integer.valueOf(value);
    }

    public Boolean getIsParamsSaved() {
        String key = context.getString(R.string.pref_key_checkbox_search_params_save);
        return globalSharedPreferences.getBoolean(key, true);
    }

    public Boolean getIsDebugMode() {
        String key = context.getString(R.string.pref_key_checkbox_debug_mode);
        return globalSharedPreferences.getBoolean(key, false);
    }

    public long getMissionSyncTime() {
        String key = context.getString(R.string.pref_key_list_mission_sync_time);
        return Long.valueOf(globalSharedPreferences.getString(key, "720"));
    }

    public String getFedeoProviderHost() {
        String key = context.getString(R.string.pref_key_fedeo_provider);
        return globalSharedPreferences.getString(key, "http://fedeo.esa.int/opensearch");
    }

    public String getFedeoMaxRecords() {
        String key = context.getString(R.string.pref_key_edit_max_records);
        return globalSharedPreferences.getString(key, "30");
    }

    public String getFedeoStartRecord() {
        String key = context.getString(R.string.pref_key_edit_start_record);
        return globalSharedPreferences.getString(key, "1");
    }

    public String getFedeoStartPage() {
        String key = context.getString(R.string.pref_key_edit_start_page);
        return globalSharedPreferences.getString(key, "1");
    }
}
