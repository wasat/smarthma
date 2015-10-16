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

public class EntryISO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id = "";
    private String title = "";
    private String identifier = "";
    private String updated = "";
    private Date date = new Date();
    private Polygon polygon = new Polygon();
    private Summary summary = new Summary();
    private List<Link> link = new ArrayList<>();
    private MDMetadata MDMetadata = new MDMetadata();
    private String XmlLang = "";

    private String guid = "";
    private long dbId;
    private boolean read;
    private boolean offline;
    private boolean isFavourite;

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
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier The identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return The link
     */
    public List<Link> getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(List<Link> link) {
        this.link = link;
    }

    /**
     * @return The MDMetadata
     */
    public pl.wasat.smarthma.model.iso.MDMetadata getMDMetadata() {
        return MDMetadata;
    }

    /**
     * @param MDMetadata The MD_Metadata
     */
    public void setMDMetadata(pl.wasat.smarthma.model.iso.MDMetadata MDMetadata) {
        this.MDMetadata = MDMetadata;
    }

    /**
     * @return The XmlLang
     */
    public String getXmlLang() {
        return XmlLang;
    }

    /**
     * @param XmlLang The _xml:lang
     */
    public void setXmlLang(String XmlLang) {
        this.XmlLang = XmlLang;
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

    public boolean isNotRead() {
        return !read;
    }

    public void setRead(boolean read) {
        this.read = true;
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

    public boolean simpleEquals(EntryISO o) {
        //return super.equals(other);
        return title.equals(o.getTitle()) && id.equals(o.getId()) && identifier.equals(o.getIdentifier()) && guid.equals(o.getGuid()) &&
                date.equals(o.getDate()) && updated.equals(o.getUpdated()) && dbId == o.getDbId() && MDMetadata.equals(o.getMDMetadata()) &&
                summary.equals(o.getSummary()) && XmlLang.equals(o.getXmlLang());
    }

}
