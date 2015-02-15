package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class AccessConstraints implements Serializable {

    private static final long serialVersionUID = 1L;

    private MDRestrictionCode MDRestrictionCode;
    private String Prefix;


    /**
     * @return The MDRestrictionCode
     */
    public MDRestrictionCode getMDRestrictionCode() {
        return MDRestrictionCode;
    }

    /**
     * @param MDRestrictionCode The MD_RestrictionCode
     */
    public void setMDRestrictionCode(MDRestrictionCode MDRestrictionCode) {
        this.MDRestrictionCode = MDRestrictionCode;
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
        return new HashCodeBuilder().append(MDRestrictionCode).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AccessConstraints)) {
            return false;
        }
        AccessConstraints rhs = ((AccessConstraints) other);
        return new EqualsBuilder()
                .append(MDRestrictionCode, rhs.MDRestrictionCode)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
