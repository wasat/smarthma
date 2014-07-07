
package pl.wasat.smarthma.model.feed;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.model.eo.Polygon;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Where {

    private String __prefix;
    private Polygon polygon;
    private String _xmlns_gml;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public Where with__prefix(String __prefix) {
        this.__prefix = __prefix;
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
