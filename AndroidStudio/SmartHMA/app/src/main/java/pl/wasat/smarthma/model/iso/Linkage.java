package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Linkage implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.URL URL;
    private String Prefix;


    /**
     * @return The URL
     */
    public pl.wasat.smarthma.model.iso.URL getURL() {
        return URL;
    }

    /**
     * @param URL The URL
     */
    public void setURL(pl.wasat.smarthma.model.iso.URL URL) {
        this.URL = URL;
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
        return new HashCodeBuilder().append(URL).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Linkage)) {
            return false;
        }
        Linkage rhs = ((Linkage) other);
        return new EqualsBuilder().append(URL, rhs.URL)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
