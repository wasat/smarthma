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
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Md data identification.
 */
public class MDDataIdentification implements Serializable {

    private static final long serialVersionUID = 1L;

    private Citation citation;
    private Abstract _abstract;
    private PointOfContact pointOfContact;
    private List<DescriptiveKeyword> descriptiveKeywords = new ArrayList<>();
    private List<ResourceConstraint> resourceConstraints = new ArrayList<>();
    private Language language;
    private TopicCategory topicCategory;
    private List<Extent> extents = new ArrayList<>();
    private String Prefix;


    /**
     * Gets citation.
     *
     * @return The citation
     */
    public Citation getCitation() {
        return citation;
    }

    /**
     * Sets citation.
     *
     * @param citation The citation
     */
    public void setCitation(Citation citation) {
        this.citation = citation;
    }

    /**
     * Gets abstract.
     *
     * @return The _abstract
     */
    public Abstract getAbstract() {
        return _abstract;
    }

    /**
     * Sets abstract.
     *
     * @param _abstract The abstract
     */
    public void setAbstract(Abstract _abstract) {
        this._abstract = _abstract;
    }

    /**
     * Gets point of contact.
     *
     * @return The pointOfContact
     */
    public PointOfContact getPointOfContact() {
        return pointOfContact;
    }

    /**
     * Sets point of contact.
     *
     * @param pointOfContact The pointOfContact
     */
    public void setPointOfContact(PointOfContact pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    /**
     * Gets descriptive keywords.
     *
     * @return The descriptiveKeywords
     */
    public List<DescriptiveKeyword> getDescriptiveKeywords() {
        return descriptiveKeywords;
    }

    /**
     * Sets descriptive keywords.
     *
     * @param descriptiveKeywords The descriptiveKeywords
     */
    public void setDescriptiveKeywords(
            List<DescriptiveKeyword> descriptiveKeywords) {
        this.descriptiveKeywords = descriptiveKeywords;
    }

    /**
     * Gets resource constraints.
     *
     * @return The resourceConstraints
     */
    public List<ResourceConstraint> getResourceConstraints() {
        return resourceConstraints;
    }

    /**
     * Sets resource constraints.
     *
     * @param resourceConstraints The resourceConstraints
     */
    public void setResourceConstraints(
            List<ResourceConstraint> resourceConstraints) {
        this.resourceConstraints = resourceConstraints;
    }

    /**
     * Gets language.
     *
     * @return The language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language The language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Gets topic category.
     *
     * @return The topicCategory
     */
    public TopicCategory getTopicCategory() {
        return topicCategory;
    }

    /**
     * Sets topic category.
     *
     * @param topicCategory The topicCategory
     */
    public void setTopicCategory(TopicCategory topicCategory) {
        this.topicCategory = topicCategory;
    }

    /**
     * Gets extents.
     *
     * @return The extent
     */
    public List<Extent> getExtents() {
        return extents;
    }

    /**
     * Sets extents.
     *
     * @param extents The extent
     */
    public void setExtents(List<Extent> extents) {
        this.extents = extents;
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
