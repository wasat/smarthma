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

package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Archiving information.
 */
public class ArchivingInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private ArchivingCenter archivingCenter;
    private ArchivingDate archivingDate;


    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String get_prefix() {
        return _prefix;
    }

    /**
     * Sets prefix.
     *
     * @param _prefix the prefix
     */
    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    /**
     * Gets archiving center.
     *
     * @return the archiving center
     */
    public ArchivingCenter getArchivingCenter() {
        return archivingCenter;
    }

    /**
     * Sets archiving center.
     *
     * @param archivingCenter the archiving center
     */
    public void setArchivingCenter(ArchivingCenter archivingCenter) {
        this.archivingCenter = archivingCenter;
    }


    /**
     * Gets archiving date.
     *
     * @return the archiving date
     */
    public ArchivingDate getArchivingDate() {
        return archivingDate;
    }

    /**
     * Sets archiving date.
     *
     * @param archivingDate the archiving date
     */
    public void setArchivingDate(ArchivingDate archivingDate) {
        this.archivingDate = archivingDate;
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
