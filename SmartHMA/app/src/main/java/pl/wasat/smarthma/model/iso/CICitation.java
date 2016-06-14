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

package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Ci citation.
 */
public class CICitation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Title title;
    private Date date;
    private Identifier identifier;
    private String Prefix;


    /**
     * Gets title.
     *
     * @return The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Gets date.
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets identifier.
     *
     * @return The identifier
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier The identifier
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
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
