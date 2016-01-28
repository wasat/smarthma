/*
package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

import pl.wasat.smarthma.model.entry.Group;
import pl.wasat.smarthma.model.entry.Where;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EntryOM implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String KEY_RSS_ENTRY = "KEY_RSS_ENTRY";

    private String id="";
    private ArrayList<Link> links = new ArrayList();
    private String published="";
    private String title="";
    private String updated="";
    private String summary="";
    private EarthObservation earthObservation = new EarthObservation();
    private Where where = new Where();
    private Group group = new Group();

    private String guid="";
    private long dbId;
    private String identifier="";
    private String date="";
    private boolean read;
    private boolean offline;
    private boolean isFavourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    */
/**
     * @return the links
 *//*

    public ArrayList<Link> getLinks() {
        return links;
    }

    */
/**
     * @param links the links to set
 *//*

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = extractCData(summary);
    }


    public EarthObservation getEarthObservation() {
        return earthObservation;
    }

    public void setEarthObservation(EarthObservation earthObservation) {
        this.earthObservation = earthObservation;
    }


    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        this.isFavourite = favourite;
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

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public boolean simpleEquals(EntryOM o)
    {
        //return super.equals(other);
        return title.equals(o.getTitle()) && id.equals(o.getId()) && identifier.equals(o.getIdentifier()) && guid.equals(o.getGuid()) &&
                date.equals(o.getDate()) && updated.equals(o.getUpdated()) && dbId==o.getDbId() && earthObservation.equals(o.getEarthObservation()) &&
                group.equals(o.getGroup()) && links.equals(o.getLinks()) && published.equals(o.getPublished()) && summary.equals(o.getSummary()) &&
                where.equals(o.getWhere());
    }

    private String extractCData(String data) {
        data = data.replaceAll("<!\\[CDATA\\[", "");
        data = data.replaceAll("\\]\\]>", "");
        return data;
    }

}
*/
