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
 * The type Processing information.
 */
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
     * Gets method.
     *
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Gets processor name.
     *
     * @return the processor name
     */
    public ProcessorName getProcessorName() {
        return processorName;
    }

    /**
     * Sets processor name.
     *
     * @param processorName the processor name
     */
    public void setProcessorName(ProcessorName processorName) {
        this.processorName = processorName;
    }

    /**
     * Gets processor version.
     *
     * @return the processor version
     */
    public ProcessorVersion getProcessorVersion() {
        return processorVersion;
    }

    /**
     * Sets processor version.
     *
     * @param processorVersion the processor version
     */
    public void setProcessorVersion(ProcessorVersion processorVersion) {
        this.processorVersion = processorVersion;
    }


    /**
     * Gets processing center.
     *
     * @return the processing center
     */
    public ProcessingCenter getProcessingCenter() {
        return processingCenter;
    }

    /**
     * Sets processing center.
     *
     * @param processingCenter the processing center
     */
    public void setProcessingCenter(ProcessingCenter processingCenter) {
        this.processingCenter = processingCenter;
    }

    /**
     * Gets processing date.
     *
     * @return the processing date
     */
    public ProcessingDate getProcessingDate() {
        return processingDate;
    }

    /**
     * Sets processing date.
     *
     * @param processingDate the processing date
     */
    public void setProcessingDate(ProcessingDate processingDate) {
        this.processingDate = processingDate;
    }

    /**
     * Gets processing mode.
     *
     * @return the processing mode
     */
    public ProcessingMode getProcessingMode() {
        return processingMode;
    }

    /**
     * Sets processing mode.
     *
     * @param processingMode the processing mode
     */
    public void setProcessingMode(ProcessingMode processingMode) {
        this.processingMode = processingMode;
    }

    /**
     * Gets ground track uncertainty.
     *
     * @return the ground track uncertainty
     */
    public GroundTrackUncertainty getGroundTrackUncertainty() {
        return groundTrackUncertainty;
    }

    /**
     * Sets ground track uncertainty.
     *
     * @param groundTrackUncertainty the ground track uncertainty
     */
    public void setGroundTrackUncertainty(
            GroundTrackUncertainty groundTrackUncertainty) {
        this.groundTrackUncertainty = groundTrackUncertainty;
    }

    /**
     * Gets sampling rate.
     *
     * @return the sampling rate
     */
    public List<SamplingRate> getSamplingRate() {
        return samplingRate;
    }

    /**
     * Sets sampling rate.
     *
     * @param samplingRate the sampling rate
     */
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
