package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class ProcessingInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Method method;
    private ProcessorName processorName;
    private ProcessorVersion processorVersion;
    private ProcessingCenter processingCenter;
    private ProcessingDate processingDate;
    private ProcessingMode processingMode;
    private GroundTrackUncertainty groundTrackUncertainty;
    private List<SamplingRate> samplingRate = new ArrayList<>();


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ProcessorName getProcessorName() {
        return processorName;
    }

    public void setProcessorName(ProcessorName processorName) {
        this.processorName = processorName;
    }

    public ProcessorVersion getProcessorVersion() {
        return processorVersion;
    }

    public void setProcessorVersion(ProcessorVersion processorVersion) {
        this.processorVersion = processorVersion;
    }


    public ProcessingCenter getProcessingCenter() {
        return processingCenter;
    }

    public void setProcessingCenter(ProcessingCenter processingCenter) {
        this.processingCenter = processingCenter;
    }

    public ProcessingDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(ProcessingDate processingDate) {
        this.processingDate = processingDate;
    }

    public ProcessingMode getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(ProcessingMode processingMode) {
        this.processingMode = processingMode;
    }

    public GroundTrackUncertainty getGroundTrackUncertainty() {
        return groundTrackUncertainty;
    }

    public void setGroundTrackUncertainty(
            GroundTrackUncertainty groundTrackUncertainty) {
        this.groundTrackUncertainty = groundTrackUncertainty;
    }

    public List<SamplingRate> getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(List<SamplingRate> samplingRate) {
        this.samplingRate = samplingRate;
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
