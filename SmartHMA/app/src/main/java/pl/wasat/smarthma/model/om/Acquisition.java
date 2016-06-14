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
 * The type Acquisition.
 */
public class Acquisition implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private OrbitNumber orbitNumber;
    private LastOrbitNumber lastOrbitNumber;
    private OrbitDirection orbitDirection;
    private IlluminationAzimuthAngle illuminationAzimuthAngle;
    private AcrossTrackIncidenceAngle acrossTrackIncidenceAngle;
    private AlongTrackIncidenceAngle alongTrackIncidenceAngle;
    private Pitch pitch;
    private Roll roll;
    private Yaw yaw;
    private CycleNumber cycleNumber;
    private IsSegment isSegment;
    private RelativePassNumber relativePassNumber;
    private PolarisationMode polarisationMode;
    private PolarisationChannels polarisationChannels;
    private WrsLongitudeGrid wrsLongitudeGrid;
    private WrsLatitudeGrid wrsLatitudeGrid;


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
     * Gets orbit number.
     *
     * @return the orbit number
     */
    public OrbitNumber getOrbitNumber() {
        return orbitNumber;
    }

    /**
     * Sets orbit number.
     *
     * @param orbitNumber the orbit number
     */
    public void setOrbitNumber(OrbitNumber orbitNumber) {
        this.orbitNumber = orbitNumber;
    }


    /**
     * Gets last orbit number.
     *
     * @return the last orbit number
     */
    public LastOrbitNumber getLastOrbitNumber() {
        return lastOrbitNumber;
    }

    /**
     * Sets last orbit number.
     *
     * @param lastOrbitNumber the last orbit number
     */
    public void setLastOrbitNumber(LastOrbitNumber lastOrbitNumber) {
        this.lastOrbitNumber = lastOrbitNumber;
    }


    /**
     * Gets orbit direction.
     *
     * @return the orbit direction
     */
    public OrbitDirection getOrbitDirection() {
        return orbitDirection;
    }

    /**
     * Sets orbit direction.
     *
     * @param orbitDirection the orbit direction
     */
    public void setOrbitDirection(OrbitDirection orbitDirection) {
        this.orbitDirection = orbitDirection;
    }

    /**
     * Gets illumination azimuth angle.
     *
     * @return the illumination azimuth angle
     */
    public IlluminationAzimuthAngle getIlluminationAzimuthAngle() {
        return illuminationAzimuthAngle;
    }

    /**
     * Sets illumination azimuth angle.
     *
     * @param illuminationAzimuthAngle the illumination azimuth angle
     */
    public void setIlluminationAzimuthAngle(
            IlluminationAzimuthAngle illuminationAzimuthAngle) {
        this.illuminationAzimuthAngle = illuminationAzimuthAngle;
    }


    /**
     * Gets across track incidence angle.
     *
     * @return the across track incidence angle
     */
    public AcrossTrackIncidenceAngle getAcrossTrackIncidenceAngle() {
        return acrossTrackIncidenceAngle;
    }

    /**
     * Sets across track incidence angle.
     *
     * @param acrossTrackIncidenceAngle the across track incidence angle
     */
    public void setAcrossTrackIncidenceAngle(
            AcrossTrackIncidenceAngle acrossTrackIncidenceAngle) {
        this.acrossTrackIncidenceAngle = acrossTrackIncidenceAngle;
    }


    /**
     * Gets along track incidence angle.
     *
     * @return the along track incidence angle
     */
    public AlongTrackIncidenceAngle getAlongTrackIncidenceAngle() {
        return alongTrackIncidenceAngle;
    }

    /**
     * Sets along track incidence angle.
     *
     * @param alongTrackIncidenceAngle the along track incidence angle
     */
    public void setAlongTrackIncidenceAngle(
            AlongTrackIncidenceAngle alongTrackIncidenceAngle) {
        this.alongTrackIncidenceAngle = alongTrackIncidenceAngle;
    }


    /**
     * Gets pitch.
     *
     * @return the pitch
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * Sets pitch.
     *
     * @param pitch the pitch
     */
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets roll.
     *
     * @return the roll
     */
    public Roll getRoll() {
        return roll;
    }

    /**
     * Sets roll.
     *
     * @param roll the roll
     */
    public void setRoll(Roll roll) {
        this.roll = roll;
    }


    /**
     * Gets yaw.
     *
     * @return the yaw
     */
    public Yaw getYaw() {
        return yaw;
    }

    /**
     * Sets yaw.
     *
     * @param yaw the yaw
     */
    public void setYaw(Yaw yaw) {
        this.yaw = yaw;
    }


    /**
     * Gets cycle number.
     *
     * @return the cycle number
     */
    public CycleNumber getCycleNumber() {
        return cycleNumber;
    }

    /**
     * Sets cycle number.
     *
     * @param cycleNumber the cycle number
     */
    public void setCycleNumber(CycleNumber cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    /**
     * Gets is segment.
     *
     * @return the is segment
     */
    public IsSegment getIsSegment() {
        return isSegment;
    }

    /**
     * Sets is segment.
     *
     * @param isSegment the is segment
     */
    public void setIsSegment(IsSegment isSegment) {
        this.isSegment = isSegment;
    }


    /**
     * Gets relative pass number.
     *
     * @return the relative pass number
     */
    public RelativePassNumber getRelativePassNumber() {
        return relativePassNumber;
    }

    /**
     * Sets relative pass number.
     *
     * @param relativePassNumber the relative pass number
     */
    public void setRelativePassNumber(RelativePassNumber relativePassNumber) {
        this.relativePassNumber = relativePassNumber;
    }


    /**
     * Gets polarisation mode.
     *
     * @return the polarisation mode
     */
    public PolarisationMode getPolarisationMode() {
        return polarisationMode;
    }

    /**
     * Sets polarisation mode.
     *
     * @param polarisationMode the polarisation mode
     */
    public void setPolarisationMode(PolarisationMode polarisationMode) {
        this.polarisationMode = polarisationMode;
    }


    /**
     * Gets polarisation channels.
     *
     * @return the polarisation channels
     */
    public PolarisationChannels getPolarisationChannels() {
        return polarisationChannels;
    }

    /**
     * Sets polarisation channels.
     *
     * @param polarisationChannels the polarisation channels
     */
    public void setPolarisationChannels(
            PolarisationChannels polarisationChannels) {
        this.polarisationChannels = polarisationChannels;
    }


    /**
     * Gets wrs longitude grid.
     *
     * @return the wrs longitude grid
     */
    public WrsLongitudeGrid getWrsLongitudeGrid() {
        return wrsLongitudeGrid;
    }

    /**
     * Sets wrs longitude grid.
     *
     * @param wrsLongitudeGrid the wrs longitude grid
     */
    public void setWrsLongitudeGrid(WrsLongitudeGrid wrsLongitudeGrid) {
        this.wrsLongitudeGrid = wrsLongitudeGrid;
    }


    /**
     * Gets wrs latitude grid.
     *
     * @return the wrs latitude grid
     */
    public WrsLatitudeGrid getWrsLatitudeGrid() {
        return wrsLatitudeGrid;
    }

    /**
     * Sets wrs latitude grid.
     *
     * @param wrsLatitudeGrid the wrs latitude grid
     */
    public void setWrsLatitudeGrid(WrsLatitudeGrid wrsLatitudeGrid) {
        this.wrsLatitudeGrid = wrsLatitudeGrid;
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
