package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EXGeographicBoundingBox implements Serializable {

    private static final long serialVersionUID = 1L;

    private WestBoundLongitude westBoundLongitude;
    private EastBoundLongitude eastBoundLongitude;
    private SouthBoundLatitude southBoundLatitude;
    private NorthBoundLatitude northBoundLatitude;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The westBoundLongitude
     */
    public WestBoundLongitude getWestBoundLongitude() {
        return westBoundLongitude;
    }

    /**
     * @param westBoundLongitude The westBoundLongitude
     */
    public void setWestBoundLongitude(WestBoundLongitude westBoundLongitude) {
        this.westBoundLongitude = westBoundLongitude;
    }

    /**
     * @return The eastBoundLongitude
     */
    public EastBoundLongitude getEastBoundLongitude() {
        return eastBoundLongitude;
    }

    /**
     * @param eastBoundLongitude The eastBoundLongitude
     */
    public void setEastBoundLongitude(EastBoundLongitude eastBoundLongitude) {
        this.eastBoundLongitude = eastBoundLongitude;
    }

    /**
     * @return The southBoundLatitude
     */
    public SouthBoundLatitude getSouthBoundLatitude() {
        return southBoundLatitude;
    }

    /**
     * @param southBoundLatitude The southBoundLatitude
     */
    public void setSouthBoundLatitude(SouthBoundLatitude southBoundLatitude) {
        this.southBoundLatitude = southBoundLatitude;
    }

    /**
     * @return The northBoundLatitude
     */
    public NorthBoundLatitude getNorthBoundLatitude() {
        return northBoundLatitude;
    }

    /**
     * @param northBoundLatitude The northBoundLatitude
     */
    public void setNorthBoundLatitude(NorthBoundLatitude northBoundLatitude) {
        this.northBoundLatitude = northBoundLatitude;
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
        return new HashCodeBuilder().append(westBoundLongitude)
                .append(eastBoundLongitude).append(southBoundLatitude)
                .append(northBoundLatitude).append(Prefix)
                .append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EXGeographicBoundingBox)) {
            return false;
        }
        EXGeographicBoundingBox rhs = ((EXGeographicBoundingBox) other);
        return new EqualsBuilder()
                .append(westBoundLongitude, rhs.westBoundLongitude)
                .append(eastBoundLongitude, rhs.eastBoundLongitude)
                .append(southBoundLatitude, rhs.southBoundLatitude)
                .append(northBoundLatitude, rhs.northBoundLatitude)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
