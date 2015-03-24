package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

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


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public MultiExtentOf getMultiExtentOf() {
        return multiExtentOf;
    }

    public void setMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
    }


    public MaximumAltitude getMaximumAltitude() {
        return maximumAltitude;
    }

    public void setMaximumAltitude(MaximumAltitude maximumAltitude) {
        this.maximumAltitude = maximumAltitude;
    }


    public MinimumAltitude getMinimumAltitude() {
        return minimumAltitude;
    }

    public void setMinimumAltitude(MinimumAltitude minimumAltitude) {
        this.minimumAltitude = minimumAltitude;
    }


    public NominalTrack getNominalTrack() {
        return nominalTrack;
    }

    public void setNominalTrack(NominalTrack nominalTrack) {
        this.nominalTrack = nominalTrack;
    }


    public OccultationPoints getOccultationPoints() {
        return occultationPoints;
    }

    public void setOccultationPoints(OccultationPoints occultationPoints) {
        this.occultationPoints = occultationPoints;
    }


    public CenterOf getCenterOf() {
        return centerOf;
    }

    public void setCenterOf(CenterOf centerOf) {
        this.centerOf = centerOf;
    }


    public LocationName getLocationName() {
        return locationName;
    }

    public void setLocationName(LocationName locationName) {
        this.locationName = locationName;
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
