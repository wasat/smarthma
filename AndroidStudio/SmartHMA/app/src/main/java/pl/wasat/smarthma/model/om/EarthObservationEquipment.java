package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EarthObservationEquipment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private List<Sensor> sensor = new ArrayList<>();
    private List<Instrument> instrument = new ArrayList<>();
    private List<Platform> platform = new ArrayList<>();
    private AcquisitionParameters acquisitionParameters;
    private AuxiliaryInstrument auxiliaryInstrument;
    private String _gml_id;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public List<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(List<Sensor> sensor) {
        this.sensor = sensor;
    }

    public List<Instrument> getInstrument() {
        return instrument;
    }

    public void setInstrument(List<Instrument> instrument) {
        this.instrument = instrument;
    }

    public List<Platform> getPlatform() {
        return platform;
    }

    public void setPlatform(List<Platform> platform) {
        this.platform = platform;
    }

    public AcquisitionParameters getAcquisitionParameters() {
        return acquisitionParameters;
    }

    public void setAcquisitionParameters(
            AcquisitionParameters acquisitionParameters) {
        this.acquisitionParameters = acquisitionParameters;
    }


    public AuxiliaryInstrument getAuxiliaryInstrument() {
        return auxiliaryInstrument;
    }

    public void setAuxiliaryInstrument(AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
    }

    public String get_gml_id() {
        return _gml_id;
    }

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
