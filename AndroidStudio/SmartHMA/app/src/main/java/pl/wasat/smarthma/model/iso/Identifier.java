package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Identifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private RSIdentifier RSIdentifier;
    // private String Text;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The RSIdentifier
     */
    public pl.wasat.smarthma.model.iso.RSIdentifier getRSIdentifier() {
        return RSIdentifier;
    }

    /**
     * @param RSIdentifier The RS_Identifier
     */
    public void setRSIdentifier(
            pl.wasat.smarthma.model.iso.RSIdentifier RSIdentifier) {
        this.RSIdentifier = RSIdentifier;
    }

	/*	*//**
     * @return the text
     */
    /*
	 * public String getText() { return Text; }
	 *//**
     * @param text
     *            the text to set
     */
	/*
	 * public void setText(String text) { Text = text; }
	 */

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(RSIdentifier)
                // .append(Text)
                .append(Prefix).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Identifier)) {
            return false;
        }
        Identifier rhs = ((Identifier) other);
        return new EqualsBuilder()
                .append(RSIdentifier, rhs.RSIdentifier)
                        // .append(Text, rhs.Text)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
