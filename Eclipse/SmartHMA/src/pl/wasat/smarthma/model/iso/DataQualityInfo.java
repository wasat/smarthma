package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DataQualityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.DQDataQuality DQDataQuality;
    private String Prefix;


    /**
     * @return The DQDataQuality
     */
    public pl.wasat.smarthma.model.iso.DQDataQuality getDQDataQuality() {
        return DQDataQuality;
    }

    /**
     * @param DQDataQuality The DQ_DataQuality
     */
    public void setDQDataQuality(
            pl.wasat.smarthma.model.iso.DQDataQuality DQDataQuality) {
        this.DQDataQuality = DQDataQuality;
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


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(DQDataQuality).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DataQualityInfo)) {
            return false;
        }
        DataQualityInfo rhs = ((DataQualityInfo) other);
        return new EqualsBuilder().append(DQDataQuality, rhs.DQDataQuality)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
