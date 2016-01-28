package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * Created by Daniel Zinkiewicz on 11.02.15 01:47.
 * Part of the project  ${PROJECT_NAME}
 */
public class ISOPrefixes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Xmlns;
    private String XmlnsDc;
    private String XmlnsGeo;
    private String XmlnsGeorss;
    private String XmlnsGml;
    private String XmlnsOs;
    private String XmlnsSemantic;
    private String XmlnsSru;
    private String XmlnsTime;
    private String XmlnsUrlencoder;
    private String XmlnsWrs;
    private String XmlnsXlink;


    public String getXmlns() {
        return Xmlns;
    }

    public void setXmlns(String xmlns) {
        Xmlns = xmlns;
    }

    public String getXmlnsDc() {
        return XmlnsDc;
    }

    public void setXmlnsDc(String xmlnsDc) {
        XmlnsDc = xmlnsDc;
    }

    public String getXmlnsGeo() {
        return XmlnsGeo;
    }

    public void setXmlnsGeo(String xmlnsGeo) {
        XmlnsGeo = xmlnsGeo;
    }

    public String getXmlnsGeorss() {
        return XmlnsGeorss;
    }

    public void setXmlnsGeorss(String xmlnsGeorss) {
        XmlnsGeorss = xmlnsGeorss;
    }

    public String getXmlnsGml() {
        return XmlnsGml;
    }

    public void setXmlnsGml(String xmlnsGml) {
        XmlnsGml = xmlnsGml;
    }

    public String getXmlnsOs() {
        return XmlnsOs;
    }

    public void setXmlnsOs(String xmlnsOs) {
        XmlnsOs = xmlnsOs;
    }

    public String getXmlnsSemantic() {
        return XmlnsSemantic;
    }

    public void setXmlnsSemantic(String xmlnsSemantic) {
        XmlnsSemantic = xmlnsSemantic;
    }

    public String getXmlnsSru() {
        return XmlnsSru;
    }

    public void setXmlnsSru(String xmlnsSru) {
        XmlnsSru = xmlnsSru;
    }

    public String getXmlnsTime() {
        return XmlnsTime;
    }

    public void setXmlnsTime(String xmlnsTime) {
        XmlnsTime = xmlnsTime;
    }

    public String getXmlnsUrlencoder() {
        return XmlnsUrlencoder;
    }

    public void setXmlnsUrlencoder(String xmlnsUrlencoder) {
        XmlnsUrlencoder = xmlnsUrlencoder;
    }

    public String getXmlnsWrs() {
        return XmlnsWrs;
    }

    public void setXmlnsWrs(String xmlnsWrs) {
        XmlnsWrs = xmlnsWrs;
    }

    public String getXmlnsXlink() {
        return XmlnsXlink;
    }

    public void setXmlnsXlink(String xmlnsXlink) {
        XmlnsXlink = xmlnsXlink;
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
