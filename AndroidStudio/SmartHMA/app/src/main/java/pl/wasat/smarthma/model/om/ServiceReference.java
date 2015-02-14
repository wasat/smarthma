package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class ServiceReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private RequestMessage requestMessage;
    private String _xmlns;
    private String _xlink_href;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public ServiceReference with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ServiceReference withRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
        return this;
    }

    public String get_xmlns() {
        return _xmlns;
    }

    public void set_xmlns() {
        this._xmlns = "xmlns";
    }

    public ServiceReference with_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
        return this;
    }

    public String get_xlink_href() {
        return _xlink_href;
    }

    public void set_xlink_href(String _xlink_href) {
        this._xlink_href = _xlink_href;
    }

    public ServiceReference with_xlink_href(String _xlink_href) {
        this._xlink_href = _xlink_href;
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
