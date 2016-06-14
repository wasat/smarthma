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

package pl.wasat.smarthma.model.news;

import java.io.Serializable;

import pl.wasat.smarthma.model.feed.Link;

/**
 * The type News article.
 */
public class NewsArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The constant KEY.
     */
    public static final String KEY = "pl.wasat.smarthma.NEWS_ARTICLE";

    private String guid;
    private String title;
    private String description;
    private String pubDate;
    private Link link;

    private String author;
    private String encodedContent;

    private boolean read;
    private boolean offline;
    private long dbId;

    /**
     * Gets guid.
     *
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets guid.
     *
     * @param guid the guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
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
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public Link getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = extractCData(description);
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets pub date.
     *
     * @param pubDate the pub date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * Gets pub date.
     *
     * @return the pub date
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets encoded content.
     *
     * @param encodedContent the encoded content
     */
    public void setEncodedContent(String encodedContent) {
        this.encodedContent = extractCData(encodedContent);
    }

    /**
     * Gets encoded content.
     *
     * @return the encoded content
     */
    public String getEncodedContent() {
        return encodedContent;
    }

    /**
     * Is read boolean.
     *
     * @return the boolean
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets read.
     *
     * @param read the read
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Is offline boolean.
     *
     * @return the boolean
     */
    public boolean isOffline() {
        return offline;
    }

    /**
     * Sets offline.
     *
     * @param offline the offline
     */
    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    /**
     * Gets db id.
     *
     * @return the db id
     */
    public long getDbId() {
        return dbId;
    }

    /**
     * Sets db id.
     *
     * @param dbId the db id
     */
    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    private String extractCData(String data) {
        data = data.replaceAll("<!\\[CDATA\\[", "");
        data = data.replaceAll("\\]\\]>", "");
        return data;
    }

}