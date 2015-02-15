package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class MultiSurface implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private SurfaceMembers surfaceMembers;
    private String _srsName;
    private String _gml_id;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public MultiSurface with_prefix(String _prefix) {
        this._prefix = _prefix;
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


}
