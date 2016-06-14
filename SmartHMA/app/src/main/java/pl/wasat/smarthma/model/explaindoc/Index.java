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

package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Index.
 */
public class Index implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private ArrayList<String> map;
    private ConfigInfo configInfo;

    /**
     * Instantiates a new Index.
     */
    public Index() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public ArrayList<String> getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map to set
     */
    public void setMap(ArrayList<String> map) {
        this.map = map;
    }

    /**
     * Gets config info.
     *
     * @return the configInfo
     */
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    /**
     * Sets config info.
     *
     * @param configInfo the configInfo to set
     */
    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((configInfo == null) ? 0 : configInfo.hashCode());
        result = prime * result + id;
        result = prime * result + ((map == null) ? 0 : map.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Index other = (Index) obj;
        if (configInfo == null) {
            if (other.configInfo != null)
                return false;
        } else if (!configInfo.equals(other.configInfo))
            return false;
        if (id != other.id)
            return false;
        if (map == null) {
            if (other.map != null)
                return false;
        } else if (!map.equals(other.map))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Index [title=" + title + ", map=" + map + ", configInfo="
                + configInfo + "]";
    }

}
