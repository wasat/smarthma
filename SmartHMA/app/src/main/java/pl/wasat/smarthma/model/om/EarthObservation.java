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
 * The type Earth observation.
 */
public class EarthObservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private PhenomenonTime phenomenonTime;
    private ResultTime resultTime;
    private Procedure procedure;
    private ObservedProperty observedProperty;
    private FeatureOfInterest featureOfInterest;
    private Result result;
    private MetaDataProperty metaDataProperty;
    private String _xmlns_xsi;
    private String _xmlns_xlink;
    private String _xmlns_gml;
    private String _xmlns_eop;
    private String _xmlns_opt;
    private String _xmlns_atm;
    private String _xmlns_ows;
    private String _xmlns_swe;
    private String _xmlns_om;
    private String _gml_id;
    private String _xsi_schemaLocation;


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
     * Gets phenomenon time.
     *
     * @return the phenomenon time
     */
    public PhenomenonTime getPhenomenonTime() {
        return phenomenonTime;
    }

    /**
     * Sets phenomenon time.
     *
     * @param phenomenonTime the phenomenon time
     */
    public void setPhenomenonTime(PhenomenonTime phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    /**
     * Gets result time.
     *
     * @return the result time
     */
    public ResultTime getResultTime() {
        return resultTime;
    }

    /**
     * Sets result time.
     *
     * @param resultTime the result time
     */
    public void setResultTime(ResultTime resultTime) {
        this.resultTime = resultTime;
    }

    /**
     * Gets procedure.
     *
     * @return the procedure
     */
    public Procedure getProcedure() {
        return procedure;
    }

    /**
     * Sets procedure.
     *
     * @param procedure the procedure
     */
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }


    /**
     * Gets observed property.
     *
     * @return the observed property
     */
    public ObservedProperty getObservedProperty() {
        return observedProperty;
    }

    /**
     * Sets observed property.
     *
     * @param observedProperty the observed property
     */
    public void setObservedProperty(ObservedProperty observedProperty) {
        this.observedProperty = observedProperty;
    }

    /**
     * Gets feature of interest.
     *
     * @return the feature of interest
     */
    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    /**
     * Sets feature of interest.
     *
     * @param featureOfInterest the feature of interest
     */
    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(Result result) {
        this.result = result;
    }


    /**
     * Gets meta data property.
     *
     * @return the meta data property
     */
    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    /**
     * Sets meta data property.
     *
     * @param metaDataProperty the meta data property
     */
    public void setMetaDataProperty(MetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }


    /**
     * Gets xmlns xsi.
     *
     * @return the xmlns xsi
     */
    public String get_xmlns_xsi() {
        return _xmlns_xsi;
    }

    /**
     * Sets xmlns xsi.
     *
     * @param _xmlns_xsi the xmlns xsi
     */
    public void set_xmlns_xsi(String _xmlns_xsi) {
        this._xmlns_xsi = _xmlns_xsi;
    }


    /**
     * Gets xmlns xlink.
     *
     * @return the xmlns xlink
     */
    public String get_xmlns_xlink() {
        return _xmlns_xlink;
    }

    /**
     * Sets xmlns xlink.
     *
     * @param _xmlns_xlink the xmlns xlink
     */
    public void set_xmlns_xlink(String _xmlns_xlink) {
        this._xmlns_xlink = _xmlns_xlink;
    }


    /**
     * Gets xmlns gml.
     *
     * @return the xmlns gml
     */
    public String get_xmlns_gml() {
        return _xmlns_gml;
    }

    /**
     * Sets xmlns gml.
     *
     * @param _xmlns_gml the xmlns gml
     */
    public void set_xmlns_gml(String _xmlns_gml) {
        this._xmlns_gml = _xmlns_gml;
    }


    /**
     * Gets xmlns eop.
     *
     * @return the xmlns eop
     */
    public String get_xmlns_eop() {
        return _xmlns_eop;
    }

    /**
     * Sets xmlns eop.
     *
     * @param _xmlns_eop the xmlns eop
     */
    public void set_xmlns_eop(String _xmlns_eop) {
        this._xmlns_eop = _xmlns_eop;
    }


    /**
     * Gets xmlns opt.
     *
     * @return the xmlns opt
     */
    public String get_xmlns_opt() {
        return _xmlns_opt;
    }

    /**
     * Sets xmlns opt.
     *
     * @param _xmlns_opt the xmlns opt
     */
    public void set_xmlns_opt(String _xmlns_opt) {
        this._xmlns_opt = _xmlns_opt;
    }


    /**
     * Gets xmlns atm.
     *
     * @return the xmlns atm
     */
    public String get_xmlns_atm() {
        return _xmlns_atm;
    }

    /**
     * Sets xmlns atm.
     *
     * @param _xmlns_atm the xmlns atm
     */
    public void set_xmlns_atm(String _xmlns_atm) {
        this._xmlns_atm = _xmlns_atm;
    }

    /**
     * Gets xmlns ows.
     *
     * @return the xmlns ows
     */
    public String get_xmlns_ows() {
        return _xmlns_ows;
    }

    /**
     * Sets xmlns ows.
     *
     * @param _xmlns_ows the xmlns ows
     */
    public void set_xmlns_ows(String _xmlns_ows) {
        this._xmlns_ows = _xmlns_ows;
    }

    /**
     * Gets xmlns swe.
     *
     * @return the xmlns swe
     */
    public String get_xmlns_swe() {
        return _xmlns_swe;
    }

    /**
     * Sets xmlns swe.
     *
     * @param _xmlns_swe the xmlns swe
     */
    public void set_xmlns_swe(String _xmlns_swe) {
        this._xmlns_swe = _xmlns_swe;
    }

    /**
     * Gets xmlns om.
     *
     * @return the xmlns om
     */
    public String get_xmlns_om() {
        return _xmlns_om;
    }

    /**
     * Sets xmlns om.
     *
     * @param _xmlns_om the xmlns om
     */
    public void set_xmlns_om(String _xmlns_om) {
        this._xmlns_om = _xmlns_om;
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

    /**
     * Gets xsi schema location.
     *
     * @return the xsi schema location
     */
    public String get_xsi_schemaLocation() {
        return _xsi_schemaLocation;
    }

    /**
     * Sets xsi schema location.
     *
     * @param _xsi_schemaLocation the xsi schema location
     */
    public void set_xsi_schemaLocation(String _xsi_schemaLocation) {
        this._xsi_schemaLocation = _xsi_schemaLocation;
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

