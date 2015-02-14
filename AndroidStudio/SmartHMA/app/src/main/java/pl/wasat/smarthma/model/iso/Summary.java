package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Type;
    private String Cdata;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The _type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return The Cdata
     */
    public String getCdata() {
        return Cdata;
    }

    /**
     * @param Cdata The _cdata
     */
    public void setCdata(String Cdata) {
        this.Cdata = Cdata;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Type).append(Cdata)
                .append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Summary)) {
            return false;
        }
        Summary rhs = ((Summary) other);
        return new EqualsBuilder().append(Type, rhs.Type)
                .append(Cdata, rhs.Cdata)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
