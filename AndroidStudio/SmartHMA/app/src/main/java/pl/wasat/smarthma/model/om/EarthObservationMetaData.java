package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

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

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    public ModificationDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(ModificationDate modificationDate) {
        this.modificationDate = modificationDate;
    }


    public ParentIdentifier getParentIdentifier() {
        return parentIdentifier;
    }

    public void setParentIdentifier(ParentIdentifier parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }


    public AcquisitionType getAcquisitionType() {
        return acquisitionType;
    }

    public void setAcquisitionType(AcquisitionType acquisitionType) {
        this.acquisitionType = acquisitionType;
    }


    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusSubType getStatusSubType() {
        return statusSubType;
    }

    public void setStatusSubType(StatusSubType statusSubType) {
        this.statusSubType = statusSubType;
    }

    public DownlinkedTo getDownlinkedTo() {
        return downlinkedTo;
    }

    public void setDownlinkedTo(DownlinkedTo downlinkedTo) {
        this.downlinkedTo = downlinkedTo;
    }


    public ArchivedIn getArchivedIn() {
        return archivedIn;
    }

    public void setArchivedIn(ArchivedIn archivedIn) {
        this.archivedIn = archivedIn;
    }


    public ProductQualityDegradation getProductQualityDegradation() {
        return productQualityDegradation;
    }

    public void setProductQualityDegradation(
            ProductQualityDegradation productQualityDegradation) {
        this.productQualityDegradation = productQualityDegradation;
    }


    public ProductQualityStatus getProductQualityStatus() {
        return productQualityStatus;
    }

    public void setProductQualityStatus(
            ProductQualityStatus productQualityStatus) {
        this.productQualityStatus = productQualityStatus;
    }


    public List<ProductQualityDegradationTag> getProductQualityDegradationTag() {
        return productQualityDegradationTag;
    }

    public void setProductQualityDegradationTag(
            List<ProductQualityDegradationTag> productQualityDegradationTag) {
        this.productQualityDegradationTag = productQualityDegradationTag;
    }


    public ProductQualityReportURL getProductQualityReportURL() {
        return productQualityReportURL;
    }

    public void setProductQualityReportURL(
            ProductQualityReportURL productQualityReportURL) {
        this.productQualityReportURL = productQualityReportURL;
    }

    public ComposedOf getComposedOf() {
        return composedOf;
    }

    public void setComposedOf(ComposedOf composedOf) {
        this.composedOf = composedOf;
    }


    public List<Processing> getProcessing() {
        return processing;
    }

    public void setProcessing(List<Processing> processing) {
        this.processing = processing;
    }

    public ProductGroupId getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(ProductGroupId productGroupId) {
        this.productGroupId = productGroupId;
    }


    public DerivedFrom getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(DerivedFrom derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    public NominalDate getNominalDate() {
        return nominalDate;
    }

    public void setNominalDate(NominalDate nominalDate) {
        this.nominalDate = nominalDate;
    }


    public ProductQualityDegradationQuotationMode getProductQualityDegradationQuotationMode() {
        return productQualityDegradationQuotationMode;
    }

    public void setProductQualityDegradationQuotationMode(
            ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode) {
        this.productQualityDegradationQuotationMode = productQualityDegradationQuotationMode;
    }


    /**
     * @return the acquisitionSubType
     */
    public AcquisitionSubType getAcquisitionSubType() {
        return acquisitionSubType;
    }

    /**
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
