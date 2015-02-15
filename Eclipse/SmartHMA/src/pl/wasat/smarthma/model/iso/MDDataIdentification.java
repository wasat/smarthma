package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

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
     * @return The citation
     */
    public Citation getCitation() {
        return citation;
    }

    /**
     * @param citation The citation
     */
    public void setCitation(Citation citation) {
        this.citation = citation;
    }

    /**
     * @return The _abstract
     */
    public Abstract getAbstract() {
        return _abstract;
    }

    /**
     * @param _abstract The abstract
     */
    public void setAbstract(Abstract _abstract) {
        this._abstract = _abstract;
    }

    /**
     * @return The pointOfContact
     */
    public PointOfContact getPointOfContact() {
        return pointOfContact;
    }

    /**
     * @param pointOfContact The pointOfContact
     */
    public void setPointOfContact(PointOfContact pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    /**
     * @return The descriptiveKeywords
     */
    public List<DescriptiveKeyword> getDescriptiveKeywords() {
        return descriptiveKeywords;
    }

    /**
     * @param descriptiveKeywords The descriptiveKeywords
     */
    public void setDescriptiveKeywords(
            List<DescriptiveKeyword> descriptiveKeywords) {
        this.descriptiveKeywords = descriptiveKeywords;
    }

    /**
     * @return The resourceConstraints
     */
    public List<ResourceConstraint> getResourceConstraints() {
        return resourceConstraints;
    }

    /**
     * @param resourceConstraints The resourceConstraints
     */
    public void setResourceConstraints(
            List<ResourceConstraint> resourceConstraints) {
        this.resourceConstraints = resourceConstraints;
    }

    /**
     * @return The language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * @return The topicCategory
     */
    public TopicCategory getTopicCategory() {
        return topicCategory;
    }

    /**
     * @param topicCategory The topicCategory
     */
    public void setTopicCategory(TopicCategory topicCategory) {
        this.topicCategory = topicCategory;
    }

    /**
     * @return The extent
     */
    public List<Extent> getExtents() {
        return extents;
    }

    /**
     * @param extents The extent
     */
    public void setExtents(List<Extent> extents) {
        this.extents = extents;
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
        return new HashCodeBuilder().append(citation).append(_abstract)
                .append(pointOfContact).append(descriptiveKeywords)
                .append(resourceConstraints).append(language)
                .append(topicCategory).append(extents).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MDDataIdentification)) {
            return false;
        }
        MDDataIdentification rhs = ((MDDataIdentification) other);
        return new EqualsBuilder().append(citation, rhs.citation)
                .append(_abstract, rhs._abstract)
                .append(pointOfContact, rhs.pointOfContact)
                .append(descriptiveKeywords, rhs.descriptiveKeywords)
                .append(resourceConstraints, rhs.resourceConstraints)
                .append(language, rhs.language)
                .append(topicCategory, rhs.topicCategory)
                .append(extents, rhs.extents).append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
