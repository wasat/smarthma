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

package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Earth observation meta data.
 */
public class EarthObservationMetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Identifier identifier;
    private CreationDate creationDate;
    private ModificationDate modificationDate;
    private ParentIdentifier parentIdentifier;
    private AcquisitionType acquisitionType;
    private ProductType productType;
    private Status status;
    private StatusSubType statusSubType;
    private DownlinkedTo downlinkedTo;
    private ArchivedIn archivedIn;
    private ProductQualityDegradation productQualityDegradation;
    private ProductQualityStatus productQualityStatus;
    private List<ProductQualityDegradationTag> productQualityDegradationTag = new ArrayList<>();
    private ProductQualityReportURL productQualityReportURL;
    private ComposedOf composedOf;
    private List<Processing> processing = new ArrayList<>();
    private ProductGroupId productGroupId;
    private DerivedFrom derivedFrom;
    private NominalDate nominalDate;

    private AcquisitionSubType acquisitionSubType;
    private ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode;

    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String get_prefix() {
        return _prefix;
    }

    /**
     * Sets prefix.
     *
     * @param _prefix the prefix
     */
    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public CreationDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets modification date.
     *
     * @return the modification date
     */
    public ModificationDate getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets modification date.
     *
     * @param modificationDate the modification date
     */
    public void setModificationDate(ModificationDate modificationDate) {
        this.modificationDate = modificationDate;
    }


    /**
     * Gets parent identifier.
     *
     * @return the parent identifier
     */
    public ParentIdentifier getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * Sets parent identifier.
     *
     * @param parentIdentifier the parent identifier
     */
    public void setParentIdentifier(ParentIdentifier parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }


    /**
     * Gets acquisition type.
     *
     * @return the acquisition type
     */
    public AcquisitionType getAcquisitionType() {
        return acquisitionType;
    }

    /**
     * Sets acquisition type.
     *
     * @param acquisitionType the acquisition type
     */
    public void setAcquisitionType(AcquisitionType acquisitionType) {
        this.acquisitionType = acquisitionType;
    }


    /**
     * Gets product type.
     *
     * @return the product type
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Sets product type.
     *
     * @param productType the product type
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets status sub type.
     *
     * @return the status sub type
     */
    public StatusSubType getStatusSubType() {
        return statusSubType;
    }

    /**
     * Sets status sub type.
     *
     * @param statusSubType the status sub type
     */
    public void setStatusSubType(StatusSubType statusSubType) {
        this.statusSubType = statusSubType;
    }

    /**
     * Gets downlinked to.
     *
     * @return the downlinked to
     */
    public DownlinkedTo getDownlinkedTo() {
        return downlinkedTo;
    }

    /**
     * Sets downlinked to.
     *
     * @param downlinkedTo the downlinked to
     */
    public void setDownlinkedTo(DownlinkedTo downlinkedTo) {
        this.downlinkedTo = downlinkedTo;
    }


    /**
     * Gets archived in.
     *
     * @return the archived in
     */
    public ArchivedIn getArchivedIn() {
        return archivedIn;
    }

    /**
     * Sets archived in.
     *
     * @param archivedIn the archived in
     */
    public void setArchivedIn(ArchivedIn archivedIn) {
        this.archivedIn = archivedIn;
    }


    /**
     * Gets product quality degradation.
     *
     * @return the product quality degradation
     */
    public ProductQualityDegradation getProductQualityDegradation() {
        return productQualityDegradation;
    }

    /**
     * Sets product quality degradation.
     *
     * @param productQualityDegradation the product quality degradation
     */
    public void setProductQualityDegradation(
            ProductQualityDegradation productQualityDegradation) {
        this.productQualityDegradation = productQualityDegradation;
    }


    /**
     * Gets product quality status.
     *
     * @return the product quality status
     */
    public ProductQualityStatus getProductQualityStatus() {
        return productQualityStatus;
    }

    /**
     * Sets product quality status.
     *
     * @param productQualityStatus the product quality status
     */
    public void setProductQualityStatus(
            ProductQualityStatus productQualityStatus) {
        this.productQualityStatus = productQualityStatus;
    }


    /**
     * Gets product quality degradation tag.
     *
     * @return the product quality degradation tag
     */
    public List<ProductQualityDegradationTag> getProductQualityDegradationTag() {
        return productQualityDegradationTag;
    }

    /**
     * Sets product quality degradation tag.
     *
     * @param productQualityDegradationTag the product quality degradation tag
     */
    public void setProductQualityDegradationTag(
            List<ProductQualityDegradationTag> productQualityDegradationTag) {
        this.productQualityDegradationTag = productQualityDegradationTag;
    }


    /**
     * Gets product quality report url.
     *
     * @return the product quality report url
     */
    public ProductQualityReportURL getProductQualityReportURL() {
        return productQualityReportURL;
    }

    /**
     * Sets product quality report url.
     *
     * @param productQualityReportURL the product quality report url
     */
    public void setProductQualityReportURL(
            ProductQualityReportURL productQualityReportURL) {
        this.productQualityReportURL = productQualityReportURL;
    }

    /**
     * Gets composed of.
     *
     * @return the composed of
     */
    public ComposedOf getComposedOf() {
        return composedOf;
    }

    /**
     * Sets composed of.
     *
     * @param composedOf the composed of
     */
    public void setComposedOf(ComposedOf composedOf) {
        this.composedOf = composedOf;
    }


    /**
     * Gets processing.
     *
     * @return the processing
     */
    public List<Processing> getProcessing() {
        return processing;
    }

    /**
     * Sets processing.
     *
     * @param processing the processing
     */
    public void setProcessing(List<Processing> processing) {
        this.processing = processing;
    }

    /**
     * Gets product group id.
     *
     * @return the product group id
     */
    public ProductGroupId getProductGroupId() {
        return productGroupId;
    }

    /**
     * Sets product group id.
     *
     * @param productGroupId the product group id
     */
    public void setProductGroupId(ProductGroupId productGroupId) {
        this.productGroupId = productGroupId;
    }


    /**
     * Gets derived from.
     *
     * @return the derived from
     */
    public DerivedFrom getDerivedFrom() {
        return derivedFrom;
    }

    /**
     * Sets derived from.
     *
     * @param derivedFrom the derived from
     */
    public void setDerivedFrom(DerivedFrom derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    /**
     * Gets nominal date.
     *
     * @return the nominal date
     */
    public NominalDate getNominalDate() {
        return nominalDate;
    }

    /**
     * Sets nominal date.
     *
     * @param nominalDate the nominal date
     */
    public void setNominalDate(NominalDate nominalDate) {
        this.nominalDate = nominalDate;
    }


    /**
     * Gets product quality degradation quotation mode.
     *
     * @return the product quality degradation quotation mode
     */
    public ProductQualityDegradationQuotationMode getProductQualityDegradationQuotationMode() {
        return productQualityDegradationQuotationMode;
    }

    /**
     * Sets product quality degradation quotation mode.
     *
     * @param productQualityDegradationQuotationMode the product quality degradation quotation mode
     */
    public void setProductQualityDegradationQuotationMode(
            ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode) {
        this.productQualityDegradationQuotationMode = productQualityDegradationQuotationMode;
    }


    /**
     * Gets acquisition sub type.
     *
     * @return the acquisitionSubType
     */
    public AcquisitionSubType getAcquisitionSubType() {
        return acquisitionSubType;
    }

    /**
     * Sets acquisition sub type.
     *
     * @param acquisitionSubType the acquisitionSubType to set
     */
    public void setAcquisitionSubType(AcquisitionSubType acquisitionSubType) {
        this.acquisitionSubType = acquisitionSubType;
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
