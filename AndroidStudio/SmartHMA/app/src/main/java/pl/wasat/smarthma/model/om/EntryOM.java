package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.model.feed.Group;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class EntryOM implements Serializable {

    private static final long serialVersionUID = 1L;


    public static final String KEY_RSS_ENTRY = "KEY_RSS_ENTRY";


    private String id;
    private ArrayList<Link> links;
    private String published;
    private String title;
    private String updated;
    private String summary;
    private EarthObservation earthObservation;
    private Where where;
    private Group group;


    private String guid;
    private long dbId;
    private String identifier;
    private String date;
    private boolean read;
    private boolean offline;


    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntryOM withId(String id) {
        this.id = id;
        return this;
    }


    /**
     * @return the links
     */
    public ArrayList<Link> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public EntryOM withLink(ArrayList<Link> links) {
        this.links = links;
        return this;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public EntryOM withPublished(String published) {
        this.published = published;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EntryOM withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public EntryOM withUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = extractCData(summary);
    }

    public EntryOM withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public EarthObservation getEarthObservation() {
        return earthObservation;
    }

    public void setEarthObservation(EarthObservation earthObservation) {
        this.earthObservation = earthObservation;
    }

    public EntryOM withEarthObservation(EarthObservation earthObservation) {
        this.earthObservation = earthObservation;
        return this;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public EntryOM withWhere(Where where) {
        this.where = where;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public EntryOM withGroup(Group group) {
        this.group = group;
        return this;
    }


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public long getDbId() {
        return dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    private String extractCData(String data) {
        data = data.replaceAll("<!\\[CDATA\\[", "");
        data = data.replaceAll("\\]\\]>", "");
        return data;
    }

}
