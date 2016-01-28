package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class CIAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private DeliveryPoint deliveryPoint;
    private City city;
    private PostalCode postalCode;
    private Country country;
    private ElectronicMailAddress electronicMailAddress;
    private String Prefix;


    /**
     * @return The deliveryPoint
     */
    public DeliveryPoint getDeliveryPoint() {
        return deliveryPoint;
    }

    /**
     * @param deliveryPoint The deliveryPoint
     */
    public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    /**
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return The postalCode
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode The postalCode
     */
    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return The electronicMailAddress
     */
    public ElectronicMailAddress getElectronicMailAddress() {
        return electronicMailAddress;
    }

    /**
     * @param electronicMailAddress The electronicMailAddress
     */
    public void setElectronicMailAddress(
            ElectronicMailAddress electronicMailAddress) {
        this.electronicMailAddress = electronicMailAddress;
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
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
