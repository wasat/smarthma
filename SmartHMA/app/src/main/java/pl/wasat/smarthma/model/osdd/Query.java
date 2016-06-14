
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

package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The type Query.
 */
public class Query {

    private String GeoBox;
    private String Role;
    private String TimeEnd;
    private String TimeStart;


    /**
     * Gets geo box.
     *
     * @return The GeoBox
     */
    public String getGeoBox() {
        return GeoBox;
    }

    /**
     * Sets geo box.
     *
     * @param GeoBox The _geo:box
     */
    public void setGeoBox(String GeoBox) {
        this.GeoBox = GeoBox;
    }

    /**
     * Gets role.
     *
     * @return The Role
     */
    public String getRole() {
        return Role;
    }

    /**
     * Sets role.
     *
     * @param Role The _role
     */
    public void setRole(String Role) {
        this.Role = Role;
    }

    /**
     * Gets time end.
     *
     * @return The TimeEnd
     */
    public String getTimeEnd() {
        return TimeEnd;
    }

    /**
     * Sets time end.
     *
     * @param TimeEnd The _time:end
     */
    public void setTimeEnd(String TimeEnd) {
        this.TimeEnd = TimeEnd;
    }

    /**
     * Gets time start.
     *
     * @return The TimeStart
     */
    public String getTimeStart() {
        return TimeStart;
    }

    /**
     * Sets time start.
     *
     * @param TimeStart The _time:start
     */
    public void setTimeStart(String TimeStart) {
        this.TimeStart = TimeStart;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(GeoBox).append(Role).append(TimeEnd).append(TimeStart).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Query)) {
            return false;
        }
        Query rhs = ((Query) other);
        return new EqualsBuilder().append(GeoBox, rhs.GeoBox).append(Role, rhs.Role).append(TimeEnd, rhs.TimeEnd).append(TimeStart, rhs.TimeStart).isEquals();
    }

}
