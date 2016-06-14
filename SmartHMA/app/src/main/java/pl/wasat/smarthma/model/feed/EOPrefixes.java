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
public class EOPrefixes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _xmlns;
    private String _xmlns_dc;
    private String _xmlns_eo;
    private String _xmlns_geo;
    private String _xmlns_georss;
    private String _xmlns_media;
    private String _xmlns_os;
    private String _xmlns_sru;
    private String _xmlns_time;
    private String _xmlns_wrs;

    /**
     * Gets xmlns.
     *
     * @return the xmlns
     */
    public String get_xmlns() {
        return _xmlns;
    }

    /**
     * Sets xmlns.
     *
     * @param _xmlns the xmlns
     */
    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    /**
     * Gets xmlns dc.
     *
     * @return the xmlns dc
     */
    public String get_xmlns_dc() {
        return _xmlns_dc;
    }

    /**
     * Sets xmlns dc.
     *
     * @param _xmlns_dc the xmlns dc
     */
    public void set_xmlns_dc(String _xmlns_dc) {
        this._xmlns_dc = _xmlns_dc;
    }

    /**
     * Gets xmlns eo.
     *
     * @return the xmlns eo
     */
    public String get_xmlns_eo() {
        return _xmlns_eo;
    }

    /**
     * Sets xmlns eo.
     *
     * @param _xmlns_eo the xmlns eo
     */
    public void set_xmlns_eo(String _xmlns_eo) {
        this._xmlns_eo = _xmlns_eo;
    }

    /**
     * Gets xmlns geo.
     *
     * @return the xmlns geo
     */
    public String get_xmlns_geo() {
        return _xmlns_geo;
    }

    /**
     * Sets xmlns geo.
     *
     * @param _xmlns_geo the xmlns geo
     */
    public void set_xmlns_geo(String _xmlns_geo) {
        this._xmlns_geo = _xmlns_geo;
    }

    /**
     * Gets xmlns georss.
     *
     * @return the xmlns georss
     */
    public String get_xmlns_georss() {
        return _xmlns_georss;
    }

    /**
     * Sets xmlns georss.
     *
     * @param _xmlns_georss the xmlns georss
     */
    public void set_xmlns_georss(String _xmlns_georss) {
        this._xmlns_georss = _xmlns_georss;
    }

    /**
     * Gets xmlns media.
     *
     * @return the xmlns media
     */
    public String get_xmlns_media() {
        return _xmlns_media;
    }

    /**
     * Sets xmlns media.
     *
     * @param _xmlns_media the xmlns media
     */
    public void set_xmlns_media(String _xmlns_media) {
        this._xmlns_media = _xmlns_media;
    }

    /**
     * Gets xmlns os.
     *
     * @return the xmlns os
     */
    public String get_xmlns_os() {
        return _xmlns_os;
    }

    /**
     * Sets xmlns os.
     *
     * @param _xmlns_os the xmlns os
     */
    public void set_xmlns_os(String _xmlns_os) {
        this._xmlns_os = _xmlns_os;
    }

    /**
     * Gets xmlns sru.
     *
     * @return the xmlns sru
     */
    public String get_xmlns_sru() {
        return _xmlns_sru;
    }

    /**
     * Sets xmlns sru.
     *
     * @param _xmlns_sru the xmlns sru
     */
    public void set_xmlns_sru(String _xmlns_sru) {
        this._xmlns_sru = _xmlns_sru;
    }

    /**
     * Gets xmlns time.
     *
     * @return the xmlns time
     */
    public String get_xmlns_time() {
        return _xmlns_time;
    }

    /**
     * Sets xmlns time.
     *
     * @param _xmlns_time the xmlns time
     */
    public void set_xmlns_time(String _xmlns_time) {
        this._xmlns_time = _xmlns_time;
    }

    /**
     * Gets xmlns wrs.
     *
     * @return the xmlns wrs
     */
    public String get_xmlns_wrs() {
        return _xmlns_wrs;
    }

    /**
     * Sets xmlns wrs.
     *
     * @param _xmlns_wrs the xmlns wrs
     */
    public void set_xmlns_wrs(String _xmlns_wrs) {
        this._xmlns_wrs = _xmlns_wrs;
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
