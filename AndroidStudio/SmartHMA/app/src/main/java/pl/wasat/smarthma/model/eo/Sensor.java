package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Sensor implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private SensorType sensorType;
	private OperationalMode operationalMode;
	private Resolution resolution;
	private MeasurementType measurementType;
	private SwathIdentifier swathIdentifier;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public Sensor with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	public Sensor withSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
		return this;
	}

	public OperationalMode getOperationalMode() {
		return operationalMode;
	}

	public void setOperationalMode(OperationalMode operationalMode) {
		this.operationalMode = operationalMode;
	}

	public Sensor withOperationalMode(OperationalMode operationalMode) {
		this.operationalMode = operationalMode;
		return this;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public Sensor withResolution(Resolution resolution) {
		this.resolution = resolution;
		return this;
	}

	public MeasurementType getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
	}

	public Sensor withMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
		return this;
	}

	public SwathIdentifier getSwathIdentifier() {
		return swathIdentifier;
	}

	public void setSwathIdentifier(SwathIdentifier swathIdentifier) {
		this.swathIdentifier = swathIdentifier;
	}

	public Sensor withSwathIdentifier(SwathIdentifier swathIdentifier) {
		this.swathIdentifier = swathIdentifier;
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

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
