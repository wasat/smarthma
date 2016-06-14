/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Ci responsible party.
 */
public class CIResponsibleParty implements Serializable {

    private static final long serialVersionUID = 1L;

    private IndividualName individualName;
    private OrganisationName organisationName;
    private PositionName positionName;
    private ContactInfo contactInfo;
    private Role role;
    private String Prefix;


    /**
     * Gets individual name.
     *
     * @return The individualName
     */
    public IndividualName getIndividualName() {
        return individualName;
    }

    /**
     * Sets individual name.
     *
     * @param individualName The individualName
     */
    public void setIndividualName(IndividualName individualName) {
        this.individualName = individualName;
    }

    /**
     * Gets organisation name.
     *
     * @return The organisationName
     */
    public OrganisationName getOrganisationName() {
        return organisationName;
    }

    /**
     * Sets organisation name.
     *
     * @param organisationName The organisationName
     */
    public void setOrganisationName(OrganisationName organisationName) {
        this.organisationName = organisationName;
    }

    /**
     * Gets position name.
     *
     * @return The positionName
     */
    public PositionName getPositionName() {
        return positionName;
    }

    /**
     * Sets position name.
     *
     * @param positionName The positionName
     */
    public void setPositionName(PositionName positionName) {
        this.positionName = positionName;
    }

    /**
     * Gets contact info.
     *
     * @return The contactInfo
     */
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets contact info.
     *
     * @param contactInfo The contactInfo
     */
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Gets role.
     *
     * @return The role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role The role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets prefix.
     *
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * Sets prefix.
     *
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
