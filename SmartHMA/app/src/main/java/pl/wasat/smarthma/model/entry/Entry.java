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

package pl.wasat.smarthma.model.entry;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.helper.enums.MetadataType;
import pl.wasat.smarthma.model.dc.Dc;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.iso.MDMetadata;
import pl.wasat.smarthma.model.om.EarthObservation;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


/**
 * The type Entry.
 */
@SuppressWarnings("WeakerAccess")
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String published;
    private String title;
    private String updated;
    private List<Link> links;
    private Summary summary;
    private Polygon polygon;
    private Where where;
    private Group group;

    private MetadataType metadataType;
    private String rawMetadata;
    private SimpleMetadata simpleMetadata;
    private Dc dc;
    private MDMetadata MDMetadata;
    private EarthObservation earthObservation;

    private String guid;
    private long dbId;
    private String identifier;
    private String date;
    private boolean read;
    private boolean offline;
    private boolean isFavourite;

    /**
     * Instantiates a new Entry.
     */
    public Entry() {
        this.links = new ArrayList<>();
        this.where = new Where();
        this.group = new Group();
        this.metadataType = MetadataType.NONE;
        //this.MDMetadata = new MDMetadata();
        //this.earthObservation = new EarthObservation();
        //this.dc = new Dc();

        this.guid = "";
        this.identifier = "";
        this.date = "";
    }

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
     * Gets where.
     *
     * @return the where
     */
    public Where getWhere() {
        return where;
    }

    /**
     * Sets where.
     *
     * @param where the where
     */
    public void setWhere(Where where) {
        this.where = where;
    }

    /**
     * Gets group.
     *
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets group.
     *
     * @param group the group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Gets metadata type.
     *
     * @return the metadata type
     */
    public MetadataType getMetadataType() {
        return metadataType;
    }

    /**
     * Sets metadata type.
     *
     * @param metadataType the metadata type
     */
    public void setMetadataType(MetadataType metadataType) {
        this.metadataType = metadataType;
    }

    /**
     * Gets raw metadata.
     *
     * @return the raw metadata
     */
    public String getRawMetadata() {
        return rawMetadata;
    }

    /**
     * Sets raw metadata.
     *
     * @param rawMetadata the raw metadata
     */
    public void setRawMetadata(String rawMetadata) {
        this.rawMetadata = rawMetadata;
    }

    /**
     * Gets simple metadata.
     *
     * @return the simple metadata
     */
    public SimpleMetadata getSimpleMetadata() {
        if (simpleMetadata == null) simpleMetadata = new SimpleMetadata(this);
        return simpleMetadata;
    }

    /**
     * Sets simple metadata.
     *
     * @param simpleMetadata the simple metadata
     */
    public void setSimpleMetadata(SimpleMetadata simpleMetadata) {
        this.simpleMetadata = simpleMetadata;
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

    /**
     * Gets md metadata.
     *
     * @return The MDMetadata
     */
    public MDMetadata getMDMetadata() {
        return MDMetadata;
    }

    /**
     * Sets md metadata.
     *
     * @param MDMetadata The MD_Metadata
     */
    public void setMDMetadata(MDMetadata MDMetadata) {
        this.MDMetadata = MDMetadata;
    }

    /**
     * Gets earth observation.
     *
     * @return the earth observation
     */
    public EarthObservation getEarthObservation() {
        return earthObservation;
    }

    /**
     * Sets earth observation.
     *
     * @param earthObservation the earth observation
     */
    public void setEarthObservation(EarthObservation earthObservation) {
        this.earthObservation = earthObservation;
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
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
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

    /**
     * Generate simple metadata.
     */
    public void generateSimpleMetadata() {
        this.simpleMetadata = new SimpleMetadata(this);
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
    public boolean simpleEquals(Entry o) {
        //return super.equals(other);
        return title.equals(o.getTitle()) && id.equals(o.getId()) && identifier.equals(o.getIdentifier()) && guid.equals(o.getGuid()) &&
                date.equals(o.getDate()) && updated.equals(o.getUpdated()) && dbId == o.getDbId() && /*earthObservation.equals(o.getEarthObservation()) &&*/
                group.equals(o.getGroup()) && links.equals(o.getLinks()) && published.equals(o.getPublished()) && summary.equals(o.getSummary()) &&
                where.equals(o.getWhere());
    }

    /**
     * Safe clone entry.
     *
     * @return the entry
     */
    public Entry safeClone() {

        Entry testEntry = new Entry();
        testEntry.setTitle(title);
        testEntry.setId(id);
        testEntry.setIdentifier(identifier);
        testEntry.setGuid(guid);
        testEntry.setDate(date);
        testEntry.setUpdated(updated);
        testEntry.setDbId(dbId);
        testEntry.setGroup(group);
        testEntry.setLinks(links);
        testEntry.setPublished(published);
        testEntry.setSummary(summary);
        testEntry.setWhere(where);
        testEntry.setFavourite(isFavourite);

        testEntry.setMetadataType(metadataType);
        testEntry.setRawMetadata(rawMetadata);
        //testEntry.setSimpleMetadata(simpleMetadata);
        testEntry.setDc(dc);
        testEntry.setMDMetadata(getMDMetadata());
        testEntry.setEarthObservation(earthObservation);

        return testEntry;
    }

    private String extractCData(String data) {
        data = data.replaceAll("<!\\[CDATA\\[", "");
        data = data.replaceAll("\\]\\]>", "");
        return data;
    }


}
