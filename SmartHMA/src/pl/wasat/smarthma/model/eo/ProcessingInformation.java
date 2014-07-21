
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


public class ProcessingInformation {

    private String __prefix;
    private Method method;
    private ProcessorName processorName;
    private ProcessorVersion processorVersion;
    private ProcessingCenter processingCenter;
    private ProcessingDate processingDate;
    private ProcessingMode processingMode;
    private GroundTrackUncertainty groundTrackUncertainty;
    private List<SamplingRate> samplingRate = new ArrayList<SamplingRate>();   
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public ProcessingInformation with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ProcessingInformation withMethod(Method method) {
        this.method = method;
        return this;
    }

    public ProcessorName getProcessorName() {
        return processorName;
    }

    public void setProcessorName(ProcessorName processorName) {
        this.processorName = processorName;
    }

    public ProcessingInformation withProcessorName(ProcessorName processorName) {
        this.processorName = processorName;
        return this;
    }

    public ProcessorVersion getProcessorVersion() {
        return processorVersion;
    }

    public void setProcessorVersion(ProcessorVersion processorVersion) {
        this.processorVersion = processorVersion;
    }

    public ProcessingInformation withProcessorVersion(ProcessorVersion processorVersion) {
        this.processorVersion = processorVersion;
        return this;
    }

    public ProcessingCenter getProcessingCenter() {
        return processingCenter;
    }

    public void setProcessingCenter(ProcessingCenter processingCenter) {
        this.processingCenter = processingCenter;
    }

    public ProcessingInformation withProcessingCenter(ProcessingCenter processingCenter) {
        this.processingCenter = processingCenter;
        return this;
    }

    public ProcessingDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(ProcessingDate processingDate) {
        this.processingDate = processingDate;
    }

    public ProcessingInformation withProcessingDate(ProcessingDate processingDate) {
        this.processingDate = processingDate;
        return this;
    }

    public ProcessingMode getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(ProcessingMode processingMode) {
        this.processingMode = processingMode;
    }

    public ProcessingInformation withProcessingMode(ProcessingMode processingMode) {
        this.processingMode = processingMode;
        return this;
    }

    public GroundTrackUncertainty getGroundTrackUncertainty() {
        return groundTrackUncertainty;
    }

    public void setGroundTrackUncertainty(GroundTrackUncertainty groundTrackUncertainty) {
        this.groundTrackUncertainty = groundTrackUncertainty;
    }

    public ProcessingInformation withGroundTrackUncertainty(GroundTrackUncertainty groundTrackUncertainty) {
        this.groundTrackUncertainty = groundTrackUncertainty;
        return this;
    }

    public List<SamplingRate> getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(List<SamplingRate> samplingRate) {
        this.samplingRate = samplingRate;
    }

    public ProcessingInformation withSamplingRate(List<SamplingRate> samplingRate) {
        this.samplingRate = samplingRate;
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
