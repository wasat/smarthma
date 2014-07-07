
package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Phenomenon {

    private String __prefix;
    private Name name;
    private String _xmlns_ns1;
    private String _ns1_id;
    private String _xmlns_gml31;
    private String _gml31_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public Phenomenon with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Phenomenon withName(Name name) {
        this.name = name;
        return this;
    }

    public String get_xmlns_ns1() {
        return _xmlns_ns1;
    }

    public void set_xmlns_ns1(String _xmlns_ns1) {
        this._xmlns_ns1 = _xmlns_ns1;
    }

    public Phenomenon with_xmlns_ns1(String _xmlns_ns1) {
        this._xmlns_ns1 = _xmlns_ns1;
        return this;
    }

    public String get_ns1_id() {
        return _ns1_id;
    }

    public void set_ns1_id(String _ns1_id) {
        this._ns1_id = _ns1_id;
    }

    public Phenomenon with_ns1_id(String _ns1_id) {
        this._ns1_id = _ns1_id;
        return this;
    }
    
    
    public String get_xmlns_gml31() {
        return _xmlns_gml31;
    }

    public void set_xmlns_gml31(String _xmlns_gml31) {
        this._xmlns_gml31 = _xmlns_gml31;
    }

    public Phenomenon with_xmlns_gml31(String _xmlns_gml31) {
        this._xmlns_gml31 = _xmlns_gml31;
        return this;
    }

    public String get_gml31_id() {
        return _gml31_id;
    }

    public void set_gml31_id(String _gml31_id) {
        this._gml31_id = _gml31_id;
    }

    public Phenomenon with_gml31_id(String _gml31_id) {
        this._gml31_id = _gml31_id;
        return this;
    }


    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
