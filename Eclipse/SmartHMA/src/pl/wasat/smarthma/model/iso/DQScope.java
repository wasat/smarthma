package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DQScope implements Serializable {

    private static final long serialVersionUID = 1L;

    private Level level;
    private String Prefix;


    /**
     * @return The level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level The level
     */
    public void setLevel(Level level) {
        this.level = level;
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
        return new HashCodeBuilder().append(level).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DQScope)) {
            return false;
        }
        DQScope rhs = ((DQScope) other);
        return new EqualsBuilder().append(level, rhs.level)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
