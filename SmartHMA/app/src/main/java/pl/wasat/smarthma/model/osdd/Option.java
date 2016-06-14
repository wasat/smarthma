
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


/**
 * The type Option.
 */
public class Option {

    private String Label;
    private String Value;
    private String Prefix;


    /**
     * Gets label.
     *
     * @return The Label
     */
    public String getLabel() {
        return Label.trim();
    }

    /**
     * Sets label.
     *
     * @param Label The _label
     */
    public void setLabel(String Label) {
        this.Label = Label.trim();
    }

    /**
     * Gets value.
     *
     * @return The Value
     */
    public String getValue() {
        return Value.trim();
    }

    /**
     * Sets value.
     *
     * @param Value The _value
     */
    public void setValue(String Value) {
        this.Value = Value.trim();
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
