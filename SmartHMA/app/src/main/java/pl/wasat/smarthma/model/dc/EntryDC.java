

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


/**
 * The type Entry dc.
 */
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
     * Gets id.
     *
     * @return The id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets id.
     *
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets link.
     *
     * @return The link
     */
    public Link getLink() {
        return link;
    }


    /**
     * Sets link.
     *
     * @param link The link
     */
    public void setLink(Link link) {
        this.link = link;
    }


    /**
     * Gets published.
     *
     * @return The published
     */
    public String getPublished() {
        return published;
    }


    /**
     * Sets published.
     *
     * @param published The published
     */
    public void setPublished(String published) {
        this.published = published;
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
     * Gets updated.
     *
     * @return The updated
     */
    public String getUpdated() {
        return updated;
    }


    /**
     * Sets updated.
     *
     * @param updated The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }


    /**
     * Gets summary.
     *
     * @return The summary
     */
    public Summary getSummary() {
        return summary;
    }


    /**
     * Sets summary.
     *
     * @param summary The summary
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }


    /**
     * Gets polygon.
     *
     * @return The polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }


    /**
     * Sets polygon.
     *
     * @param polygon The polygon
     */
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }


    /**
     * Gets dc.
     *
     * @return The dc
     */
    public Dc getDc() {
        return dc;
    }


    /**
     * Sets dc.
     *
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