package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class GeographicElement implements Serializable {

    private static final long serialVersionUID = 1L;

    private EXGeographicBoundingBox EXGeographicBoundingBox;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The EXGeographicBoundingBox
     */
    public EXGeographicBoundingBox getEXGeographicBoundingBox() {
        return EXGeographicBoundingBox;
    }

    /**
     * @param EXGeographicBoundingBox The EX_GeographicBoundingBox
     */
    public void setEXGeographicBoundingBox(
            EXGeographicBoundingBox EXGeographicBoundingBox) {
        this.EXGeographicBoundingBox = EXGeographicBoundingBox;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The __prefix
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
        return new HashCodeBuilder().append(EXGeographicBoundingBox)
                .append(Prefix).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GeographicElement)) {
            return false;
        }
        GeographicElement rhs = ((GeographicElement) other);
        return new EqualsBuilder()
                .append(EXGeographicBoundingBox, rhs.EXGeographicBoundingBox)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
