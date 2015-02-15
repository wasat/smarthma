package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class TemporalElement implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.EXTemporalExtent EXTemporalExtent;
    private String Prefix;


    /**
     * @return The EXTemporalExtent
     */
    public pl.wasat.smarthma.model.iso.EXTemporalExtent getEXTemporalExtent() {
        return EXTemporalExtent;
    }

    /**
     * @param EXTemporalExtent The EX_TemporalExtent
     */
    public void setEXTemporalExtent(
            pl.wasat.smarthma.model.iso.EXTemporalExtent EXTemporalExtent) {
        this.EXTemporalExtent = EXTemporalExtent;
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
        return new HashCodeBuilder().append(EXTemporalExtent).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TemporalElement)) {
            return false;
        }
        TemporalElement rhs = ((TemporalElement) other);
        return new EqualsBuilder()
                .append(EXTemporalExtent, rhs.EXTemporalExtent)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
