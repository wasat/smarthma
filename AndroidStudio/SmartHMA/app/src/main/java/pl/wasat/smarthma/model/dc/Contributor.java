
package pl.wasat.smarthma.model.dc;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Contributor implements Serializable {
    private static final long serialVersionUID = 1L;
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
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
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
        if (!(other instanceof Contributor)) {
            return false;
        }
        Contributor rhs = ((Contributor) other);
        return new EqualsBuilder().append(Prefix, rhs.Prefix).append(Text, rhs.Text).isEquals();
    }

}
