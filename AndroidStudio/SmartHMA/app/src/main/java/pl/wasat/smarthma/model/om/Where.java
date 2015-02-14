
package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Where implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Polygon polygon;
    private String _xmlns_gml;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public Where with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Where withPolygon(Polygon polygon) {
        this.polygon = polygon;
        return this;
    }

    public String get_xmlns_gml() {
        return _xmlns_gml;
    }

    public void set_xmlns_gml(String _xmlns_gml) {
        this._xmlns_gml = _xmlns_gml;
    }

    public Where with_xmlns_gml(String _xmlns_gml) {
        this._xmlns_gml = _xmlns_gml;
        return this;
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
