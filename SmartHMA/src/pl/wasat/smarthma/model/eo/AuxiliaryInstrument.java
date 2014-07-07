package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class AuxiliaryInstrument {

	private String __prefix;
	private ShortName shortName;
	private InstrumentType instrumentType;
	private AuxiliaryInstrument auxiliaryInstrument;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public AuxiliaryInstrument with__prefix(String __prefix) {
		this.__prefix = __prefix;
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
