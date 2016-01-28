package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Phenomenon implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Name name;
    private String _xmlns_ns1;
    private String _ns1_id;
    private String _xmlns_gml31;
    private String _gml31_id;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String get_xmlns_ns1() {
        return _xmlns_ns1;
    }

    public void set_xmlns_ns1(String _xmlns_ns1) {
        this._xmlns_ns1 = _xmlns_ns1;
    }

    public String get_ns1_id() {
        return _ns1_id;
    }

    public void set_ns1_id(String _ns1_id) {
        this._ns1_id = _ns1_id;
    }

    public String get_xmlns_gml31() {
        return _xmlns_gml31;
    }

    public void set_xmlns_gml31(String _xmlns_gml31) {
        this._xmlns_gml31 = _xmlns_gml31;
    }

    public String get_gml31_id() {
        return _gml31_id;
    }

    public void set_gml31_id(String _gml31_id) {
        this._gml31_id = _gml31_id;
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
