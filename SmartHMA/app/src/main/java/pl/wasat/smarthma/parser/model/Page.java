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

package pl.wasat.smarthma.parser.model;

/**
 * Created by marcel on 2015-08-04 00:09.
 * Part of the project  SmartHMA
 */
public class Page {
    private int id;
    private int category_id;
    private int mission_id;
    private String name;
    private String data;

    /**
     * Instantiates a new Page.
     *
     * @param id          the id
     * @param category_id the category id
     * @param mission_id  the mission id
     * @param name        the name
     * @param data        the data
     */
    public Page(int id, int category_id, int mission_id, String name, String data) {
        this.id = id;
        this.category_id = category_id;
        this.mission_id = mission_id;
        this.name = name;
        this.data = data;
    }

    /**
     * Instantiates a new Page.
     *
     * @param category_id the category id
     * @param mission_id  the mission id
     * @param name        the name
     * @param data        the data
     */
    public Page(int category_id, int mission_id, String name, String data) {
        this.category_id = category_id;
        this.mission_id = mission_id;
        this.name = name;
        this.data = data;
    }

    /**
     * Instantiates a new Page.
     */
    public Page() {
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
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * Sets category id.
     *
     * @param category_id the category id
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    /**
     * Gets mission id.
     *
     * @return the mission id
     */
    public int getMission_id() {
        return mission_id;
    }

    /**
     * Sets mission id.
     *
     * @param mission_id the mission id
     */
    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", mission_id=" + mission_id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
