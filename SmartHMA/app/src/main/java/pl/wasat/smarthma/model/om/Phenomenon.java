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
 * The type Phenomenon.
 */
public class Phenomenon implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Name name;
    private String _xmlns_ns1;
    private String _ns1_id;
    private String _xmlns_gml31;
    private String _gml31_id;


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
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Gets xmlns ns 1.
     *
     * @return the xmlns ns 1
     */
    public String get_xmlns_ns1() {
        return _xmlns_ns1;
    }

    /**
     * Sets xmlns ns 1.
     *
     * @param _xmlns_ns1 the xmlns ns 1
     */
    public void set_xmlns_ns1(String _xmlns_ns1) {
        this._xmlns_ns1 = _xmlns_ns1;
    }

    /**
     * Gets ns 1 id.
     *
     * @return the ns 1 id
     */
    public String get_ns1_id() {
        return _ns1_id;
    }

    /**
     * Sets ns 1 id.
     *
     * @param _ns1_id the ns 1 id
     */
    public void set_ns1_id(String _ns1_id) {
        this._ns1_id = _ns1_id;
    }

    /**
     * Gets xmlns gml 31.
     *
     * @return the xmlns gml 31
     */
    public String get_xmlns_gml31() {
        return _xmlns_gml31;
    }

    /**
     * Sets xmlns gml 31.
     *
     * @param _xmlns_gml31 the xmlns gml 31
     */
    public void set_xmlns_gml31(String _xmlns_gml31) {
        this._xmlns_gml31 = _xmlns_gml31;
    }

    /**
     * Gets gml 31 id.
     *
     * @return the gml 31 id
     */
    public String get_gml31_id() {
        return _gml31_id;
    }

    /**
     * Sets gml 31 id.
     *
     * @param _gml31_id the gml 31 id
     */
    public void set_gml31_id(String _gml31_id) {
        this._gml31_id = _gml31_id;
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
