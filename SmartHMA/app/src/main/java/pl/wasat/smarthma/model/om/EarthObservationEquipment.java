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
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Earth observation equipment.
 */
public class EarthObservationEquipment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private List<Sensor> sensor = new ArrayList<>();
    private List<Instrument> instrument = new ArrayList<>();
    private List<Platform> platform = new ArrayList<>();
    private AcquisitionParameters acquisitionParameters;
    private AuxiliaryInstrument auxiliaryInstrument;
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
     * Gets sensor.
     *
     * @return the sensor
     */
    public List<Sensor> getSensor() {
        return sensor;
    }

    /**
     * Sets sensor.
     *
     * @param sensor the sensor
     */
    public void setSensor(List<Sensor> sensor) {
        this.sensor = sensor;
    }

    /**
     * Gets instrument.
     *
     * @return the instrument
     */
    public List<Instrument> getInstrument() {
        return instrument;
    }

    /**
     * Sets instrument.
     *
     * @param instrument the instrument
     */
    public void setInstrument(List<Instrument> instrument) {
        this.instrument = instrument;
    }

    /**
     * Gets platform.
     *
     * @return the platform
     */
    public List<Platform> getPlatform() {
        return platform;
    }

    /**
     * Sets platform.
     *
     * @param platform the platform
     */
    public void setPlatform(List<Platform> platform) {
        this.platform = platform;
    }

    /**
     * Gets acquisition parameters.
     *
     * @return the acquisition parameters
     */
    public AcquisitionParameters getAcquisitionParameters() {
        return acquisitionParameters;
    }

    /**
     * Sets acquisition parameters.
     *
     * @param acquisitionParameters the acquisition parameters
     */
    public void setAcquisitionParameters(
            AcquisitionParameters acquisitionParameters) {
        this.acquisitionParameters = acquisitionParameters;
    }


    /**
     * Gets auxiliary instrument.
     *
     * @return the auxiliary instrument
     */
    public AuxiliaryInstrument getAuxiliaryInstrument() {
        return auxiliaryInstrument;
    }

    /**
     * Sets auxiliary instrument.
     *
     * @param auxiliaryInstrument the auxiliary instrument
     */
    public void setAuxiliaryInstrument(AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
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
