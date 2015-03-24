package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Acquisition implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private OrbitNumber orbitNumber;
    private LastOrbitNumber lastOrbitNumber;
    private OrbitDirection orbitDirection;
    private IlluminationAzimuthAngle illuminationAzimuthAngle;
    private AcrossTrackIncidenceAngle acrossTrackIncidenceAngle;
    private AlongTrackIncidenceAngle alongTrackIncidenceAngle;
    private Pitch pitch;
    private Roll roll;
    private Yaw yaw;
    private CycleNumber cycleNumber;
    private IsSegment isSegment;
    private RelativePassNumber relativePassNumber;
    private PolarisationMode polarisationMode;
    private PolarisationChannels polarisationChannels;
    private WrsLongitudeGrid wrsLongitudeGrid;
    private WrsLatitudeGrid wrsLatitudeGrid;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public OrbitNumber getOrbitNumber() {
        return orbitNumber;
    }

    public void setOrbitNumber(OrbitNumber orbitNumber) {
        this.orbitNumber = orbitNumber;
    }


    public LastOrbitNumber getLastOrbitNumber() {
        return lastOrbitNumber;
    }

    public void setLastOrbitNumber(LastOrbitNumber lastOrbitNumber) {
        this.lastOrbitNumber = lastOrbitNumber;
    }


    public OrbitDirection getOrbitDirection() {
        return orbitDirection;
    }

    public void setOrbitDirection(OrbitDirection orbitDirection) {
        this.orbitDirection = orbitDirection;
    }

    public IlluminationAzimuthAngle getIlluminationAzimuthAngle() {
        return illuminationAzimuthAngle;
    }

    public void setIlluminationAzimuthAngle(
            IlluminationAzimuthAngle illuminationAzimuthAngle) {
        this.illuminationAzimuthAngle = illuminationAzimuthAngle;
    }


    public AcrossTrackIncidenceAngle getAcrossTrackIncidenceAngle() {
        return acrossTrackIncidenceAngle;
    }

    public void setAcrossTrackIncidenceAngle(
            AcrossTrackIncidenceAngle acrossTrackIncidenceAngle) {
        this.acrossTrackIncidenceAngle = acrossTrackIncidenceAngle;
    }


    public AlongTrackIncidenceAngle getAlongTrackIncidenceAngle() {
        return alongTrackIncidenceAngle;
    }

    public void setAlongTrackIncidenceAngle(
            AlongTrackIncidenceAngle alongTrackIncidenceAngle) {
        this.alongTrackIncidenceAngle = alongTrackIncidenceAngle;
    }


    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Roll getRoll() {
        return roll;
    }

    public void setRoll(Roll roll) {
        this.roll = roll;
    }


    public Yaw getYaw() {
        return yaw;
    }

    public void setYaw(Yaw yaw) {
        this.yaw = yaw;
    }


    public CycleNumber getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(CycleNumber cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public IsSegment getIsSegment() {
        return isSegment;
    }

    public void setIsSegment(IsSegment isSegment) {
        this.isSegment = isSegment;
    }


    public RelativePassNumber getRelativePassNumber() {
        return relativePassNumber;
    }

    public void setRelativePassNumber(RelativePassNumber relativePassNumber) {
        this.relativePassNumber = relativePassNumber;
    }


    public PolarisationMode getPolarisationMode() {
        return polarisationMode;
    }

    public void setPolarisationMode(PolarisationMode polarisationMode) {
        this.polarisationMode = polarisationMode;
    }


    public PolarisationChannels getPolarisationChannels() {
        return polarisationChannels;
    }

    public void setPolarisationChannels(
            PolarisationChannels polarisationChannels) {
        this.polarisationChannels = polarisationChannels;
    }


    public WrsLongitudeGrid getWrsLongitudeGrid() {
        return wrsLongitudeGrid;
    }

    public void setWrsLongitudeGrid(WrsLongitudeGrid wrsLongitudeGrid) {
        this.wrsLongitudeGrid = wrsLongitudeGrid;
    }


    public WrsLatitudeGrid getWrsLatitudeGrid() {
        return wrsLatitudeGrid;
    }

    public void setWrsLatitudeGrid(WrsLatitudeGrid wrsLatitudeGrid) {
        this.wrsLatitudeGrid = wrsLatitudeGrid;
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
