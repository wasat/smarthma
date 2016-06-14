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
 * The type Service reference.
 */
public class ServiceReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private RequestMessage requestMessage;
    private String _xmlns;
    private String _xlink_href;


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
     * Gets request message.
     *
     * @return the request message
     */
    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    /**
     * Sets request message.
     *
     * @param requestMessage the request message
     */
    public void setRequestMessage(RequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }


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
     */
    public void set_xmlns() {
        this._xmlns = "xmlns";
    }

    /**
     * Gets xlink href.
     *
     * @return the xlink href
     */
    public String get_xlink_href() {
        return _xlink_href;
    }

    /**
     * Sets xlink href.
     *
     * @param _xlink_href the xlink href
     */
    public void set_xlink_href(String _xlink_href) {
        this._xlink_href = _xlink_href;
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
