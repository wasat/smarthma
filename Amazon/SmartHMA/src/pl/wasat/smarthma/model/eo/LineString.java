package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class LineString implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private Coordinates coordinates;
	private String _gml_id;
	private String _srsName;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public LineString with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public LineString withCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
		return this;
	}

	public String get_gml_id() {
		return _gml_id;
	}

	public void set_gml_id(String _gml_id) {
		this._gml_id = _gml_id;
	}

	public LineString with_gml_id(String _gml_id) {
		this._gml_id = _gml_id;
		return this;
	}

	public String get_srsName() {
		return _srsName;
	}

	public void set_srsName(String _srsName) {
		this._srsName = _srsName;
	}

	public LineString with_srsName(String _srsName) {
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
