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
 * The type Footprint.
 */
public class Footprint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private MultiExtentOf multiExtentOf;
    private MaximumAltitude maximumAltitude;
    private MinimumAltitude minimumAltitude;
    private NominalTrack nominalTrack;
    private OccultationPoints occultationPoints;
    private CenterOf centerOf;
    private LocationName locationName;
    private Location location;
    private String _gml_id;


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
     * Gets multi extent of.
     *
     * @return the multi extent of
     */
    public MultiExtentOf getMultiExtentOf() {
        return multiExtentOf;
    }

    /**
     * Sets multi extent of.
     *
     * @param multiExtentOf the multi extent of
     */
    public void setMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
    }


    /**
     * Gets maximum altitude.
     *
     * @return the maximum altitude
     */
    public MaximumAltitude getMaximumAltitude() {
        return maximumAltitude;
    }

    /**
     * Sets maximum altitude.
     *
     * @param maximumAltitude the maximum altitude
     */
    public void setMaximumAltitude(MaximumAltitude maximumAltitude) {
        this.maximumAltitude = maximumAltitude;
    }


    /**
     * Gets minimum altitude.
     *
     * @return the minimum altitude
     */
    public MinimumAltitude getMinimumAltitude() {
        return minimumAltitude;
    }

    /**
     * Sets minimum altitude.
     *
     * @param minimumAltitude the minimum altitude
     */
    public void setMinimumAltitude(MinimumAltitude minimumAltitude) {
        this.minimumAltitude = minimumAltitude;
    }


    /**
     * Gets nominal track.
     *
     * @return the nominal track
     */
    public NominalTrack getNominalTrack() {
        return nominalTrack;
    }

    /**
     * Sets nominal track.
     *
     * @param nominalTrack the nominal track
     */
    public void setNominalTrack(NominalTrack nominalTrack) {
        this.nominalTrack = nominalTrack;
    }

    /**
     * Gets occultation points.
     *
     * @return the occultation points
     */
    public OccultationPoints getOccultationPoints() {
        return occultationPoints;
    }

    /**
     * Sets occultation points.
     *
     * @param occultationPoints the occultation points
     */
    public void setOccultationPoints(OccultationPoints occultationPoints) {
        this.occultationPoints = occultationPoints;
    }

    /**
     * Gets center of.
     *
     * @return the center of
     */
    public CenterOf getCenterOf() {
        return centerOf;
    }

    /**
     * Sets center of.
     *
     * @param centerOf the center of
     */
    public void setCenterOf(CenterOf centerOf) {
        this.centerOf = centerOf;
    }

    /**
     * Gets location name.
     *
     * @return the location name
     */
    public LocationName getLocationName() {
        return locationName;
    }

    /**
     * Sets location name.
     *
     * @param locationName the location name
     */
    public void setLocationName(LocationName locationName) {
        this.locationName = locationName;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets gml id.
     *
     * @return the gml id
     */
    public String get_gml_id() {
        return _gml_id;
    }

    /**
     * Sets gml id.
     *
     * @param _gml_id the gml id
     */
    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
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
