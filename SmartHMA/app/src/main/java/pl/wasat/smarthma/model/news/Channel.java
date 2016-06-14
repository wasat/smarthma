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
import java.util.List;

import pl.wasat.smarthma.model.feed.Link;

/**
 * Created by Daniel on 2016-03-10.
 * This file is a part of module SmartHMA project.
 */
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;
    private String language;
    private String ttl;
    private String copyright;
    private String managingEditor;
    private String title;
    private String description;
    private Link link;
    private String pubDate;
    private String lastBuildDate;
    private String docs;
    private List<NewsArticle> items;

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets ttl.
     *
     * @return the ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * Sets ttl.
     *
     * @param ttl the ttl
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * Gets copyright.
     *
     * @return the copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets copyright.
     *
     * @param copyright the copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Gets managing editor.
     *
     * @return the managing editor
     */
    public String getManagingEditor() {
        return managingEditor;
    }

    /**
     * Sets managing editor.
     *
     * @param managingEditor the managing editor
     */
    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Gets pub date.
     *
     * @return the pub date
     */
    public String getPubDate() {
        return pubDate;
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
     * Gets last build date.
     *
     * @return the last build date
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Sets last build date.
     *
     * @param lastBuildDate the last build date
     */
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    /**
     * Gets docs.
     *
     * @return the docs
     */
    public String getDocs() {
        return docs;
    }

    /**
     * Sets docs.
     *
     * @param docs the docs
     */
    public void setDocs(String docs) {
        this.docs = docs;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<NewsArticle> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<NewsArticle> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
