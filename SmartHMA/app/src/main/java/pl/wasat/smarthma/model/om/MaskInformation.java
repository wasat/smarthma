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
 * The type Mask information.
 */
public class MaskInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Type type;
    private SubType subType;
    private Format format;
    private ReferenceSystemIdentifier referenceSystemIdentifier;
    private MultiExtentOf multiExtentOf;
    private FileName fileName;


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
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets sub type.
     *
     * @return the sub type
     */
    public SubType getSubType() {
        return subType;
    }

    /**
     * Sets sub type.
     *
     * @param subType the sub type
     */
    public void setSubType(SubType subType) {
        this.subType = subType;
    }


    /**
     * Gets format.
     *
     * @return the format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Sets format.
     *
     * @param format the format
     */
    public void setFormat(Format format) {
        this.format = format;
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
     * Gets multi extent of.
     *
     * @return the multi extent of
     */
    public MultiExtentOf getMultiExtentOf() {
        return multiExtentOf;
    }

    /**
     * Sets multi extent of.
     *
     * @param multiExtentOf the multi extent of
     */
    public void setMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
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
