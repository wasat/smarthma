<<<<<<< HEAD
<<<<<<< HEAD
/*
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


<<<<<<< HEAD
<<<<<<< HEAD
class DescDoc {
=======
public class DescDoc {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
public class DescDoc {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4

    private pl.wasat.smarthma.model.osdd.OpenSearchDescription OpenSearchDescription;


<<<<<<< HEAD
<<<<<<< HEAD
    */
/**
 * @return The OpenSearchDescription
 *//*

=======
    /**
     * @return The OpenSearchDescription
     */
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
    /**
     * @return The OpenSearchDescription
     */
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public pl.wasat.smarthma.model.osdd.OpenSearchDescription getOpenSearchDescription() {
        return OpenSearchDescription;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    */
/**
 * @param OpenSearchDescription The OpenSearchDescription
 *//*

=======
    /**
     * @param OpenSearchDescription The OpenSearchDescription
     */
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
    /**
     * @param OpenSearchDescription The OpenSearchDescription
     */
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    public void setOpenSearchDescription(pl.wasat.smarthma.model.osdd.OpenSearchDescription OpenSearchDescription) {
        this.OpenSearchDescription = OpenSearchDescription;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(OpenSearchDescription).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DescDoc) == false) {
            return false;
        }
        DescDoc rhs = ((DescDoc) other);
        return new EqualsBuilder().append(OpenSearchDescription, rhs.OpenSearchDescription).isEquals();
    }

}
<<<<<<< HEAD
<<<<<<< HEAD
*/
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
