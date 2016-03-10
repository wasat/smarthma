package pl.wasat.smarthma.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.enums.Opts;

public class SharedPrefs {
    private static SharedPreferences settings;

    public SharedPrefs(Context context) {
        settings = context.getSharedPreferences(Const.KEY_PREF_FILE, 0);
    }

    public String getCataloguePrefs() {
        return settings.getString(Const.KEY_PREF_CATALOGUE, "EOP:ESA:FEDEO");
    }

    public void setCataloguePrefs(String catalogue) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_CATALOGUE, catalogue);
        editor.apply();
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
        bboxPrefs[0] = settings.getFloat(Const.KEY_PREF_BBOX_WEST, Float.valueOf(Const.EU_BBOX_WEST));
        bboxPrefs[1] = settings.getFloat(Const.KEY_PREF_BBOX_SOUTH, Float.valueOf(Const.EU_BBOX_SOUTH));
        bboxPrefs[2] = settings.getFloat(Const.KEY_PREF_BBOX_EAST, Float.valueOf(Const.EU_BBOX_EAST));
        bboxPrefs[3] = settings.getFloat(Const.KEY_PREF_BBOX_NORTH, Float.valueOf(Const.EU_BBOX_NORTH));
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

    public float[] getCenterPrefs() {
        float[] center = new float[2];
        center[0] = settings.getFloat(Const.KEY_PREF_CENTER_LAT, Float.parseFloat("0.0"));
        center[1] = settings.getFloat(Const.KEY_PREF_CENTER_LNG, Float.parseFloat("0.0"));
        return center;
    }

    public void setCenterPrefs(float lat, float lng) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_CENTER_LAT, lat);
        editor.putFloat(Const.KEY_PREF_CENTER_LNG, lng);
        editor.apply();
    }

    public float getRadiusPrefs() {
        return settings.getFloat(Const.KEY_PREF_RADIUS, Float.parseFloat("0.0"));
    }

    public void setRadiusPrefs(float radius) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_RADIUS, radius);
        editor.apply();
    }

    public String getQueryPrefs() {
        return settings.getString(Const.KEY_PREF_QUERY, "spot");
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

    public int getAreaType() {
        return settings.getInt(Const.KEY_PREF_AREA_TYPE, Opts.AREA_POLYGON);
    }

    public void setAreaType(int areaType) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Const.KEY_PREF_AREA_TYPE, areaType);
        editor.apply();
    }

    public boolean getAreaUse() {
        return settings.getBoolean(Const.KEY_PREF_AREA_USE, true);
    }

    public void setAreaUse(boolean isUsed) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Const.KEY_PREF_AREA_USE, isUsed);
        editor.apply();
    }

    public boolean getTimeUse() {
        return settings.getBoolean(Const.KEY_PREF_TIME_USE, true);
    }

    public void setTimeUse(boolean isUsed) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Const.KEY_PREF_TIME_USE, isUsed);
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

    public String getLoginPrefs() {
        return settings.getString(Const.KEY_PREF_LOGIN, "");
    }

    public void setLoginPrefs(String login) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_LOGIN, login);
        editor.apply();
    }

    public String getPasswordPrefs() {
        return settings.getString(Const.KEY_PREF_PASSWORD, "");
    }

    public void setPasswordPrefs(String password) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_PASSWORD, password);
        editor.apply();
    }

    public long getLastSyncPrefs() {
        return settings.getLong(Const.KEY_PREF_GLOBAL_LAST_SYNC_TIME, 0);
    }

    public void setLastSyncPrefs(long time) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(Const.KEY_PREF_GLOBAL_LAST_SYNC_TIME, time);
        editor.apply();
    }

    protected void setSharedPrefDefaultValue(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
