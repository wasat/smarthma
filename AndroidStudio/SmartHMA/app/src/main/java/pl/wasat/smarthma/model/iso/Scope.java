package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Scope implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.DQScope DQScope;
    private String Prefix;


    /**
     * @return The DQScope
     */
    public pl.wasat.smarthma.model.iso.DQScope getDQScope() {
        return DQScope;
    }

    /**
     * @param DQScope The DQ_Scope
     */
    public void setDQScope(pl.wasat.smarthma.model.iso.DQScope DQScope) {
        this.DQScope = DQScope;
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
        return new HashCodeBuilder().append(DQScope).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Scope)) {
            return false;
        }
        Scope rhs = ((Scope) other);
        return new EqualsBuilder().append(DQScope, rhs.DQScope)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
