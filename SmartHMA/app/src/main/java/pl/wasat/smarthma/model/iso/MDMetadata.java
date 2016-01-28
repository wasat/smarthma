package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class MDMetadata implements Serializable {

    private static final long serialVersionUID = 1L;

    private FileIdentifier fileIdentifier;
    private Language language;
    private HierarchyLevel hierarchyLevel;
    private Contact contact;
    private DateStamp dateStamp;
    private MetadataStandardName metadataStandardName;
    private MetadataStandardVersion metadataStandardVersion;
    private IdentificationInfo identificationInfo;
    private DataQualityInfo dataQualityInfo;
    private String XmlnsGmd;
    private String Xmlns;
    private String XmlnsGco;
    private String XmlnsGmx;
    private String XmlnsGsr;
    private String XmlnsGss;
    private String XmlnsGts;
    private String XmlnsXsi;
    private String XmlnsGml;
    private String XmlnsXlink;
    private String Id;
    private String XsiSchemaLocation;
    private String Prefix;


    /**
     * @return The fileIdentifier
     */
    public FileIdentifier getFileIdentifier() {
        return fileIdentifier;
    }

    /**
     * @param fileIdentifier The fileIdentifier
     */
    public void setFileIdentifier(FileIdentifier fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    /**
     * @return The language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * @return The hierarchyLevel
     */
    public HierarchyLevel getHierarchyLevel() {
        return hierarchyLevel;
    }

    /**
     * @param hierarchyLevel The hierarchyLevel
     */
    public void setHierarchyLevel(HierarchyLevel hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    /**
     * @return The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return The dateStamp
     */
    public DateStamp getDateStamp() {
        return dateStamp;
    }

    /**
     * @param dateStamp The dateStamp
     */
    public void setDateStamp(DateStamp dateStamp) {
        this.dateStamp = dateStamp;
    }

    /**
     * @return The metadataStandardName
     */
    public MetadataStandardName getMetadataStandardName() {
        return metadataStandardName;
    }

    /**
     * @param metadataStandardName The metadataStandardName
     */
    public void setMetadataStandardName(
            MetadataStandardName metadataStandardName) {
        this.metadataStandardName = metadataStandardName;
    }

    /**
     * @return The metadataStandardVersion
     */
    public MetadataStandardVersion getMetadataStandardVersion() {
        return metadataStandardVersion;
    }

    /**
     * @param metadataStandardVersion The metadataStandardVersion
     */
    public void setMetadataStandardVersion(
            MetadataStandardVersion metadataStandardVersion) {
        this.metadataStandardVersion = metadataStandardVersion;
    }

    /**
     * @return The identificationInfo
     */
    public IdentificationInfo getIdentificationInfo() {
        return identificationInfo;
    }

    /**
     * @param identificationInfo The identificationInfo
     */
    public void setIdentificationInfo(IdentificationInfo identificationInfo) {
        this.identificationInfo = identificationInfo;
    }

    /**
     * @return The dataQualityInfo
     */
    public DataQualityInfo getDataQualityInfo() {
        return dataQualityInfo;
    }

    /**
     * @param dataQualityInfo The dataQualityInfo
     */
    public void setDataQualityInfo(DataQualityInfo dataQualityInfo) {
        this.dataQualityInfo = dataQualityInfo;
    }

    /**
     * @return The XmlnsGmd
     */
    public String getXmlnsGmd() {
        return XmlnsGmd;
    }

    /**
     * @param XmlnsGmd The _xmlns:gmd
     */
    public void setXmlnsGmd(String XmlnsGmd) {
        this.XmlnsGmd = XmlnsGmd;
    }

    /**
     * @return The Xmlns
     */
    public String getXmlns() {
        return Xmlns;
    }

    /**
     * @param Xmlns The _xmlns
     */
    public void setXmlns(String Xmlns) {
        this.Xmlns = Xmlns;
    }

    /**
     * @return The XmlnsGco
     */
    public String getXmlnsGco() {
        return XmlnsGco;
    }

    /**
     * @param XmlnsGco The _xmlns:gco
     */
    public void setXmlnsGco(String XmlnsGco) {
        this.XmlnsGco = XmlnsGco;
    }

    /**
     * @return The XmlnsGmx
     */
    public String getXmlnsGmx() {
        return XmlnsGmx;
    }

    /**
     * @param XmlnsGmx The _xmlns:gmx
     */
    public void setXmlnsGmx(String XmlnsGmx) {
        this.XmlnsGmx = XmlnsGmx;
    }

    /**
     * @return The XmlnsGsr
     */
    public String getXmlnsGsr() {
        return XmlnsGsr;
    }

    /**
     * @param XmlnsGsr The _xmlns:gsr
     */
    public void setXmlnsGsr(String XmlnsGsr) {
        this.XmlnsGsr = XmlnsGsr;
    }

    /**
     * @return The XmlnsGss
     */
    public String getXmlnsGss() {
        return XmlnsGss;
    }

    /**
     * @param XmlnsGss The _xmlns:gss
     */
    public void setXmlnsGss(String XmlnsGss) {
        this.XmlnsGss = XmlnsGss;
    }

    /**
     * @return The XmlnsGts
     */
    public String getXmlnsGts() {
        return XmlnsGts;
    }

    /**
     * @param XmlnsGts The _xmlns:gts
     */
    public void setXmlnsGts(String XmlnsGts) {
        this.XmlnsGts = XmlnsGts;
    }

    /**
     * @return The XmlnsXsi
     */
    public String getXmlnsXsi() {
        return XmlnsXsi;
    }

    /**
     * @param XmlnsXsi The _xmlns:xsi
     */
    public void setXmlnsXsi(String XmlnsXsi) {
        this.XmlnsXsi = XmlnsXsi;
    }

    /**
     * @return The XmlnsGml
     */
    public String getXmlnsGml() {
        return XmlnsGml;
    }

    /**
     * @param XmlnsGml The _xmlns:gml
     */
    public void setXmlnsGml(String XmlnsGml) {
        this.XmlnsGml = XmlnsGml;
    }

    /**
     * @return The XmlnsXlink
     */
    public String getXmlnsXlink() {
        return XmlnsXlink;
    }

    /**
     * @param XmlnsXlink The _xmlns:xlink
     */
    public void setXmlnsXlink(String XmlnsXlink) {
        this.XmlnsXlink = XmlnsXlink;
    }

    /**
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return The XsiSchemaLocation
     */
    public String getXsiSchemaLocation() {
        return XsiSchemaLocation;
    }

    /**
     * @param XsiSchemaLocation The _xsi:schemaLocation
     */
    public void setXsiSchemaLocation(String XsiSchemaLocation) {
        this.XsiSchemaLocation = XsiSchemaLocation;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The _prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
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

}
