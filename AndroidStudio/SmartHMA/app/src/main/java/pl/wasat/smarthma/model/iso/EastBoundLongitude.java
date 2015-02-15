package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EastBoundLongitude implements Serializable {

    private static final long serialVersionUID = 1L;

    private Decimal Decimal;
    private String Prefix;


    /**
     * @return The Decimal
     */
    public Decimal getDecimal() {
        return Decimal;
    }

    /**
     * @param Decimal The Decimal
     */
    public void setDecimal(Decimal Decimal) {
        this.Decimal = Decimal;
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
        return new HashCodeBuilder().append(Decimal).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EastBoundLongitude)) {
            return false;
        }
        EastBoundLongitude rhs = ((EastBoundLongitude) other);
        return new EqualsBuilder().append(Decimal, rhs.Decimal)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
