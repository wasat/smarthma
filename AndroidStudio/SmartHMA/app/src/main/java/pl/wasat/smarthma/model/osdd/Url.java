
package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Url {

    private String Rel;
    private String Template;
    private String Type;


    /**
     * @return The Rel
     */
    public String getRel() {
        return Rel;
    }

    /**
     * @param Rel The _rel
     */
    public void setRel(String Rel) {
        this.Rel = Rel;
    }

    /**
     * @return The Template
     */
    public String getTemplate() {
        return Template;
    }

    /**
     * @param Template The _template
     */
    public void setTemplate(String Template) {
        this.Template = Template;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Rel).append(Template).append(Type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
<<<<<<< HEAD
<<<<<<< HEAD
        if (!(other instanceof Url)) {
=======
        if ((other instanceof Url) == false) {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
        if ((other instanceof Url) == false) {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
            return false;
        }
        Url rhs = ((Url) other);
        return new EqualsBuilder().append(Rel, rhs.Rel).append(Template, rhs.Template).append(Type, rhs.Type).isEquals();
    }

}
