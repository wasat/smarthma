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
     * @return The links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links The links
     */
    public void setLinks(List<Link> links) {
        this.links = links;
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

    public MetadataType getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(MetadataType metadataType) {
        this.metadataType = metadataType;
    }

    public String getRawMetadata() {
        return rawMetadata;
    }

    public void setRawMetadata(String rawMetadata) {
        this.rawMetadata = rawMetadata;
    }

    public SimpleMetadata getSimpleMetadata() {
        return simpleMetadata;
    }

    public void setSimpleMetadata(SimpleMetadata simpleMetadata) {
        this.simpleMetadata = simpleMetadata;
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

    /**
     * @return The MDMetadata
     */
    public MDMetadata getMDMetadata() {
        return MDMetadata;
    }

    /**
     * @param MDMetadata The MD_Metadata
     */
    public void setMDMetadata(MDMetadata MDMetadata) {
        this.MDMetadata = MDMetadata;
    }

    public EarthObservation getEarthObservation() {
        return earthObservation;
    }

    public void setEarthObservation(EarthObservation earthObservation) {
        this.earthObservation = earthObservation;
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

    public boolean simpleEquals(Entry o) {
        //return super.equals(other);
        return title.equals(o.getTitle()) && id.equals(o.getId()) && identifier.equals(o.getIdentifier()) && guid.equals(o.getGuid()) &&
                date.equals(o.getDate()) && updated.equals(o.getUpdated()) && dbId == o.getDbId() && /*earthObservation.equals(o.getEarthObservation()) &&*/
                group.equals(o.getGroup()) && links.equals(o.getLinks()) && published.equals(o.getPublished()) && summary.equals(o.getSummary()) &&
                where.equals(o.getWhere());
    }

    public Entry safeClone()
    {
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
        testEntry.setEarthObservation(earthObservation);
        testEntry.setFavourite(isFavourite);
        return testEntry;
    }

    private String extractCData(String data) {
        data = data.replaceAll("<!\\[CDATA\\[", "");
        data = data.replaceAll("\\]\\]>", "");
        return data;
    }


}
