
package pl.wasat.smarthma.model.eo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class EarthObservationEquipment {

    private String __prefix;
    private List<Sensor> sensor = new ArrayList<Sensor>();
    private List<Instrument> instrument = new ArrayList<Instrument>();
    private List<Platform> platform = new ArrayList<Platform>();
    private AcquisitionParameters acquisitionParameters;
    private AuxiliaryInstrument auxiliaryInstrument;
    private String _gml_id;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public EarthObservationEquipment with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public List<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(List<Sensor> sensor) {
        this.sensor = sensor;
    }

    public EarthObservationEquipment withSensor(List<Sensor> sensor) {
        this.sensor = sensor;
        return this;
    }

    public List<Instrument> getInstrument() {
        return instrument;
    }

    public void setInstrument(List<Instrument> instrument) {
        this.instrument = instrument;
    }

    public EarthObservationEquipment withInstrument(List<Instrument> instrument) {
        this.instrument = instrument;
        return this;
    }

    public List<Platform> getPlatform() {
        return platform;
    }

    public void setPlatform(List<Platform> platform) {
        this.platform = platform;
    }

    public EarthObservationEquipment withPlatform(List<Platform> platform) {
        this.platform = platform;
        return this;
    }

    public AcquisitionParameters getAcquisitionParameters() {
        return acquisitionParameters;
    }

    public void setAcquisitionParameters(AcquisitionParameters acquisitionParameters) {
        this.acquisitionParameters = acquisitionParameters;
    }

    public EarthObservationEquipment withAcquisitionParameters(AcquisitionParameters acquisitionParameters) {
        this.acquisitionParameters = acquisitionParameters;
        return this;
    }

    public AuxiliaryInstrument getAuxiliaryInstrument() {
        return auxiliaryInstrument;
    }

    public void setAuxiliaryInstrument(AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
    }

    public EarthObservationEquipment withAuxiliaryInstrument(AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
        return this;
    }


    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public EarthObservationEquipment with_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
        return this;
    }

    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
