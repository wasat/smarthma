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

package pl.wasat.smarthma.utils.text;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Recursive to string style.
 */
public class RecursiveToStringStyle extends ToStringStyle {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final List<String> recursivePackages;
    private final int recursiveLimit = 10;

    /**
     * Instantiates a new Recursive to string style.
     *
     * @param pkgs the pkgs
     */
    public RecursiveToStringStyle(String... pkgs) {
        super();
        recursivePackages = Arrays.asList(pkgs);
        this.setUseIdentityHashCode(false);
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        for (String packaz : recursivePackages) {
            if (value.getClass().getCanonicalName().startsWith(packaz)) {
                buffer.append(ToStringBuilder.reflectionToString(value, this));
                return;
            }
        }
        buffer.append(value);
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, @SuppressWarnings("rawtypes") Map map) {
        buffer.append(getArrayStart());
        int count = 0;
        for (Entry<?, ?> entry : ((Map<?, ?>) map).entrySet()) {
            appendDetail(buffer, fieldName, entry.getKey());
            buffer.append(getFieldNameValueSeparator());
            appendDetail(buffer, fieldName, entry.getValue());
            appendFieldSeparator(buffer);
            if ((count++) > recursiveLimit) {
                buffer.append("...");
                break;
            }
        }
        removeLastFieldSeparator(buffer);
        buffer.append(getArrayEnd());
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, @SuppressWarnings("rawtypes") Collection coll) {
        appendContentStart(buffer);
        int count = 0;
        for (Object o : coll) {
            appendDetail(buffer, fieldName, o);
            appendFieldSeparator(buffer);
            if ((count++) > recursiveLimit) {
                buffer.append("...");
                break;
            }
        }
        removeLastFieldSeparator(buffer);
        appendContentEnd(buffer);
    }
}
