package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Extent implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.EXExtent EXExtent;
    private pl.wasat.smarthma.model.iso.TimePeriod TimePeriod;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The EXExtent
     */
    public pl.wasat.smarthma.model.iso.EXExtent getEXExtent() {
        return EXExtent;
    }

    /**
     * @param EXExtent The EX_Extent
     */
    public void setEXExtent(pl.wasat.smarthma.model.iso.EXExtent EXExtent) {
        this.EXExtent = EXExtent;
    }

    /**
     * @return The TimePeriod
     */
    public pl.wasat.smarthma.model.iso.TimePeriod getTimePeriod() {
        return TimePeriod;
    }

    /**
     * @param TimePeriod The TimePeriod
     */
    public void setTimePeriod(pl.wasat.smarthma.model.iso.TimePeriod TimePeriod) {
        this.TimePeriod = TimePeriod;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The _prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
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
        return new HashCodeBuilder().append(EXExtent).append(Prefix)
                .append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Extent)) {
            return false;
        }
        Extent rhs = ((Extent) other);
        return new EqualsBuilder().append(EXExtent, rhs.EXExtent)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
