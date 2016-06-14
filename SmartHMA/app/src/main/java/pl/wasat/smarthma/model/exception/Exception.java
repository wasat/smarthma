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

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Exception.
 */
public class Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prefix;
    private ExceptionText exceptionText;
    private String exceptionCode;
    private String locator;


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
     * Gets exception text.
     *
     * @return the exception text
     */
    public ExceptionText getExceptionText() {
        return exceptionText;
    }

    /**
     * Sets exception text.
     *
     * @param exceptionText the exception text
     */
    public void setExceptionText(ExceptionText exceptionText) {
        this.exceptionText = exceptionText;
    }

    /**
     * Gets exception code.
     *
     * @return the exception code
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Sets exception code.
     *
     * @param exceptionCode the exception code
     */
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }


    /**
     * Gets locator.
     *
     * @return the locator
     */
    public String getLocator() {
        return locator;
    }

    /**
     * Sets locator.
     *
     * @param locator the locator
     */
    public void setLocator(String locator) {
        this.locator = locator;
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
