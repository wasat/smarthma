package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class TimePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private BeginPosition beginPosition;
    private EndPosition endPosition;
    private String _gml_id;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public TimePeriod with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public BeginPosition getBeginPosition() {
        return beginPosition;
    }

    public void setBeginPosition(BeginPosition beginPosition) {
        this.beginPosition = beginPosition;
    }

    public TimePeriod withBeginPosition(BeginPosition beginPosition) {
        this.beginPosition = beginPosition;
        return this;
    }

    public EndPosition getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(EndPosition endPosition) {
        this.endPosition = endPosition;
    }

    public TimePeriod withEndPosition(EndPosition endPosition) {
        this.endPosition = endPosition;
        return this;
    }

    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public TimePeriod with_gml_id(String _gml_id) {
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
