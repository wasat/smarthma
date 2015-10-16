

package pl.wasat.smarthma.model.dc;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.model.entry.Polygon;
import pl.wasat.smarthma.model.entry.Summary;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class EntryDC implements Serializable {

    private String id;
    private Link link;
    private String published;
    private String title;
    private String updated;
    private Summary summary;
    private Polygon polygon;
    private Dc dc;


    /**
     * @return The id
     */

    public String getId() {
        return id;
    }


    /**
     * @param id The id
     */

    public void setId(String id) {
        this.id = id;
    }


    /**
     * @return The link
     */

    public Link getLink() {
        return link;
    }


    /**
     * @param link The link
     */

    public void setLink(Link link) {
        this.link = link;
    }


    /**
     * @return The published
     */

    public String getPublished() {
        return published;
    }


    /**
     * @param published The published
     */

    public void setPublished(String published) {
        this.published = published;
    }


    /**
     * @return The title
     */

    public String getTitle() {
        return title;
    }


    /**
     * @param title The title
     */

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return The updated
     */

    public String getUpdated() {
        return updated;
    }


    /**
     * @param updated The updated
     */

    public void setUpdated(String updated) {
        this.updated = updated;
    }


    /**
     * @return The summary
     */

    public Summary getSummary() {
        return summary;
    }


    /**
     * @param summary The summary
     */

    public void setSummary(Summary summary) {
        this.summary = summary;
    }


    /**
     * @return The polygon
     */

    public Polygon getPolygon() {
        return polygon;
    }


    /**
     * @param polygon The polygon
     */

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }


    /**
     * @return The dc
     */

    public Dc getDc() {
        return dc;
    }


    /**
     * @param dc The dc
     */

    public void setDc(Dc dc) {
        this.dc = dc;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(link).append(published).append(title).append(updated).append(summary).append(polygon).append(dc).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EntryDC)) {
            return false;
        }
        EntryDC rhs = ((EntryDC) other);
        return new EqualsBuilder().append(id, rhs.id).append(link, rhs.link).append(published, rhs.published).append(title, rhs.title).append(updated, rhs.updated).append(summary, rhs.summary).append(polygon, rhs.polygon).append(dc, rhs.dc).isEquals();
    }

}