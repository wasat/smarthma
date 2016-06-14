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
 * The type Ci address.
 */
public class CIAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private DeliveryPoint deliveryPoint;
    private City city;
    private PostalCode postalCode;
    private Country country;
    private ElectronicMailAddress electronicMailAddress;
    private String Prefix;


    /**
     * Gets delivery point.
     *
     * @return The deliveryPoint
     */
    public DeliveryPoint getDeliveryPoint() {
        return deliveryPoint;
    }

    /**
     * Sets delivery point.
     *
     * @param deliveryPoint The deliveryPoint
     */
    public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    /**
     * Gets city.
     *
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets postal code.
     *
     * @return The postalCode
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode The postalCode
     */
    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets country.
     *
     * @return The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country The country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Gets electronic mail address.
     *
     * @return The electronicMailAddress
     */
    public ElectronicMailAddress getElectronicMailAddress() {
        return electronicMailAddress;
    }

    /**
     * Sets electronic mail address.
     *
     * @param electronicMailAddress The electronicMailAddress
     */
    public void setElectronicMailAddress(
            ElectronicMailAddress electronicMailAddress) {
        this.electronicMailAddress = electronicMailAddress;
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
