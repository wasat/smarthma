package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class RSIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Code code;
    private CodeSpace codeSpace;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The code
     */
    public Code getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * @return The codeSpace
     */
    public CodeSpace getCodeSpace() {
        return codeSpace;
    }

    /**
     * @param codeSpace The codeSpace
     */
    public void setCodeSpace(CodeSpace codeSpace) {
        this.codeSpace = codeSpace;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(code).append(codeSpace)
                .append(Prefix).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RSIdentifier)) {
            return false;
        }
        RSIdentifier rhs = ((RSIdentifier) other);
        return new EqualsBuilder().append(code, rhs.code)
                .append(codeSpace, rhs.codeSpace).append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
