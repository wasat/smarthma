
package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Timeliness {

    private String __prefix;
    private String _codeSpace;
    private String __text;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public Timeliness with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public String get_codeSpace() {
        return _codeSpace;
    }

    public void set_codeSpace(String _codeSpace) {
        this._codeSpace = _codeSpace;
    }

    public Timeliness with_codeSpace(String _codeSpace) {
        this._codeSpace = _codeSpace;
        return this;
    }

    public String get__text() {
        return __text;
    }

    public void set__text(String __text) {
        this.__text = __text;
    }

    public Timeliness with__text(String __text) {
        this.__text = __text;
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
