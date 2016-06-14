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
 * The type Sensor.
 */
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private SensorType sensorType;
    private OperationalMode operationalMode;
    private Resolution resolution;
    private MeasurementType measurementType;
    private SwathIdentifier swathIdentifier;


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
     * Gets sensor type.
     *
     * @return the sensor type
     */
    public SensorType getSensorType() {
        return sensorType;
    }

    /**
     * Sets sensor type.
     *
     * @param sensorType the sensor type
     */
    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }


    /**
     * Gets operational mode.
     *
     * @return the operational mode
     */
    public OperationalMode getOperationalMode() {
        return operationalMode;
    }

    /**
     * Sets operational mode.
     *
     * @param operationalMode the operational mode
     */
    public void setOperationalMode(OperationalMode operationalMode) {
        this.operationalMode = operationalMode;
    }


    /**
     * Gets resolution.
     *
     * @return the resolution
     */
    public Resolution getResolution() {
        return resolution;
    }

    /**
     * Sets resolution.
     *
     * @param resolution the resolution
     */
    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }


    /**
     * Gets measurement type.
     *
     * @return the measurement type
     */
    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    /**
     * Sets measurement type.
     *
     * @param measurementType the measurement type
     */
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }


    /**
     * Gets swath identifier.
     *
     * @return the swath identifier
     */
    public SwathIdentifier getSwathIdentifier() {
        return swathIdentifier;
    }

    /**
     * Sets swath identifier.
     *
     * @param swathIdentifier the swath identifier
     */
    public void setSwathIdentifier(SwathIdentifier swathIdentifier) {
        this.swathIdentifier = swathIdentifier;
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
