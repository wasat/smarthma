package pl.wasat.smarthma.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import pl.wasat.smarthma.helper.Const;

public class SharedPrefs {
    private static SharedPreferences settings;

    public SharedPrefs(Context context) {
        settings = context.getSharedPreferences(Const.KEY_PREF_FILE, 0);
    }

    public String getStartDateTimePrefs() {
        return settings.getString(Const.KEY_PREF_DATETIME_START,
                "2000-01-01T00:00:00Z");
    }

    public String getEndDateTimePrefs() {
        return settings.getString(Const.KEY_PREF_DATETIME_END,
                "2015-01-01T00:00:00Z");
    }

    public String getParentIdPrefs() {
        return settings.getString(Const.KEY_PREF_PARENT_ID, "Fedeo");
    }

    public void setParentIdPrefs(String parentId) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_PARENT_ID, parentId);
        editor.apply();
    }

    public float[] getBboxPrefs() {
        float[] bboxPrefs = new float[4];

        bboxPrefs[3] = settings.getFloat(Const.KEY_PREF_BBOX_NORTH, Float.valueOf(Const.EU_BBOX_NORTH));
        bboxPrefs[2] = settings.getFloat(Const.KEY_PREF_BBOX_EAST, Float.valueOf(Const.EU_BBOX_EAST));
        bboxPrefs[1] = settings.getFloat(Const.KEY_PREF_BBOX_SOUTH, Float.valueOf(Const.EU_BBOX_SOUTH));
        bboxPrefs[0] = settings.getFloat(Const.KEY_PREF_BBOX_WEST, Float.valueOf(Const.EU_BBOX_WEST));

        return bboxPrefs;
    }

    public void setBboxPrefs(String bboxWest, String bboxSouth,
                             String bboxEast, String bboxNorth) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_BBOX_WEST, Float.valueOf(bboxWest));
        editor.putFloat(Const.KEY_PREF_BBOX_SOUTH, Float.valueOf(bboxSouth));
        editor.putFloat(Const.KEY_PREF_BBOX_EAST, Float.valueOf(bboxEast));
        editor.putFloat(Const.KEY_PREF_BBOX_NORTH, Float.valueOf(bboxNorth));
        editor.apply();
    }

    public String getQueryPrefs() {
        return settings.getString(Const.KEY_PREF_QUERY, "");
    }

    public void setQueryPrefs(String query) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_QUERY, query);
        editor.apply();
    }

    public String getUrlPrefs() {
        return settings.getString(Const.KEY_PREF_URL, "");
    }

    public void setUrlPrefs(String url) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_URL, url);
        editor.apply();
    }

    /**
     *
     */
    public void setDateTimePrefs(String calStartStr, String calEndStr) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_DATETIME_START, calStartStr);
        editor.putString(Const.KEY_PREF_DATETIME_END, calEndStr);
        editor.apply();
    }

    protected void setSharedPrefDefaultValue(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
