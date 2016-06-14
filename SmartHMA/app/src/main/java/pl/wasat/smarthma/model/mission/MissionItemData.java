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

package pl.wasat.smarthma.model.mission;

import java.io.Serializable;

import pl.wasat.smarthma.parser.model.Mission;

/**
 * The type Mission item data.
 */
public class MissionItemData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String contentLink;
    private String imgLink;
    private String summary;

    /**
     * Instantiates a new Mission item data.
     */
    public MissionItemData() {
    }

    /**
     * Instantiates a new Mission item data.
     *
     * @param name the name
     */
    public MissionItemData(String name) {
        super();
        this.id = 999;
        this.name = name;
        this.contentLink = "http://";
        this.imgLink = "http://";
        this.summary = "summary";
    }

    /**
     * Instantiates a new Mission item data.
     *
     * @param mission the mission
     */
    public MissionItemData(Mission mission) {
        super();
        this.id = mission.getId();
        this.name = mission.getName();
        this.contentLink = "";
        this.imgLink = mission.getImageUrl();
        this.summary = mission.getData();
    }

    /**
     * Instantiates a new Mission item data.
     *
     * @param id          the id
     * @param name        the name
     * @param contentLink the content link
     * @param imgLink     the img link
     * @param summary     the summary
     */
    public MissionItemData(int id, String name, String contentLink,
                           String imgLink, String summary) {
        super();
        this.id = id;
        this.name = name;
        this.contentLink = contentLink;
        this.imgLink = imgLink;
        this.summary = summary;
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
     * Gets content link.
     *
     * @return the content link
     */
    public String getContentLink() {
        return contentLink;
    }

    /**
     * Sets content link.
     *
     * @param contentLink the content link
     */
    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    /**
     * Gets img link.
     *
     * @return the img link
     */
    public String getImgLink() {
        return imgLink;
    }

    /**
     * Sets img link.
     *
     * @param imgLink the img link
     */
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

}