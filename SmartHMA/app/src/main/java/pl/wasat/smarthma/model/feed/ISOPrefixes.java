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
 * Created by Daniel Zinkiewicz on 11.02.15 01:47.
 * Part of the project  ${PROJECT_NAME}
 */
public class ISOPrefixes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Xmlns;
    private String XmlnsDc;
    private String XmlnsGeo;
    private String XmlnsGeorss;
    private String XmlnsGml;
    private String XmlnsOs;
    private String XmlnsSemantic;
    private String XmlnsSru;
    private String XmlnsTime;
    private String XmlnsUrlencoder;
    private String XmlnsWrs;
    private String XmlnsXlink;


    /**
     * Gets xmlns.
     *
     * @return the xmlns
     */
    public String getXmlns() {
        return Xmlns;
    }

    /**
     * Sets xmlns.
     *
     * @param xmlns the xmlns
     */
    public void setXmlns(String xmlns) {
        Xmlns = xmlns;
    }

    /**
     * Gets xmlns dc.
     *
     * @return the xmlns dc
     */
    public String getXmlnsDc() {
        return XmlnsDc;
    }

    /**
     * Sets xmlns dc.
     *
     * @param xmlnsDc the xmlns dc
     */
    public void setXmlnsDc(String xmlnsDc) {
        XmlnsDc = xmlnsDc;
    }

    /**
     * Gets xmlns geo.
     *
     * @return the xmlns geo
     */
    public String getXmlnsGeo() {
        return XmlnsGeo;
    }

    /**
     * Sets xmlns geo.
     *
     * @param xmlnsGeo the xmlns geo
     */
    public void setXmlnsGeo(String xmlnsGeo) {
        XmlnsGeo = xmlnsGeo;
    }

    /**
     * Gets xmlns georss.
     *
     * @return the xmlns georss
     */
    public String getXmlnsGeorss() {
        return XmlnsGeorss;
    }

    /**
     * Sets xmlns georss.
     *
     * @param xmlnsGeorss the xmlns georss
     */
    public void setXmlnsGeorss(String xmlnsGeorss) {
        XmlnsGeorss = xmlnsGeorss;
    }

    /**
     * Gets xmlns gml.
     *
     * @return the xmlns gml
     */
    public String getXmlnsGml() {
        return XmlnsGml;
    }

    /**
     * Sets xmlns gml.
     *
     * @param xmlnsGml the xmlns gml
     */
    public void setXmlnsGml(String xmlnsGml) {
        XmlnsGml = xmlnsGml;
    }

    /**
     * Gets xmlns os.
     *
     * @return the xmlns os
     */
    public String getXmlnsOs() {
        return XmlnsOs;
    }

    /**
     * Sets xmlns os.
     *
     * @param xmlnsOs the xmlns os
     */
    public void setXmlnsOs(String xmlnsOs) {
        XmlnsOs = xmlnsOs;
    }

    /**
     * Gets xmlns semantic.
     *
     * @return the xmlns semantic
     */
    public String getXmlnsSemantic() {
        return XmlnsSemantic;
    }

    /**
     * Sets xmlns semantic.
     *
     * @param xmlnsSemantic the xmlns semantic
     */
    public void setXmlnsSemantic(String xmlnsSemantic) {
        XmlnsSemantic = xmlnsSemantic;
    }

    /**
     * Gets xmlns sru.
     *
     * @return the xmlns sru
     */
    public String getXmlnsSru() {
        return XmlnsSru;
    }

    /**
     * Sets xmlns sru.
     *
     * @param xmlnsSru the xmlns sru
     */
    public void setXmlnsSru(String xmlnsSru) {
        XmlnsSru = xmlnsSru;
    }

    /**
     * Gets xmlns time.
     *
     * @return the xmlns time
     */
    public String getXmlnsTime() {
        return XmlnsTime;
    }

    /**
     * Sets xmlns time.
     *
     * @param xmlnsTime the xmlns time
     */
    public void setXmlnsTime(String xmlnsTime) {
        XmlnsTime = xmlnsTime;
    }

    /**
     * Gets xmlns urlencoder.
     *
     * @return the xmlns urlencoder
     */
    public String getXmlnsUrlencoder() {
        return XmlnsUrlencoder;
    }

    /**
     * Sets xmlns urlencoder.
     *
     * @param xmlnsUrlencoder the xmlns urlencoder
     */
    public void setXmlnsUrlencoder(String xmlnsUrlencoder) {
        XmlnsUrlencoder = xmlnsUrlencoder;
    }

    /**
     * Gets xmlns wrs.
     *
     * @return the xmlns wrs
     */
    public String getXmlnsWrs() {
        return XmlnsWrs;
    }

    /**
     * Sets xmlns wrs.
     *
     * @param xmlnsWrs the xmlns wrs
     */
    public void setXmlnsWrs(String xmlnsWrs) {
        XmlnsWrs = xmlnsWrs;
    }

    /**
     * Gets xmlns xlink.
     *
     * @return the xmlns xlink
     */
    public String getXmlnsXlink() {
        return XmlnsXlink;
    }

    /**
     * Sets xmlns xlink.
     *
     * @param xmlnsXlink the xmlns xlink
     */
    public void setXmlnsXlink(String xmlnsXlink) {
        XmlnsXlink = xmlnsXlink;
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
