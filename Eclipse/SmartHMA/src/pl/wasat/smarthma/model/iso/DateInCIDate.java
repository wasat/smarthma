package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DateInCIDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Prefix;
    private DateGco dateGco;


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

    /**
     * @return The Text
     */
    public DateGco getDateGco() {
        return dateGco;
    }

    /**
     * @param dateGco The __text
     */
    public void setDateGco(DateGco dateGco) {
        this.dateGco = dateGco;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Prefix).append(dateGco)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DateInCIDate)) {
            return false;
        }
        DateInCIDate rhs = ((DateInCIDate) other);
        return new EqualsBuilder().append(Prefix, rhs.Prefix)
                .append(dateGco, rhs.dateGco)

                .isEquals();
    }

}
