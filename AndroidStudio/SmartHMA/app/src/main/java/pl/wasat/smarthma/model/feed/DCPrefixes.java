package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Daniel on 2015-10-07 13:46.
 * Part of the project  SmartHMA
 */
public class DCPrefixes implements Serializable {
    private String Xmlns;
    private String XmlnsCsw;
    private String XmlnsDc;
    private String XmlnsDct;
    private String XmlnsEo;
    private String XmlnsGeo;
    private String XmlnsGeorss;
    private String XmlnsGml;
    private String XmlnsMedia;
    private String XmlnsOs;
    private String XmlnsOws;
    private String XmlnsSru;
    private String XmlnsTime;

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
     * @return The XmlnsCsw
     */
    public String getXmlnsCsw() {
        return XmlnsCsw;
    }

    /**
     * @param XmlnsCsw The _xmlns:csw
     */
    public void setXmlnsCsw(String XmlnsCsw) {
        this.XmlnsCsw = XmlnsCsw;
    }

    /**
     * @return The XmlnsDc
     */
    public String getXmlnsDc() {
        return XmlnsDc;
    }

    /**
     * @param XmlnsDc The _xmlns:dc
     */
    public void setXmlnsDc(String XmlnsDc) {
        this.XmlnsDc = XmlnsDc;
    }

    /**
     * @return The XmlnsDct
     */
    public String getXmlnsDct() {
        return XmlnsDct;
    }

    /**
     * @param XmlnsDct The _xmlns:dct
     */
    public void setXmlnsDct(String XmlnsDct) {
        this.XmlnsDct = XmlnsDct;
    }

    /**
     * @return The XmlnsEo
     */
    public String getXmlnsEo() {
        return XmlnsEo;
    }

    /**
     * @param XmlnsEo The _xmlns:eo
     */
    public void setXmlnsEo(String XmlnsEo) {
        this.XmlnsEo = XmlnsEo;
    }

    /**
     * @return The XmlnsGeo
     */
    public String getXmlnsGeo() {
        return XmlnsGeo;
    }

    /**
     * @param XmlnsGeo The _xmlns:geo
     */
    public void setXmlnsGeo(String XmlnsGeo) {
        this.XmlnsGeo = XmlnsGeo;
    }

    /**
     * @return The XmlnsGeorss
     */
    public String getXmlnsGeorss() {
        return XmlnsGeorss;
    }

    /**
     * @param XmlnsGeorss The _xmlns:georss
     */
    public void setXmlnsGeorss(String XmlnsGeorss) {
        this.XmlnsGeorss = XmlnsGeorss;
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
     * @return The XmlnsMedia
     */
    public String getXmlnsMedia() {
        return XmlnsMedia;
    }

    /**
     * @param XmlnsMedia The _xmlns:media
     */
    public void setXmlnsMedia(String XmlnsMedia) {
        this.XmlnsMedia = XmlnsMedia;
    }

    /**
     * @return The XmlnsOs
     */
    public String getXmlnsOs() {
        return XmlnsOs;
    }

    /**
     * @param XmlnsOs The _xmlns:os
     */
    public void setXmlnsOs(String XmlnsOs) {
        this.XmlnsOs = XmlnsOs;
    }

    /**
     * @return The XmlnsOws
     */
    public String getXmlnsOws() {
        return XmlnsOws;
    }

    /**
     * @param XmlnsOws The _xmlns:ows
     */
    public void setXmlnsOws(String XmlnsOws) {
        this.XmlnsOws = XmlnsOws;
    }

    /**
     * @return The XmlnsSru
     */
    public String getXmlnsSru() {
        return XmlnsSru;
    }

    /**
     * @param XmlnsSru The _xmlns:sru
     */
    public void setXmlnsSru(String XmlnsSru) {
        this.XmlnsSru = XmlnsSru;
    }

    /**
     * @return The XmlnsTime
     */
    public String getXmlnsTime() {
        return XmlnsTime;
    }

    /**
     * @param XmlnsTime The _xmlns:time
     */
    public void setXmlnsTime(String XmlnsTime) {
        this.XmlnsTime = XmlnsTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Xmlns).append(XmlnsCsw).append(XmlnsDc).append(XmlnsDct).append(XmlnsEo).append(XmlnsGeo).append(XmlnsGeorss).append(XmlnsGml).append(XmlnsMedia).append(XmlnsOs).append(XmlnsOws).append(XmlnsSru).append(XmlnsTime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DCPrefixes)) {
            return false;
        }
        DCPrefixes rhs = ((DCPrefixes) other);
        return new EqualsBuilder().append(Xmlns, rhs.Xmlns).append(XmlnsCsw, rhs.XmlnsCsw).append(XmlnsDc, rhs.XmlnsDc).append(XmlnsDct, rhs.XmlnsDct).append(XmlnsEo, rhs.XmlnsEo).append(XmlnsGeo, rhs.XmlnsGeo).append(XmlnsGeorss, rhs.XmlnsGeorss).append(XmlnsGml, rhs.XmlnsGml).append(XmlnsMedia, rhs.XmlnsMedia).append(XmlnsOs, rhs.XmlnsOs).append(XmlnsOws, rhs.XmlnsOws).append(XmlnsSru, rhs.XmlnsSru).append(XmlnsTime, rhs.XmlnsTime).isEquals();
    }

}
