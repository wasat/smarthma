
package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


public class Parameter {

    private List<pl.wasat.smarthma.model.osdd.Option> Options = new ArrayList<>();
    private String Name;
    private String Value;
    private String Prefix;
    private String Pattern;
    private String MaxInclusive;
    private String MinInclusive;


    /**
     * @return The Options
     */
    public List<pl.wasat.smarthma.model.osdd.Option> getOptions() {
        return Options;
    }

    /**
     * @param Option The Options
     */
    public void setOptions(List<pl.wasat.smarthma.model.osdd.Option> Option) {
        this.Options = Option;
    }

    /**
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name The _name
     */
    public void setName(String Name) {
        this.Name = Name;
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

    public String getPattern() {
        return Pattern;
    }

    public void setPattern(String pattern) {
        Pattern = pattern;
    }

    public String getMaxInclusive() {
        return MaxInclusive;
    }

    public void setMaxInclusive(String maxInclusive) {
        MaxInclusive = maxInclusive;
    }

    public String getMinInclusive() {
        return MinInclusive;
    }

    public void setMinInclusive(String minInclusive) {
        MinInclusive = minInclusive;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Options).append(Name).append(Value).append(Prefix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Parameter)) {
            return false;
        }
        Parameter rhs = ((Parameter) other);
        return new EqualsBuilder().append(Options, rhs.Options).append(Name, rhs.Name).append(Value, rhs.Value).append(Prefix, rhs.Prefix).isEquals();
    }

}
