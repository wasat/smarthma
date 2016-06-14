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

package pl.wasat.smarthma.model.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Exception report.
 */
public class ExceptionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prefix;
    private Exception exception;
    private String xmlnsOws;
    private String xmlnsXlink;
    private String xmlnsXsi;
    private String version;
    private String xmlLang;
    private String xsiSchemaLocation;
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets prefix.
     *
     * @param prefix the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Sets exception.
     *
     * @param exception the exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }


    /**
     * Gets xmlns ows.
     *
     * @return the xmlns ows
     */
    public String getXmlnsOws() {
        return xmlnsOws;
    }

    /**
     * Sets xmlns ows.
     *
     * @param xmlnsOws the xmlns ows
     */
    public void setXmlnsOws(String xmlnsOws) {
        this.xmlnsOws = xmlnsOws;
    }


    /**
     * Gets xmlns xlink.
     *
     * @return the xmlns xlink
     */
    public String getXmlnsXlink() {
        return xmlnsXlink;
    }

    /**
     * Sets xmlns xlink.
     *
     * @param xmlnsXlink the xmlns xlink
     */
    public void setXmlnsXlink(String xmlnsXlink) {
        this.xmlnsXlink = xmlnsXlink;
    }


    /**
     * Gets xmlns xsi.
     *
     * @return the xmlns xsi
     */
    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    /**
     * Sets xmlns xsi.
     *
     * @param xmlnsXsi the xmlns xsi
     */
    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }


    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets xml lang.
     *
     * @return the xml lang
     */
    public String getXmlLang() {
        return xmlLang;
    }

    /**
     * Sets xml lang.
     *
     * @param xmlLang the xml lang
     */
    public void setXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
    }

    /**
     * Gets xsi schema location.
     *
     * @return the xsi schema location
     */
    public String getXsiSchemaLocation() {
        return xsiSchemaLocation;
    }

    /**
     * Sets xsi schema location.
     *
     * @param xsiSchemaLocation the xsi schema location
     */
    public void setXsiSchemaLocation(String xsiSchemaLocation) {
        this.xsiSchemaLocation = xsiSchemaLocation;
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
