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

import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.helper.enums.Opts;

/**
 * The type Shared prefs.
 */
public class SharedPrefs {
    private static SharedPreferences settings;

    /**
     * Instantiates a new Shared prefs.
     *
     * @param context the context
     */
    public SharedPrefs(Context context) {
        settings = context.getSharedPreferences(Const.KEY_PREF_FILE, 0);
    }

    /**
     * Gets catalogue prefs.
     *
     * @return the catalogue prefs
     */
    public String getCataloguePrefs() {
        return settings.getString(Const.KEY_PREF_CATALOGUE, "EOP:ESA:FEDEO");
    }

    /**
     * Sets catalogue prefs.
     *
     * @param catalogue the catalogue
     */
    public void setCataloguePrefs(String catalogue) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_CATALOGUE, catalogue);
        editor.apply();
    }

    /**
     * Gets start date time prefs.
     *
     * @return the start date time prefs
     */
    public String getStartDateTimePrefs() {
        return settings.getString(Const.KEY_PREF_DATETIME_START,
                "2000-01-01T00:00:00Z");
    }

    /**
     * Gets end date time prefs.
     *
     * @return the end date time prefs
     */
    public String getEndDateTimePrefs() {
        return settings.getString(Const.KEY_PREF_DATETIME_END,
                "2015-01-01T00:00:00Z");
    }

    /**
     * Gets parent id prefs.
     *
     * @return the parent id prefs
     */
    public String getParentIdPrefs() {
        return settings.getString(Const.KEY_PREF_PARENT_ID, "Fedeo");
    }

    /**
     * Sets parent id prefs.
     *
     * @param parentId the parent id
     */
    public void setParentIdPrefs(String parentId) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_PARENT_ID, parentId);
        editor.apply();
    }

    /**
     * Get bbox prefs float [ ].
     *
     * @return the float [ ]
     */
    public float[] getBboxPrefs() {
        float[] bboxPrefs = new float[4];
        bboxPrefs[0] = settings.getFloat(Const.KEY_PREF_BBOX_WEST, Float.valueOf(Const.EU_BBOX_WEST));
        bboxPrefs[1] = settings.getFloat(Const.KEY_PREF_BBOX_SOUTH, Float.valueOf(Const.EU_BBOX_SOUTH));
        bboxPrefs[2] = settings.getFloat(Const.KEY_PREF_BBOX_EAST, Float.valueOf(Const.EU_BBOX_EAST));
        bboxPrefs[3] = settings.getFloat(Const.KEY_PREF_BBOX_NORTH, Float.valueOf(Const.EU_BBOX_NORTH));
        return bboxPrefs;
    }

    /**
     * Sets bbox prefs.
     *
     * @param bboxWest  the bbox west
     * @param bboxSouth the bbox south
     * @param bboxEast  the bbox east
     * @param bboxNorth the bbox north
     */
    public void setBboxPrefs(String bboxWest, String bboxSouth,
                             String bboxEast, String bboxNorth) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_BBOX_WEST, Float.valueOf(bboxWest));
        editor.putFloat(Const.KEY_PREF_BBOX_SOUTH, Float.valueOf(bboxSouth));
        editor.putFloat(Const.KEY_PREF_BBOX_EAST, Float.valueOf(bboxEast));
        editor.putFloat(Const.KEY_PREF_BBOX_NORTH, Float.valueOf(bboxNorth));
        editor.apply();
    }

    /**
     * Get center prefs float [ ].
     *
     * @return the float [ ]
     */
    public float[] getCenterPrefs() {
        float[] center = new float[2];
        center[0] = settings.getFloat(Const.KEY_PREF_CENTER_LAT, Float.parseFloat("0.0"));
        center[1] = settings.getFloat(Const.KEY_PREF_CENTER_LNG, Float.parseFloat("0.0"));
        return center;
    }

    /**
     * Sets center prefs.
     *
     * @param lat the lat
     * @param lng the lng
     */
    public void setCenterPrefs(float lat, float lng) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_CENTER_LAT, lat);
        editor.putFloat(Const.KEY_PREF_CENTER_LNG, lng);
        editor.apply();
    }

    /**
     * Gets radius prefs.
     *
     * @return the radius prefs
     */
    public float getRadiusPrefs() {
        return settings.getFloat(Const.KEY_PREF_RADIUS, Float.parseFloat("0.0"));
    }

    /**
     * Sets radius prefs.
     *
     * @param radius the radius
     */
    public void setRadiusPrefs(float radius) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Const.KEY_PREF_RADIUS, radius);
        editor.apply();
    }

    /**
     * Gets query prefs.
     *
     * @return the query prefs
     */
    public String getQueryPrefs() {
        return settings.getString(Const.KEY_PREF_QUERY, "spot");
    }

    /**
     * Sets query prefs.
     *
     * @param query the query
     */
    public void setQueryPrefs(String query) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_QUERY, query);
        editor.apply();
    }

    /**
     * Gets url prefs.
     *
     * @return the url prefs
     */
    public String getUrlPrefs() {
        return settings.getString(Const.KEY_PREF_URL, "");
    }

    /**
     * Sets url prefs.
     *
     * @param url the url
     */
    public void setUrlPrefs(String url) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_URL, url);
        editor.apply();
    }

    /**
     * Gets area type.
     *
     * @return the area type
     */
    public int getAreaType() {
        return settings.getInt(Const.KEY_PREF_AREA_TYPE, Opts.AREA_POLYGON);
    }

    /**
     * Sets area type.
     *
     * @param areaType the area type
     */
    public void setAreaType(int areaType) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Const.KEY_PREF_AREA_TYPE, areaType);
        editor.apply();
    }

    /**
     * Gets area use.
     *
     * @return the area use
     */
    public boolean getAreaUse() {
        return settings.getBoolean(Const.KEY_PREF_AREA_USE, true);
    }

    /**
     * Sets area use.
     *
     * @param isUsed the is used
     */
    public void setAreaUse(boolean isUsed) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Const.KEY_PREF_AREA_USE, isUsed);
        editor.apply();
    }

    /**
     * Gets time use.
     *
     * @return the time use
     */
    public boolean getTimeUse() {
        return settings.getBoolean(Const.KEY_PREF_TIME_USE, true);
    }

    /**
     * Sets time use.
     *
     * @param isUsed the is used
     */
    public void setTimeUse(boolean isUsed) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Const.KEY_PREF_TIME_USE, isUsed);
        editor.apply();
    }

    /**
     * Sets date time prefs.
     *
     * @param calStartStr the cal start str
     * @param calEndStr   the cal end str
     */
    public void setDateTimePrefs(String calStartStr, String calEndStr) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_DATETIME_START, calStartStr);
        editor.putString(Const.KEY_PREF_DATETIME_END, calEndStr);
        editor.apply();
    }

    /**
     * Gets login prefs.
     *
     * @return the login prefs
     */
    public String getLoginPrefs() {
        return settings.getString(Const.KEY_PREF_LOGIN, "");
    }

    /**
     * Sets login prefs.
     *
     * @param login the login
     */
    public void setLoginPrefs(String login) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_LOGIN, login);
        editor.apply();
    }

    /**
     * Gets password prefs.
     *
     * @return the password prefs
     */
    public String getPasswordPrefs() {
        return settings.getString(Const.KEY_PREF_PASSWORD, "");
    }

    /**
     * Sets password prefs.
     *
     * @param password the password
     */
    public void setPasswordPrefs(String password) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Const.KEY_PREF_PASSWORD, password);
        editor.apply();
    }

    /**
     * Gets last sync prefs.
     *
     * @return the last sync prefs
     */
    public long getLastSyncPrefs() {
        return settings.getLong(Const.KEY_PREF_GLOBAL_LAST_SYNC_TIME, 0);
    }

    /**
     * Sets last sync prefs.
     *
     * @param time the time
     */
    public void setLastSyncPrefs(long time) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(Const.KEY_PREF_GLOBAL_LAST_SYNC_TIME, time);
        editor.apply();
    }

    /**
     * Sets shared pref default value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setSharedPrefDefaultValue(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
