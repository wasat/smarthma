package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    private LanguageCode LanguageCode;
    private String Prefix;


    /**
     * @return The LanguageCode
     */
    public pl.wasat.smarthma.model.iso.LanguageCode getLanguageCode() {
        return LanguageCode;
    }

    /**
     * @param LanguageCode The LanguageCode
     */
    public void setLanguageCode(
            pl.wasat.smarthma.model.iso.LanguageCode LanguageCode) {
        this.LanguageCode = LanguageCode;
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
        return new HashCodeBuilder().append(LanguageCode).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Language)) {
            return false;
        }
        Language rhs = ((Language) other);
        return new EqualsBuilder().append(LanguageCode, rhs.LanguageCode)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
