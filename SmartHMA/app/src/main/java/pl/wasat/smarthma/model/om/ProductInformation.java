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
 * The type Product information.
 */
public class ProductInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private ReferenceSystemIdentifier referenceSystemIdentifier;
    private FileName fileName;
    private Size size;
    private Timeliness timeliness;


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
     * Gets reference system identifier.
     *
     * @return the reference system identifier
     */
    public ReferenceSystemIdentifier getReferenceSystemIdentifier() {
        return referenceSystemIdentifier;
    }

    /**
     * Sets reference system identifier.
     *
     * @param referenceSystemIdentifier the reference system identifier
     */
    public void setReferenceSystemIdentifier(
            ReferenceSystemIdentifier referenceSystemIdentifier) {
        this.referenceSystemIdentifier = referenceSystemIdentifier;
    }


    /**
     * Gets file name.
     *
     * @return the file name
     */
    public FileName getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(FileName fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets timeliness.
     *
     * @return the timeliness
     */
    public Timeliness getTimeliness() {
        return timeliness;
    }

    /**
     * Sets timeliness.
     *
     * @param timeliness the timeliness
     */
    public void setTimeliness(Timeliness timeliness) {
        this.timeliness = timeliness;
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
