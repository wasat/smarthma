package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class CIDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private DateInCIDate dateInCIDate;
    private DateType dateType;
    private String Prefix;


    /**
     * @return The date
     */
    public DateInCIDate getDateInCIDate() {
        return dateInCIDate;
    }

    /**
     * @param dateInCIDate The date
     */
    public void setDateInCIDate(DateInCIDate dateInCIDate) {
        this.dateInCIDate = dateInCIDate;
    }

    /**
     * @return The dateType
     */
    public DateType getDateType() {
        return dateType;
    }

    /**
     * @param dateType The dateType
     */
    public void setDateType(DateType dateType) {
        this.dateType = dateType;
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
        return new HashCodeBuilder().append(dateInCIDate).append(dateType)
                .append(Prefix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CIDate)) {
            return false;
        }
        CIDate rhs = ((CIDate) other);
        return new EqualsBuilder().append(dateInCIDate, rhs.dateInCIDate)
                .append(dateType, rhs.dateType).append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
