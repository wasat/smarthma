package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class MultiPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private List<PointMember> pointMember = new ArrayList<PointMember>();
    private String _gml_id;
    private String _srsName;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public MultiPoint with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public List<PointMember> getPointMember() {
        return pointMember;
    }

    public void setPointMember(List<PointMember> pointMember) {
        this.pointMember = pointMember;
    }

    public MultiPoint withPointMember(List<PointMember> pointMember) {
        this.pointMember = pointMember;
        return this;
    }

    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public MultiPoint with_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
        return this;
    }

    public String get_srsName() {
        return _srsName;
    }

    public void set_srsName(String _srsName) {
        this._srsName = _srsName;
    }

    public MultiPoint with_srsName(String _srsName) {
        this._srsName = _srsName;
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
