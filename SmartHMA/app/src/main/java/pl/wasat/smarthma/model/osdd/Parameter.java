
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

package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Parameter.
 */
public class Parameter {

    private List<pl.wasat.smarthma.model.osdd.Option> Options = new ArrayList<>();
    private String Name;
    private String Value;
    private String Prefix;
    private String Pattern;
    private String MaxInclusive;
    private String MinInclusive;


    /**
     * Gets options.
     *
     * @return The Options
     */
    public List<pl.wasat.smarthma.model.osdd.Option> getOptions() {
        return Options;
    }

    /**
     * Sets options.
     *
     * @param Option The Options
     */
    public void setOptions(List<pl.wasat.smarthma.model.osdd.Option> Option) {
        this.Options = Option;
    }

    /**
     * Gets name.
     *
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets name.
     *
     * @param Name The _name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Gets value.
     *
     * @return The Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * Sets value.
     *
     * @param Value The _value
     */
    public void setValue(String Value) {
        this.Value = Value;
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
     * @param Prefix The __prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    /**
     * Gets pattern.
     *
     * @return the pattern
     */
    public String getPattern() {
        return Pattern;
    }

    /**
     * Sets pattern.
     *
     * @param pattern the pattern
     */
    public void setPattern(String pattern) {
        Pattern = pattern;
    }

    /**
     * Gets max inclusive.
     *
     * @return the max inclusive
     */
    public String getMaxInclusive() {
        return MaxInclusive;
    }

    /**
     * Sets max inclusive.
     *
     * @param maxInclusive the max inclusive
     */
    public void setMaxInclusive(String maxInclusive) {
        MaxInclusive = maxInclusive;
    }

    /**
     * Gets min inclusive.
     *
     * @return the min inclusive
     */
    public String getMinInclusive() {
        return MinInclusive;
    }

    /**
     * Sets min inclusive.
     *
     * @param minInclusive the min inclusive
     */
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
