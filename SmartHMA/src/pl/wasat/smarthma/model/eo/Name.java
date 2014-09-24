package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Name implements Serializable {

	private static final long serialVersionUID = 1L;
	private String __text;
	private String _xmlns;
	private String _codeSpace;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__text() {
		return __text;
	}

	public void set__text(String __text) {
		this.__text = __text;
	}

	public Name with__text(String __text) {
		this.__text = __text;
		return this;
	}

	public String get_xmlns() {
		return _xmlns;
	}

	public void set_xmlns(String _xmlns) {
		this._xmlns = _xmlns;
	}

	public Name with_xmlns(String _xmlns) {
		this._xmlns = _xmlns;
		return this;
	}

	public String get_codeSpace() {
		return _codeSpace;
	}

	public void set_codeSpace(String _codeSpace) {
		this._codeSpace = _codeSpace;
	}

	public Name with_codeSpace(String _codeSpace) {
		this._codeSpace = _codeSpace;
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
