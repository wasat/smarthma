
package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class MultiSurface {

    private String __prefix;
    private SurfaceMembers surfaceMembers;
    private String _srsName;
    private String _gml_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public MultiSurface with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public SurfaceMembers getSurfaceMembers() {
        return surfaceMembers;
    }

    public void setSurfaceMembers(SurfaceMembers surfaceMembers) {
        this.surfaceMembers = surfaceMembers;
    }

    public MultiSurface withSurfaceMembers(SurfaceMembers surfaceMembers) {
        this.surfaceMembers = surfaceMembers;
        return this;
    }

    public String get_srsName() {
        return _srsName;
    }

    public void set_srsName(String _srsName) {
        this._srsName = _srsName;
    }

    public MultiSurface with_srsName(String _srsName) {
        this._srsName = _srsName;
        return this;
    }

    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public MultiSurface with_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
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
