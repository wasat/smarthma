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
 * The type Content.
 */
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Category category;
    private String _medium;
    private String _type;
    private String _url;


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
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     * Gets medium.
     *
     * @return the medium
     */
    public String get_medium() {
        return _medium;
    }

    /**
     * Sets medium.
     *
     * @param _medium the medium
     */
    public void set_medium(String _medium) {
        this._medium = _medium;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String get_type() {
        return _type;
    }

    /**
     * Sets type.
     *
     * @param _type the type
     */
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String get_url() {
        return _url;
    }

    /**
     * Sets url.
     *
     * @param _url the url
     */
    public void set_url(String _url) {
        this._url = _url;
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
