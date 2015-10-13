
package pl.wasat.smarthma.model.entry;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Type;
    private String Cdata;

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The _type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return The Cdata
     */
    public String getCdata() {
        return Cdata;
    }

    /**
     * @param Cdata The __cdata
     */
    public void setCdata(String Cdata) {
        this.Cdata = Cdata;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Type).append(Cdata).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Summary)) {
            return false;
        }
        Summary rhs = ((Summary) other);
        return new EqualsBuilder().append(Type, rhs.Type).append(Cdata, rhs.Cdata).isEquals();
    }

}
