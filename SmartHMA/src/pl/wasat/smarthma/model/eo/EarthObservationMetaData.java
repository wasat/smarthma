
package pl.wasat.smarthma.model.eo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class EarthObservationMetaData {

    private String __prefix;
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
    private List<ProductQualityDegradationTag> productQualityDegradationTag = new ArrayList<ProductQualityDegradationTag>();
    private ProductQualityReportURL productQualityReportURL;
    private ComposedOf composedOf;
    private List<Processing> processing = new ArrayList<Processing>();
    private ProductGroupId productGroupId;
    private DerivedFrom derivedFrom;
    private NominalDate nominalDate;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private AcquisitionSubType acquisitionSubType;
    private ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode;
    

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public EarthObservationMetaData with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public EarthObservationMetaData withIdentifier(Identifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    public EarthObservationMetaData withCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ModificationDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(ModificationDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public EarthObservationMetaData withModificationDate(ModificationDate modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public ParentIdentifier getParentIdentifier() {
        return parentIdentifier;
    }

    public void setParentIdentifier(ParentIdentifier parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }

    public EarthObservationMetaData withParentIdentifier(ParentIdentifier parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
        return this;
    }

    public AcquisitionType getAcquisitionType() {
        return acquisitionType;
    }

    public void setAcquisitionType(AcquisitionType acquisitionType) {
        this.acquisitionType = acquisitionType;
    }

    public EarthObservationMetaData withAcquisitionType(AcquisitionType acquisitionType) {
        this.acquisitionType = acquisitionType;
        return this;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public EarthObservationMetaData withProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public EarthObservationMetaData withStatus(Status status) {
        this.status = status;
        return this;
    }

    public StatusSubType getStatusSubType() {
        return statusSubType;
    }

    public void setStatusSubType(StatusSubType statusSubType) {
        this.statusSubType = statusSubType;
    }

    public EarthObservationMetaData withStatusSubType(StatusSubType statusSubType) {
        this.statusSubType = statusSubType;
        return this;
    }

    public DownlinkedTo getDownlinkedTo() {
        return downlinkedTo;
    }

    public void setDownlinkedTo(DownlinkedTo downlinkedTo) {
        this.downlinkedTo = downlinkedTo;
    }

    public EarthObservationMetaData withDownlinkedTo(DownlinkedTo downlinkedTo) {
        this.downlinkedTo = downlinkedTo;
        return this;
    }

    public ArchivedIn getArchivedIn() {
        return archivedIn;
    }

    public void setArchivedIn(ArchivedIn archivedIn) {
        this.archivedIn = archivedIn;
    }

    public EarthObservationMetaData withArchivedIn(ArchivedIn archivedIn) {
        this.archivedIn = archivedIn;
        return this;
    }

    public ProductQualityDegradation getProductQualityDegradation() {
        return productQualityDegradation;
    }

    public void setProductQualityDegradation(ProductQualityDegradation productQualityDegradation) {
        this.productQualityDegradation = productQualityDegradation;
    }

    public EarthObservationMetaData withProductQualityDegradation(ProductQualityDegradation productQualityDegradation) {
        this.productQualityDegradation = productQualityDegradation;
        return this;
    }

    public ProductQualityStatus getProductQualityStatus() {
        return productQualityStatus;
    }

    public void setProductQualityStatus(ProductQualityStatus productQualityStatus) {
        this.productQualityStatus = productQualityStatus;
    }

    public EarthObservationMetaData withProductQualityStatus(ProductQualityStatus productQualityStatus) {
        this.productQualityStatus = productQualityStatus;
        return this;
    }

    public List<ProductQualityDegradationTag> getProductQualityDegradationTag() {
        return productQualityDegradationTag;
    }

    public void setProductQualityDegradationTag(List<ProductQualityDegradationTag> productQualityDegradationTag) {
        this.productQualityDegradationTag = productQualityDegradationTag;
    }

    public EarthObservationMetaData withProductQualityDegradationTag(List<ProductQualityDegradationTag> productQualityDegradationTag) {
        this.productQualityDegradationTag = productQualityDegradationTag;
        return this;
    }

    public ProductQualityReportURL getProductQualityReportURL() {
        return productQualityReportURL;
    }

    public void setProductQualityReportURL(ProductQualityReportURL productQualityReportURL) {
        this.productQualityReportURL = productQualityReportURL;
    }

    public EarthObservationMetaData withProductQualityReportURL(ProductQualityReportURL productQualityReportURL) {
        this.productQualityReportURL = productQualityReportURL;
        return this;
    }

    public ComposedOf getComposedOf() {
        return composedOf;
    }

    public void setComposedOf(ComposedOf composedOf) {
        this.composedOf = composedOf;
    }

    public EarthObservationMetaData withComposedOf(ComposedOf composedOf) {
        this.composedOf = composedOf;
        return this;
    }

    public List<Processing> getProcessing() {
        return processing;
    }

    public void setProcessing(List<Processing> processing) {
        this.processing = processing;
    }

    public EarthObservationMetaData withProcessing(List<Processing> processing) {
        this.processing = processing;
        return this;
    }

    public ProductGroupId getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(ProductGroupId productGroupId) {
        this.productGroupId = productGroupId;
    }

    public EarthObservationMetaData withProductGroupId(ProductGroupId productGroupId) {
        this.productGroupId = productGroupId;
        return this;
    }

    public DerivedFrom getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(DerivedFrom derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    public EarthObservationMetaData withDerivedFrom(DerivedFrom derivedFrom) {
        this.derivedFrom = derivedFrom;
        return this;
    }

    public NominalDate getNominalDate() {
        return nominalDate;
    }

    public void setNominalDate(NominalDate nominalDate) {
        this.nominalDate = nominalDate;
    }

    public EarthObservationMetaData withNominalDate(NominalDate nominalDate) {
        this.nominalDate = nominalDate;
        return this;
    }

    
    
    public ProductQualityDegradationQuotationMode getProductQualityDegradationQuotationMode() {
        return productQualityDegradationQuotationMode;
    }

    public void setProductQualityDegradationQuotationMode(ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode) {
        this.productQualityDegradationQuotationMode = productQualityDegradationQuotationMode;
    }

    public EarthObservationMetaData withProductQualityDegradationQuotationMode(ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode) {
        this.productQualityDegradationQuotationMode = productQualityDegradationQuotationMode;
        return this;
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
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
