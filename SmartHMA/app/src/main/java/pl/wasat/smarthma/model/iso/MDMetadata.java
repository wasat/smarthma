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

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Md metadata.
 */
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
     * Gets file identifier.
     *
     * @return The fileIdentifier
     */
    public FileIdentifier getFileIdentifier() {
        return fileIdentifier;
    }

    /**
     * Sets file identifier.
     *
     * @param fileIdentifier The fileIdentifier
     */
    public void setFileIdentifier(FileIdentifier fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    /**
     * Gets language.
     *
     * @return The language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language The language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Gets hierarchy level.
     *
     * @return The hierarchyLevel
     */
    public HierarchyLevel getHierarchyLevel() {
        return hierarchyLevel;
    }

    /**
     * Sets hierarchy level.
     *
     * @param hierarchyLevel The hierarchyLevel
     */
    public void setHierarchyLevel(HierarchyLevel hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    /**
     * Gets contact.
     *
     * @return The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets contact.
     *
     * @param contact The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Gets date stamp.
     *
     * @return The dateStamp
     */
    public DateStamp getDateStamp() {
        return dateStamp;
    }

    /**
     * Sets date stamp.
     *
     * @param dateStamp The dateStamp
     */
    public void setDateStamp(DateStamp dateStamp) {
        this.dateStamp = dateStamp;
    }

    /**
     * Gets metadata standard name.
     *
     * @return The metadataStandardName
     */
    public MetadataStandardName getMetadataStandardName() {
        return metadataStandardName;
    }

    /**
     * Sets metadata standard name.
     *
     * @param metadataStandardName The metadataStandardName
     */
    public void setMetadataStandardName(
            MetadataStandardName metadataStandardName) {
        this.metadataStandardName = metadataStandardName;
    }

    /**
     * Gets metadata standard version.
     *
     * @return The metadataStandardVersion
     */
    public MetadataStandardVersion getMetadataStandardVersion() {
        return metadataStandardVersion;
    }

    /**
     * Sets metadata standard version.
     *
     * @param metadataStandardVersion The metadataStandardVersion
     */
    public void setMetadataStandardVersion(
            MetadataStandardVersion metadataStandardVersion) {
        this.metadataStandardVersion = metadataStandardVersion;
    }

    /**
     * Gets identification info.
     *
     * @return The identificationInfo
     */
    public IdentificationInfo getIdentificationInfo() {
        return identificationInfo;
    }

    /**
     * Sets identification info.
     *
     * @param identificationInfo The identificationInfo
     */
    public void setIdentificationInfo(IdentificationInfo identificationInfo) {
        this.identificationInfo = identificationInfo;
    }

    /**
     * Gets data quality info.
     *
     * @return The dataQualityInfo
     */
    public DataQualityInfo getDataQualityInfo() {
        return dataQualityInfo;
    }

    /**
     * Sets data quality info.
     *
     * @param dataQualityInfo The dataQualityInfo
     */
    public void setDataQualityInfo(DataQualityInfo dataQualityInfo) {
        this.dataQualityInfo = dataQualityInfo;
    }

    /**
     * Gets xmlns gmd.
     *
     * @return The XmlnsGmd
     */
    public String getXmlnsGmd() {
        return XmlnsGmd;
    }

    /**
     * Sets xmlns gmd.
     *
     * @param XmlnsGmd The _xmlns:gmd
     */
    public void setXmlnsGmd(String XmlnsGmd) {
        this.XmlnsGmd = XmlnsGmd;
    }

    /**
     * Gets xmlns.
     *
     * @return The Xmlns
     */
    public String getXmlns() {
        return Xmlns;
    }

    /**
     * Sets xmlns.
     *
     * @param Xmlns The _xmlns
     */
    public void setXmlns(String Xmlns) {
        this.Xmlns = Xmlns;
    }

    /**
     * Gets xmlns gco.
     *
     * @return The XmlnsGco
     */
    public String getXmlnsGco() {
        return XmlnsGco;
    }

    /**
     * Sets xmlns gco.
     *
     * @param XmlnsGco The _xmlns:gco
     */
    public void setXmlnsGco(String XmlnsGco) {
        this.XmlnsGco = XmlnsGco;
    }

    /**
     * Gets xmlns gmx.
     *
     * @return The XmlnsGmx
     */
    public String getXmlnsGmx() {
        return XmlnsGmx;
    }

    /**
     * Sets xmlns gmx.
     *
     * @param XmlnsGmx The _xmlns:gmx
     */
    public void setXmlnsGmx(String XmlnsGmx) {
        this.XmlnsGmx = XmlnsGmx;
    }

    /**
     * Gets xmlns gsr.
     *
     * @return The XmlnsGsr
     */
    public String getXmlnsGsr() {
        return XmlnsGsr;
    }

    /**
     * Sets xmlns gsr.
     *
     * @param XmlnsGsr The _xmlns:gsr
     */
    public void setXmlnsGsr(String XmlnsGsr) {
        this.XmlnsGsr = XmlnsGsr;
    }

    /**
     * Gets xmlns gss.
     *
     * @return The XmlnsGss
     */
    public String getXmlnsGss() {
        return XmlnsGss;
    }

    /**
     * Sets xmlns gss.
     *
     * @param XmlnsGss The _xmlns:gss
     */
    public void setXmlnsGss(String XmlnsGss) {
        this.XmlnsGss = XmlnsGss;
    }

    /**
     * Gets xmlns gts.
     *
     * @return The XmlnsGts
     */
    public String getXmlnsGts() {
        return XmlnsGts;
    }

    /**
     * Sets xmlns gts.
     *
     * @param XmlnsGts The _xmlns:gts
     */
    public void setXmlnsGts(String XmlnsGts) {
        this.XmlnsGts = XmlnsGts;
    }

    /**
     * Gets xmlns xsi.
     *
     * @return The XmlnsXsi
     */
    public String getXmlnsXsi() {
        return XmlnsXsi;
    }

    /**
     * Sets xmlns xsi.
     *
     * @param XmlnsXsi The _xmlns:xsi
     */
    public void setXmlnsXsi(String XmlnsXsi) {
        this.XmlnsXsi = XmlnsXsi;
    }

    /**
     * Gets xmlns gml.
     *
     * @return The XmlnsGml
     */
    public String getXmlnsGml() {
        return XmlnsGml;
    }

    /**
     * Sets xmlns gml.
     *
     * @param XmlnsGml The _xmlns:gml
     */
    public void setXmlnsGml(String XmlnsGml) {
        this.XmlnsGml = XmlnsGml;
    }

    /**
     * Gets xmlns xlink.
     *
     * @return The XmlnsXlink
     */
    public String getXmlnsXlink() {
        return XmlnsXlink;
    }

    /**
     * Sets xmlns xlink.
     *
     * @param XmlnsXlink The _xmlns:xlink
     */
    public void setXmlnsXlink(String XmlnsXlink) {
        this.XmlnsXlink = XmlnsXlink;
    }

    /**
     * Gets id.
     *
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * Sets id.
     *
     * @param Id The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * Gets xsi schema location.
     *
     * @return The XsiSchemaLocation
     */
    public String getXsiSchemaLocation() {
        return XsiSchemaLocation;
    }

    /**
     * Sets xsi schema location.
     *
     * @param XsiSchemaLocation The _xsi:schemaLocation
     */
    public void setXsiSchemaLocation(String XsiSchemaLocation) {
        this.XsiSchemaLocation = XsiSchemaLocation;
    }

    /**
     * Gets prefix.
     *
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * Sets prefix.
     *
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
