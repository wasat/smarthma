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
public class Mission {
    private int id;
    private int category_id;
    private String name;
    private String data;
    private String imageUrl;

    /**
     * Instantiates a new Mission.
     *
     * @param id          the id
     * @param category_id the category id
     * @param name        the name
     * @param data        the data
     * @param imageUrl    the image url
     */
    public Mission(int id, int category_id, String name, String data, String imageUrl) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.data = data;
        this.imageUrl = imageUrl;
    }

    /**
     * Instantiates a new Mission.
     *
     * @param id          the id
     * @param category_id the category id
     * @param name        the name
     * @param data        the data
     */
    public Mission(int id, int category_id, String name, String data) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.data = data;
    }

    /**
     * Instantiates a new Mission.
     *
     * @param id          the id
     * @param category_id the category id
     * @param name        the name
     */
    public Mission(int id, int category_id, String name) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
    }

    /**
     * Instantiates a new Mission.
     */
    public Mission() {
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

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
