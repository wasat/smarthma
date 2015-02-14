package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Footprint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private MultiExtentOf multiExtentOf;
    private MaximumAltitude maximumAltitude;
    private MinimumAltitude minimumAltitude;
    private NominalTrack nominalTrack;
    private OccultationPoints occultationPoints;
    private CenterOf centerOf;
    private LocationName locationName;
    private String _gml_id;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public Footprint with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public MultiExtentOf getMultiExtentOf() {
        return multiExtentOf;
    }

    public void setMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
    }

    public Footprint withMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
        return this;
    }

    public MaximumAltitude getMaximumAltitude() {
        return maximumAltitude;
    }

    public void setMaximumAltitude(MaximumAltitude maximumAltitude) {
        this.maximumAltitude = maximumAltitude;
    }

    public Footprint withMaximumAltitude(MaximumAltitude maximumAltitude) {
        this.maximumAltitude = maximumAltitude;
        return this;
    }

    public MinimumAltitude getMinimumAltitude() {
        return minimumAltitude;
    }

    public void setMinimumAltitude(MinimumAltitude minimumAltitude) {
        this.minimumAltitude = minimumAltitude;
    }

    public Footprint withMinimumAltitude(MinimumAltitude minimumAltitude) {
        this.minimumAltitude = minimumAltitude;
        return this;
    }

    public NominalTrack getNominalTrack() {
        return nominalTrack;
    }

    public void setNominalTrack(NominalTrack nominalTrack) {
        this.nominalTrack = nominalTrack;
    }

    public Footprint withNominalTrack(NominalTrack nominalTrack) {
        this.nominalTrack = nominalTrack;
        return this;
    }

    public OccultationPoints getOccultationPoints() {
        return occultationPoints;
    }

    public void setOccultationPoints(OccultationPoints occultationPoints) {
        this.occultationPoints = occultationPoints;
    }

    public Footprint withOccultationPoints(OccultationPoints occultationPoints) {
        this.occultationPoints = occultationPoints;
        return this;
    }

    public CenterOf getCenterOf() {
        return centerOf;
    }

    public void setCenterOf(CenterOf centerOf) {
        this.centerOf = centerOf;
    }

    public Footprint withCenterOf(CenterOf centerOf) {
        this.centerOf = centerOf;
        return this;
    }

    public LocationName getLocationName() {
        return locationName;
    }

    public void setLocationName(LocationName locationName) {
        this.locationName = locationName;
    }

    public Footprint withLocationName(LocationName locationName) {
        this.locationName = locationName;
        return this;
    }

    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public Footprint with_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
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
