
package pl.wasat.smarthma.model.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Fedeo {

    private ExceptionReport exceptionReport;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ExceptionReport getExceptionReport() {
        return exceptionReport;
    }

    public void setExceptionReport(ExceptionReport exceptionReport) {
        this.exceptionReport = exceptionReport;
    }

    public Fedeo withExceptionReport(ExceptionReport exceptionReport) {
        this.exceptionReport = exceptionReport;
        return this;
    }

    @Override
    public String toString() {
		ToStringStyle style = new SmartHMAStringStyle();
		ToStringBuilder.setDefaultStyle(style);
		return ToStringBuilder.reflectionToString(this);
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

    public Fedeo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
