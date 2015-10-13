
package pl.wasat.smarthma.model.entry;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public class Polygon implements Serializable {

    private String Prefix;
    private String Text;

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
    public String getText() {
        return Text;
    }

    /**
     * @param Text The __text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Prefix).append(Text).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Polygon)) {
            return false;
        }
        Polygon rhs = ((Polygon) other);
        return new EqualsBuilder().append(Prefix, rhs.Prefix).append(Text, rhs.Text).isEquals();
    }

}
