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
public class EOPrefixes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _xmlns;
    private String _xmlns_dc;
    private String _xmlns_eo;
    private String _xmlns_geo;
    private String _xmlns_georss;
    private String _xmlns_media;
    private String _xmlns_os;
    private String _xmlns_sru;
    private String _xmlns_time;
    private String _xmlns_wrs;

    public String get_xmlns() {
        return _xmlns;
    }

    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public String get_xmlns_dc() {
        return _xmlns_dc;
    }

    public void set_xmlns_dc(String _xmlns_dc) {
        this._xmlns_dc = _xmlns_dc;
    }

    public String get_xmlns_eo() {
        return _xmlns_eo;
    }

    public void set_xmlns_eo(String _xmlns_eo) {
        this._xmlns_eo = _xmlns_eo;
    }

    public String get_xmlns_geo() {
        return _xmlns_geo;
    }

    public void set_xmlns_geo(String _xmlns_geo) {
        this._xmlns_geo = _xmlns_geo;
    }

    public String get_xmlns_georss() {
        return _xmlns_georss;
    }

    public void set_xmlns_georss(String _xmlns_georss) {
        this._xmlns_georss = _xmlns_georss;
    }

    public String get_xmlns_media() {
        return _xmlns_media;
    }

    public void set_xmlns_media(String _xmlns_media) {
        this._xmlns_media = _xmlns_media;
    }

    public String get_xmlns_os() {
        return _xmlns_os;
    }

    public void set_xmlns_os(String _xmlns_os) {
        this._xmlns_os = _xmlns_os;
    }

    public String get_xmlns_sru() {
        return _xmlns_sru;
    }

    public void set_xmlns_sru(String _xmlns_sru) {
        this._xmlns_sru = _xmlns_sru;
    }

    public String get_xmlns_time() {
        return _xmlns_time;
    }

    public void set_xmlns_time(String _xmlns_time) {
        this._xmlns_time = _xmlns_time;
    }

    public String get_xmlns_wrs() {
        return _xmlns_wrs;
    }

    public void set_xmlns_wrs(String _xmlns_wrs) {
        this._xmlns_wrs = _xmlns_wrs;
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
