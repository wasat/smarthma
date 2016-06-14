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

/**
 *
 */
package pl.wasat.smarthma.utils.text;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The type Smart hma string style.
 *
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 27-06-2014
 */
public class SmartHMAStringStyle extends ToStringStyle {
    /**
     *
     */
    private static final long serialVersionUID = -109264611603721844L;

    /**
     * Instantiates a new Smart hma string style.
     */
    public SmartHMAStringStyle() {// constructor is copied from
        super();

        this.setUseShortClassName(true);
        this.setUseFieldNames(true);
        this.setUseIdentityHashCode(false);
        this.setContentStart("");
        this.setFieldSeparator(SystemUtils.LINE_SEPARATOR);
        this.setFieldSeparatorAtStart(false);
        this.setFieldSeparatorAtEnd(true);
        this.setUseClassName(false);
        this.setContentEnd("");
        this.setArrayEnd(" zzz ");
        this.setArrayStart(" sss ");
        this.setArraySeparator(" qqq ");
        this.setFieldNameValueSeparator(" - ");
        this.setArrayContentDetail(true);

    }

    // override this to do checking of null, so only non-nulls are printed out
    // in toString
    @Override
    public void append(StringBuffer buffer, String fieldName, Object value,
                       Boolean fullDetail) {

        if (fieldName.equalsIgnoreCase("_text")) {
            fieldName = "value";
        }
        if (fieldName.equalsIgnoreCase("_xlink_href")) {
            fieldName = "link";
        }
        if (fieldName.equalsIgnoreCase("uom")) {
            fieldName = "unit";
        }

        if (!fieldName.equalsIgnoreCase("additionalProperties")
                && !fieldName.equalsIgnoreCase("_gml_id")
                && !fieldName.equalsIgnoreCase("_xmlns") && value != null) {
            super.append(buffer, fieldName, value, fullDetail);
        }

        if (buffer.length() > 0) {
            int idx = buffer.indexOf(",");
            if (idx >= 0) {
                String str = buffer.toString();
                String s = str.replaceAll(", ", "");
                //noinspection UnusedAssignment
                buffer = new StringBuffer(s);
            }
        }
    }
}
