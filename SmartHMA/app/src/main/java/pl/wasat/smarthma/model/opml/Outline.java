
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

package pl.wasat.smarthma.model.opml;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Outline.
 */
public class Outline {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("htmlUrl")
    @Expose
    private String htmlUrl;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("xmlUrl")
    @Expose
    private String xmlUrl;

    /**
     * Gets text.
     *
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets description.
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets html url.
     *
     * @return The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Sets html url.
     *
     * @param htmlUrl The htmlUrl
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * Gets language.
     *
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets title.
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets type.
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets version.
     *
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets xml url.
     *
     * @return The xmlUrl
     */
    public String getXmlUrl() {
        return xmlUrl;
    }

    /**
     * Sets xml url.
     *
     * @param xmlUrl The xmlUrl
     */
    public void setXmlUrl(String xmlUrl) {
        this.xmlUrl = xmlUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
