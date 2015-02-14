package pl.wasat.smarthma.model.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	private String prefix;
	private ExceptionText exceptionText;
	private String exceptionCode;
	private String locator;
	private final Map<String, Object> additionalProperties = new HashMap<>();

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Exception withPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public ExceptionText getExceptionText() {
		return exceptionText;
	}

	public void setExceptionText(ExceptionText exceptionText) {
		this.exceptionText = exceptionText;
	}

	public Exception withExceptionText(ExceptionText exceptionText) {
		this.exceptionText = exceptionText;
		return this;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public Exception withExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
		return this;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public Exception withLocator(String locator) {
		this.locator = locator;
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

	public Exception withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
