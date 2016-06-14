
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

package pl.wasat.smarthma.model.dc;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


/**
 * The type Title.
 */
public class Title implements Serializable {

    private String Prefix;
    private String Text;

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
     * Gets text.
     *
     * @return The Text
     */
    public String getText() {
        return Text;
    }

    /**
     * Sets text.
     *
     * @param Text The __text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Prefix).append(Text).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Title)) {
            return false;
        }
        Title rhs = ((Title) other);
        return new EqualsBuilder().append(Prefix, rhs.Prefix).append(Text, rhs.Text).isEquals();
    }

}
