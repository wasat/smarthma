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

package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Ex geographic bounding box.
 */
public class EXGeographicBoundingBox implements Serializable {

    private static final long serialVersionUID = 1L;

    private WestBoundLongitude westBoundLongitude;
    private EastBoundLongitude eastBoundLongitude;
    private SouthBoundLatitude southBoundLatitude;
    private NorthBoundLatitude northBoundLatitude;
    private String Prefix;


    /**
     * Gets west bound longitude.
     *
     * @return The westBoundLongitude
     */
    public WestBoundLongitude getWestBoundLongitude() {
        return westBoundLongitude;
    }

    /**
     * Sets west bound longitude.
     *
     * @param westBoundLongitude The westBoundLongitude
     */
    public void setWestBoundLongitude(WestBoundLongitude westBoundLongitude) {
        this.westBoundLongitude = westBoundLongitude;
    }

    /**
     * Gets east bound longitude.
     *
     * @return The eastBoundLongitude
     */
    public EastBoundLongitude getEastBoundLongitude() {
        return eastBoundLongitude;
    }

    /**
     * Sets east bound longitude.
     *
     * @param eastBoundLongitude The eastBoundLongitude
     */
    public void setEastBoundLongitude(EastBoundLongitude eastBoundLongitude) {
        this.eastBoundLongitude = eastBoundLongitude;
    }

    /**
     * Gets south bound latitude.
     *
     * @return The southBoundLatitude
     */
    public SouthBoundLatitude getSouthBoundLatitude() {
        return southBoundLatitude;
    }

    /**
     * Sets south bound latitude.
     *
     * @param southBoundLatitude The southBoundLatitude
     */
    public void setSouthBoundLatitude(SouthBoundLatitude southBoundLatitude) {
        this.southBoundLatitude = southBoundLatitude;
    }

    /**
     * Gets north bound latitude.
     *
     * @return The northBoundLatitude
     */
    public NorthBoundLatitude getNorthBoundLatitude() {
        return northBoundLatitude;
    }

    /**
     * Sets north bound latitude.
     *
     * @param northBoundLatitude The northBoundLatitude
     */
    public void setNorthBoundLatitude(NorthBoundLatitude northBoundLatitude) {
        this.northBoundLatitude = northBoundLatitude;
    }

    /**
     * Gets prefix.
     *
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * Sets prefix.
     *
     * @param Prefix The __prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
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
