package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class CIContact implements Serializable {

    private static final long serialVersionUID = 1L;

    private Phone phone;
    private Address address;
    private OnlineResource onlineResource;
    private String Prefix;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    /**
     * @return The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return The onlineResource
     */
    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    /**
     * @param onlineResource The onlineResource
     */
    public void setOnlineResource(OnlineResource onlineResource) {
        this.onlineResource = onlineResource;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(phone).append(address)
                .append(onlineResource).append(Prefix)
                .append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CIContact)) {
            return false;
        }
        CIContact rhs = ((CIContact) other);
        return new EqualsBuilder().append(phone, rhs.phone)
                .append(address, rhs.address)
                .append(onlineResource, rhs.onlineResource)
                .append(Prefix, rhs.Prefix)
                .append(additionalProperties, rhs.additionalProperties)
                .isEquals();
    }

}
