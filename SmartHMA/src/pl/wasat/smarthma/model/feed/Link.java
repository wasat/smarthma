
package pl.wasat.smarthma.model.feed;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Link {

    private String _href;
    private String _rel;
    private String _title;
    private String _type;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_href() {
        return _href;
    }

    public void set_href(String _href) {
        this._href = _href;
    }

    public Link with_href(String _href) {
        this._href = _href;
        return this;
    }

    public String get_rel() {
        return _rel;
    }

    public void set_rel(String _rel) {
        this._rel = _rel;
    }

    public Link with_rel(String _rel) {
        this._rel = _rel;
        return this;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public Link with_title(String _title) {
        this._title = _title;
        return this;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public Link with_type(String _type) {
        this._type = _type;
        return this;
    }

    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
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
