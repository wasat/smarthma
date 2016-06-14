
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

package pl.wasat.smarthma.model.entry;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * The type Summary.
 */
public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Type;
    private String Cdata;

    /**
     * Gets type.
     *
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * Sets type.
     *
     * @param Type The _type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * Gets cdata.
     *
     * @return The Cdata
     */
    public String getCdata() {
        return Cdata;
    }

    /**
     * Sets cdata.
     *
     * @param Cdata The __cdata
     */
    public void setCdata(String Cdata) {
        this.Cdata = Cdata;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Type).append(Cdata).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Summary)) {
            return false;
        }
        Summary rhs = ((Summary) other);
        return new EqualsBuilder().append(Type, rhs.Type).append(Cdata, rhs.Cdata).isEquals();
    }

}
