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
 * The type Resource constraint.
 */
public class ResourceConstraint implements Serializable {

    private static final long serialVersionUID = 1L;

    private MDConstraints MDConstraints;
    private String Prefix;
    private MDLegalConstraints MDLegalConstraints;


    /**
     * Gets md constraints.
     *
     * @return The MDConstraints
     */
    public MDConstraints getMDConstraints() {
        return MDConstraints;
    }

    /**
     * Sets md constraints.
     *
     * @param MDConstraints The MD_Constraints
     */
    public void setMDConstraints(MDConstraints MDConstraints) {
        this.MDConstraints = MDConstraints;
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

    /**
     * Gets md legal constraints.
     *
     * @return The MDLegalConstraints
     */
    public MDLegalConstraints getMDLegalConstraints() {
        return MDLegalConstraints;
    }

    /**
     * Sets md legal constraints.
     *
     * @param MDLegalConstraints The MD_LegalConstraints
     */
    public void setMDLegalConstraints(MDLegalConstraints MDLegalConstraints) {
        this.MDLegalConstraints = MDLegalConstraints;
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
