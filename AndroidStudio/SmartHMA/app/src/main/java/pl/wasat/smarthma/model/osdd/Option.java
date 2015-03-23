
package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Option {

    private String Label;
    private String Value;
    private String Prefix;


    /**
     * @return The Label
     */
    public String getLabel() {
        return Label;
    }

    /**
     * @param Label The _label
     */
    public void setLabel(String Label) {
        this.Label = Label;
    }

    /**
     * @return The Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value The _value
     */
    public void setValue(String Value) {
        this.Value = Value;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The __prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Label).append(Value).append(Prefix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Option)) {
            return false;
        }
        Option rhs = ((Option) other);
        return new EqualsBuilder().append(Label, rhs.Label).append(Value, rhs.Value).append(Prefix, rhs.Prefix).isEquals();
    }

}
