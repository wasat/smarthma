package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class CIResponsibleParty implements Serializable {

    private static final long serialVersionUID = 1L;

    private IndividualName individualName;
    private OrganisationName organisationName;
    private PositionName positionName;
    private ContactInfo contactInfo;
    private Role role;
    private String Prefix;


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


    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
