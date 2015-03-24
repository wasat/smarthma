package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Extent implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.EXExtent EXExtent;
    private pl.wasat.smarthma.model.iso.TimePeriod TimePeriod;
    private String Prefix;


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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }
}
