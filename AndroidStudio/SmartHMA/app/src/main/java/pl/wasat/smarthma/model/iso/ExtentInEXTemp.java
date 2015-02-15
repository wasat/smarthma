package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class ExtentInEXTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    private TimePeriod TimePeriod;
    private String Prefix;


    /**
     * @return The TimePeriod
     */
    public TimePeriod getTimePeriod() {
        return TimePeriod;
    }

    /**
     * @param TimePeriod The TimePeriod
     */
    public void setTimePeriod(TimePeriod TimePeriod) {
        this.TimePeriod = TimePeriod;
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


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(TimePeriod).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ExtentInEXTemp)) {
            return false;
        }
        ExtentInEXTemp rhs = ((ExtentInEXTemp) other);
        return new EqualsBuilder().append(TimePeriod, rhs.TimePeriod)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
