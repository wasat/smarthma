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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public List<NewsArticle> getItems() {
        return items;
    }

    public void setItems(List<NewsArticle> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
