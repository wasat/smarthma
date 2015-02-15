package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DateStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    private DateGco dateGco;
    private String Prefix;


    /**
     * @return The Date
     */
    public DateGco getDateGco() {
        return dateGco;
    }

    /**
     * @param dateGco The Date
     */
    public void setDate(DateGco dateGco) {
        this.dateGco = dateGco;
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
        return new HashCodeBuilder().append(dateGco).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DateStamp)) {
            return false;
        }
        DateStamp rhs = ((DateStamp) other);
        return new EqualsBuilder().append(dateGco, rhs.dateGco)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
