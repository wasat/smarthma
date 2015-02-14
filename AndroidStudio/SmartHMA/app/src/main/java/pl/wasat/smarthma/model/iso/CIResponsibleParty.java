package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class CIResponsibleParty implements Serializable {

    private static final long serialVersionUID = 1L;

    private IndividualName individualName;
    private OrganisationName organisationName;
    private PositionName positionName;
    private ContactInfo contactInfo;
    private Role role;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The individualName
     */
    public IndividualName getIndividualName() {
        return individualName;
    }

    /**
     * @param individualName The individualName
     */
    public void setIndividualName(IndividualName individualName) {
        this.individualName = individualName;
    }

    /**
     * @return The organisationName
     */
    public OrganisationName getOrganisationName() {
        return organisationName;
    }

    /**
     * @param organisationName The organisationName
     */
    public void setOrganisationName(OrganisationName organisationName) {
        this.organisationName = organisationName;
    }

    /**
     * @return The positionName
     */
    public PositionName getPositionName() {
        return positionName;
    }

    /**
     * @param positionName The positionName
     */
    public void setPositionName(PositionName positionName) {
        this.positionName = positionName;
    }

    /**
     * @return The contactInfo
     */
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo The contactInfo
     */
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * @return The role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role The role
     */
    public void setRole(Role role) {
        this.role = role;
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
        return new HashCodeBuilder().append(individualName)
                .append(organisationName).append(contactInfo).append(role)
                .append(Prefix).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CIResponsibleParty)) {
            return false;
        }
        CIResponsibleParty rhs = ((CIResponsibleParty) other);
        return new EqualsBuilder().append(individualName, rhs.individualName)
                .append(organisationName, rhs.organisationName)
                .append(contactInfo, rhs.contactInfo).append(role, rhs.role)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
