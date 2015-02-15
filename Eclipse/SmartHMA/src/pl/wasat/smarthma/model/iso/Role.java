package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.CIRoleCode CIRoleCode;
    private String Prefix;


    /**
     * @return The CIRoleCode
     */
    public pl.wasat.smarthma.model.iso.CIRoleCode getCIRoleCode() {
        return CIRoleCode;
    }

    /**
     * @param CIRoleCode The CI_RoleCode
     */
    public void setCIRoleCode(pl.wasat.smarthma.model.iso.CIRoleCode CIRoleCode) {
        this.CIRoleCode = CIRoleCode;
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
        return new HashCodeBuilder().append(CIRoleCode).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Role)) {
            return false;
        }
        Role rhs = ((Role) other);
        return new EqualsBuilder().append(CIRoleCode, rhs.CIRoleCode)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
