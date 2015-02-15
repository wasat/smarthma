package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class MDConstraints implements Serializable {

    private static final long serialVersionUID = 1L;

    private UseLimitation useLimitation;
    private String Prefix;


    /**
     * @return The useLimitation
     */
    public UseLimitation getUseLimitation() {
        return useLimitation;
    }

    /**
     * @param useLimitation The useLimitation
     */
    public void setUseLimitation(UseLimitation useLimitation) {
        this.useLimitation = useLimitation;
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
        return new HashCodeBuilder().append(useLimitation).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MDConstraints)) {
            return false;
        }
        MDConstraints rhs = ((MDConstraints) other);
        return new EqualsBuilder().append(useLimitation, rhs.useLimitation)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
