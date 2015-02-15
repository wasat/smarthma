package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class AuxiliaryInstrument implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private ShortName shortName;
    private InstrumentType instrumentType;
    private AuxiliaryInstrument auxiliaryInstrument;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public AuxiliaryInstrument with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public ShortName getShortName() {
        return shortName;
    }

    public void setShortName(ShortName shortName) {
        this.shortName = shortName;
    }

    public AuxiliaryInstrument withShortName(ShortName shortName) {
        this.shortName = shortName;
        return this;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public AuxiliaryInstrument withInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
        return this;
    }

    public AuxiliaryInstrument getAuxiliaryInstrument() {
        return auxiliaryInstrument;
    }

    public void setAuxiliaryInstrument(AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
    }

    public AuxiliaryInstrument withAuxiliaryInstrument(
            AuxiliaryInstrument auxiliaryInstrument) {
        this.auxiliaryInstrument = auxiliaryInstrument;
        return this;
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

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }


}
