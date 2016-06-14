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

package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.entry.Polygon;
import pl.wasat.smarthma.model.entry.Summary;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Entry iso.
 */
public class EntryISO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id = "";
    private String title = "";
    private String identifier = "";
    private String updated = "";
    private Date date = new Date();
    private Polygon polygon = new Polygon();
    private Summary summary = new Summary();
    private List<Link> links = new ArrayList<>();
    private MDMetadata MDMetadata = new MDMetadata();
    private String XmlLang = "";

    private String guid = "";
    private long dbId;
    private boolean read;
    private boolean offline;
    private boolean isFavourite;

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
     * Gets title.
     *
     * @return The title
     */
    public String getTitle() {
        if (title != null) {
            return title;
        } else {
            return "(null)";
        }
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
     * Gets identifier.
     *
     * @return The identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier The identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
     * Gets date.
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
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
     * Gets links.
     *
     * @return The links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links The links
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Gets spec link.
     *
     * @param linkRel  the link rel
     * @param linkType the link type
     * @return the spec link
     */
    public String getSpecLink(String linkRel, String linkType) {
        for (Link link : links) {
            if (link.getRel().equalsIgnoreCase(linkRel)
                    && link.getType().equalsIgnoreCase(linkType))
                return link.getHref();
        }
        return "";
    }

    /**
     * Gets md metadata.
     *
     * @return The MDMetadata
     */
    public pl.wasat.smarthma.model.iso.MDMetadata getMDMetadata() {
        return MDMetadata;
    }

    /**
     * Sets md metadata.
     *
     * @param MDMetadata The MD_Metadata
     */
    public void setMDMetadata(pl.wasat.smarthma.model.iso.MDMetadata MDMetadata) {
        this.MDMetadata = MDMetadata;
    }

    /**
     * Gets xml lang.
     *
     * @return The XmlLang
     */
    public String getXmlLang() {
        return XmlLang;
    }

    /**
     * Sets xml lang.
     *
     * @param XmlLang The _xml:lang
     */
    public void setXmlLang(String XmlLang) {
        this.XmlLang = XmlLang;
    }

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

    /**
     * Is not read boolean.
     *
     * @return the boolean
     */
    public boolean isNotRead() {
        return !read;
    }

    /**
     * Sets read.
     */
    public void setRead() {
        this.read = true;
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
     * Is favourite boolean.
     *
     * @return the boolean
     */
    public boolean isFavourite() {
        return isFavourite;
    }

    /**
     * Sets favourite.
     *
     * @param favourite the favourite
     */
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

    /**
     * Simple equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    public boolean simpleEquals(EntryISO o) {
        //return super.equals(other);
        return title.equals(o.getTitle()) && id.equals(o.getId()) && identifier.equals(o.getIdentifier()) && guid.equals(o.getGuid()) &&
                date.equals(o.getDate()) && updated.equals(o.getUpdated()) && dbId == o.getDbId() && MDMetadata.equals(o.getMDMetadata()) &&
                summary.equals(o.getSummary()) && XmlLang.equals(o.getXmlLang());
    }

}
