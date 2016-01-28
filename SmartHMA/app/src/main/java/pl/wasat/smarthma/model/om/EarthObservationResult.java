package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EarthObservationResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private List<Browse> browseList = new ArrayList<>();
    private Product product;
    private List<Mask> mask = new ArrayList<>();
    private Parameter parameter;
    private CloudCoverPercentage cloudCoverPercentage;
    private CloudCoverPercentageQuotationMode cloudCoverPercentageQuotationMode;
    private String _gml_id;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    public List<Browse> getBrowseList() {
        return browseList;
    }

    public void setBrowseList(List<Browse> browseList) {
        this.browseList = browseList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * @return the parameter
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public List<Mask> getMask() {
        return mask;
    }

    public void setMask(List<Mask> mask) {
        this.mask = mask;
    }


    public CloudCoverPercentage getCloudCoverPercentage() {
        return cloudCoverPercentage;
    }

    public void setCloudCoverPercentage(
            CloudCoverPercentage cloudCoverPercentage) {
        this.cloudCoverPercentage = cloudCoverPercentage;
    }


    public CloudCoverPercentageQuotationMode getCloudCoverPercentageQuotationMode() {
        return cloudCoverPercentageQuotationMode;
    }

    public void setCloudCoverPercentageQuotationMode(
            CloudCoverPercentageQuotationMode cloudCoverPercentageQuotationMode) {
        this.cloudCoverPercentageQuotationMode = cloudCoverPercentageQuotationMode;
    }


    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
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
