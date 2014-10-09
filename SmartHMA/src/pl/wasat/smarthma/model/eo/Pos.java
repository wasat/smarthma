package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.android.gms.maps.model.LatLng;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Pos implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private String __text;
	private LatLng latLng;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public Pos with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public String get__text() {
		return __text;
	}

	public void set__text(String __text) {
		__text = __text.replaceAll(",", " ");
		this.__text = __text;
		toLatLng();
	}

	public Pos with__text(String __text) {
		__text = __text.replaceAll(",", " ");
		this.__text = __text;
		return this;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
		this.__text = latLng.latitude + " " + latLng.longitude;
				
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

	private void toLatLng() {
		latLng = new LatLng(Double.valueOf(__text.split(" ")[0]),
				Double.valueOf(__text.split(" ")[1]));
	}
}
