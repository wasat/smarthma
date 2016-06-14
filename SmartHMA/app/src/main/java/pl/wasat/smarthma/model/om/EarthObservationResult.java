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
 * The type Earth observation result.
 */
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
     * Gets browse list.
     *
     * @return the browse list
     */
    public List<Browse> getBrowseList() {
        return browseList;
    }

    /**
     * Sets browse list.
     *
     * @param browseList the browse list
     */
    public void setBrowseList(List<Browse> browseList) {
        this.browseList = browseList;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * Gets parameter.
     *
     * @return the parameter
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Sets parameter.
     *
     * @param parameter the parameter to set
     */
    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    /**
     * Gets mask.
     *
     * @return the mask
     */
    public List<Mask> getMask() {
        return mask;
    }

    /**
     * Sets mask.
     *
     * @param mask the mask
     */
    public void setMask(List<Mask> mask) {
        this.mask = mask;
    }


    /**
     * Gets cloud cover percentage.
     *
     * @return the cloud cover percentage
     */
    public CloudCoverPercentage getCloudCoverPercentage() {
        return cloudCoverPercentage;
    }

    /**
     * Sets cloud cover percentage.
     *
     * @param cloudCoverPercentage the cloud cover percentage
     */
    public void setCloudCoverPercentage(
            CloudCoverPercentage cloudCoverPercentage) {
        this.cloudCoverPercentage = cloudCoverPercentage;
    }


    /**
     * Gets cloud cover percentage quotation mode.
     *
     * @return the cloud cover percentage quotation mode
     */
    public CloudCoverPercentageQuotationMode getCloudCoverPercentageQuotationMode() {
        return cloudCoverPercentageQuotationMode;
    }

    /**
     * Sets cloud cover percentage quotation mode.
     *
     * @param cloudCoverPercentageQuotationMode the cloud cover percentage quotation mode
     */
    public void setCloudCoverPercentageQuotationMode(
            CloudCoverPercentageQuotationMode cloudCoverPercentageQuotationMode) {
        this.cloudCoverPercentageQuotationMode = cloudCoverPercentageQuotationMode;
    }


    /**
     * Gets gml id.
     *
     * @return the gml id
     */
    public String get_gml_id() {
        return _gml_id;
    }

    /**
     * Sets gml id.
     *
     * @param _gml_id the gml id
     */
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
