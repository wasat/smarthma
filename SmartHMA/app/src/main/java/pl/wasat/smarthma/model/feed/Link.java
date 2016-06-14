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

package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


/**
 * The type Link.
 */
public class Link implements Serializable {

    /**
     * The constant REL_ALTERNATE.
     */
    public static final String REL_ALTERNATE = "alternate";
    /**
     * The constant REL_SEARCH.
     */
    public static final String REL_SEARCH = "search";
    /**
     * The constant REL_RESULTS.
     */
    public static final String REL_RESULTS = "results";
    /**
     * The constant REL_COLLECTION.
     */
    public static final String REL_COLLECTION = "collection";
    /**
     * The constant TYPE_ATOM_XML.
     */
    public static final String TYPE_ATOM_XML = "application/atom+xml";
    /**
     * The constant TYPE_OSDD_XML.
     */
    public static final String TYPE_OSDD_XML = "application/opensearchdescription+xml";
    /**
     * The constant REL_SELF.
     */
    public static final String REL_SELF = "self";
    /**
     * The constant REL_FIRST.
     */
    public static final String REL_FIRST = "first";
    /**
     * The constant REL_NEXT.
     */
    public static final String REL_NEXT = "next";
    /**
     * The constant REL_LAST.
     */
    public static final String REL_LAST = "last";
    /**
     * The constant REL_PREVIOUS.
     */
    public static final String REL_PREVIOUS = "previous";

    private static final long serialVersionUID = 1L;

    private String href;
    private String rel;
    private String title;
    private String type;

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
        validateHref();
        return href;
    }

    /**
     * Sets href.
     *
     * @param href the href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Gets rel.
     *
     * @return the rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets rel.
     *
     * @param rel the rel
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        if (title == null) return "";
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    private void validateHref() {
        this.href = this.href.replaceAll("\\+", "%2B");
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
