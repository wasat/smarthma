package pl.wasat.smarthma.model.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class ExceptionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prefix;
    private Exception exception;
    private String xmlnsOws;
    private String xmlnsXlink;
    private String xmlnsXsi;
    private String version;
    private String xmlLang;
    private String xsiSchemaLocation;
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ExceptionReport withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ExceptionReport withException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public String getXmlnsOws() {
        return xmlnsOws;
    }

    public void setXmlnsOws(String xmlnsOws) {
        this.xmlnsOws = xmlnsOws;
    }

    public ExceptionReport withXmlnsOws(String xmlnsOws) {
        this.xmlnsOws = xmlnsOws;
        return this;
    }

    public String getXmlnsXlink() {
        return xmlnsXlink;
    }

    public void setXmlnsXlink(String xmlnsXlink) {
        this.xmlnsXlink = xmlnsXlink;
    }

    public ExceptionReport withXmlnsXlink(String xmlnsXlink) {
        this.xmlnsXlink = xmlnsXlink;
        return this;
    }

    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }

    public ExceptionReport withXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ExceptionReport withVersion(String version) {
        this.version = version;
        return this;
    }

    public String getXmlLang() {
        return xmlLang;
    }

    public void setXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
    }

    public ExceptionReport withXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
        return this;
    }

    public String getXsiSchemaLocation() {
        return xsiSchemaLocation;
    }

    public void setXsiSchemaLocation(String xsiSchemaLocation) {
        this.xsiSchemaLocation = xsiSchemaLocation;
    }

    public ExceptionReport withXsiSchemaLocation(String xsiSchemaLocation) {
        this.xsiSchemaLocation = xsiSchemaLocation;
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


    public ExceptionReport withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
