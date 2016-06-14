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
 * The type Time period.
 */
public class TimePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    private BeginPosition beginPosition;
    private EndPosition endPosition;
    private String GmlId;
    private String Prefix;


    /**
     * Gets begin position.
     *
     * @return The beginPosition
     */
    public BeginPosition getBeginPosition() {
        return beginPosition;
    }

    /**
     * Sets begin position.
     *
     * @param beginPosition The beginPosition
     */
    public void setBeginPosition(BeginPosition beginPosition) {
        this.beginPosition = beginPosition;
    }

    /**
     * Gets end position.
     *
     * @return The endPosition
     */
    public EndPosition getEndPosition() {
        return endPosition;
    }

    /**
     * Sets end position.
     *
     * @param endPosition The endPosition
     */
    public void setEndPosition(EndPosition endPosition) {
        this.endPosition = endPosition;
    }

    /**
     * Gets gml id.
     *
     * @return The GmlId
     */
    public String getGmlId() {
        return GmlId;
    }

    /**
     * Sets gml id.
     *
     * @param GmlId The _gml:id
     */
    public void setGmlId(String GmlId) {
        this.GmlId = GmlId;
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
