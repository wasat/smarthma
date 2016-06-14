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

package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;

/**
 * The type Meta info.
 */
public class MetaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String dateModified;

    /**
     * Instantiates a new Meta info.
     */
    public MetaInfo() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets date modified.
     *
     * @return the dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * Sets date modified.
     *
     * @param dateModified the dateModified to set
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dateModified == null) ? 0 : dateModified.hashCode());
        result = prime * result + id;
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MetaInfo other = (MetaInfo) obj;
        if (dateModified == null) {
            if (other.dateModified != null)
                return false;
        } else if (!dateModified.equals(other.dateModified))
            return false;
        return id == other.id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MetaInfo [dateModified=" + dateModified + "]";
    }

}
