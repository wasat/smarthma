
package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Acquisition {

    private String __prefix;
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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public Acquisition with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public OrbitNumber getOrbitNumber() {
        return orbitNumber;
    }

    public void setOrbitNumber(OrbitNumber orbitNumber) {
        this.orbitNumber = orbitNumber;
    }

    public Acquisition withOrbitNumber(OrbitNumber orbitNumber) {
        this.orbitNumber = orbitNumber;
        return this;
    }

    public LastOrbitNumber getLastOrbitNumber() {
        return lastOrbitNumber;
    }

    public void setLastOrbitNumber(LastOrbitNumber lastOrbitNumber) {
        this.lastOrbitNumber = lastOrbitNumber;
    }

    public Acquisition withLastOrbitNumber(LastOrbitNumber lastOrbitNumber) {
        this.lastOrbitNumber = lastOrbitNumber;
        return this;
    }

    public OrbitDirection getOrbitDirection() {
        return orbitDirection;
    }

    public void setOrbitDirection(OrbitDirection orbitDirection) {
        this.orbitDirection = orbitDirection;
    }

    public Acquisition withOrbitDirection(OrbitDirection orbitDirection) {
        this.orbitDirection = orbitDirection;
        return this;
    }

    public IlluminationAzimuthAngle getIlluminationAzimuthAngle() {
        return illuminationAzimuthAngle;
    }

    public void setIlluminationAzimuthAngle(IlluminationAzimuthAngle illuminationAzimuthAngle) {
        this.illuminationAzimuthAngle = illuminationAzimuthAngle;
    }

    public Acquisition withIlluminationAzimuthAngle(IlluminationAzimuthAngle illuminationAzimuthAngle) {
        this.illuminationAzimuthAngle = illuminationAzimuthAngle;
        return this;
    }

    public AcrossTrackIncidenceAngle getAcrossTrackIncidenceAngle() {
        return acrossTrackIncidenceAngle;
    }

    public void setAcrossTrackIncidenceAngle(AcrossTrackIncidenceAngle acrossTrackIncidenceAngle) {
        this.acrossTrackIncidenceAngle = acrossTrackIncidenceAngle;
    }

    public Acquisition withAcrossTrackIncidenceAngle(AcrossTrackIncidenceAngle acrossTrackIncidenceAngle) {
        this.acrossTrackIncidenceAngle = acrossTrackIncidenceAngle;
        return this;
    }

    public AlongTrackIncidenceAngle getAlongTrackIncidenceAngle() {
        return alongTrackIncidenceAngle;
    }

    public void setAlongTrackIncidenceAngle(AlongTrackIncidenceAngle alongTrackIncidenceAngle) {
        this.alongTrackIncidenceAngle = alongTrackIncidenceAngle;
    }

    public Acquisition withAlongTrackIncidenceAngle(AlongTrackIncidenceAngle alongTrackIncidenceAngle) {
        this.alongTrackIncidenceAngle = alongTrackIncidenceAngle;
        return this;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Acquisition withPitch(Pitch pitch) {
        this.pitch = pitch;
        return this;
    }

    public Roll getRoll() {
        return roll;
    }

    public void setRoll(Roll roll) {
        this.roll = roll;
    }

    public Acquisition withRoll(Roll roll) {
        this.roll = roll;
        return this;
    }

    public Yaw getYaw() {
        return yaw;
    }

    public void setYaw(Yaw yaw) {
        this.yaw = yaw;
    }

    public Acquisition withYaw(Yaw yaw) {
        this.yaw = yaw;
        return this;
    }

    public CycleNumber getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(CycleNumber cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public Acquisition withCycleNumber(CycleNumber cycleNumber) {
        this.cycleNumber = cycleNumber;
        return this;
    }

    public IsSegment getIsSegment() {
        return isSegment;
    }

    public void setIsSegment(IsSegment isSegment) {
        this.isSegment = isSegment;
    }

    public Acquisition withIsSegment(IsSegment isSegment) {
        this.isSegment = isSegment;
        return this;
    }

    public RelativePassNumber getRelativePassNumber() {
        return relativePassNumber;
    }

    public void setRelativePassNumber(RelativePassNumber relativePassNumber) {
        this.relativePassNumber = relativePassNumber;
    }

    public Acquisition withRelativePassNumber(RelativePassNumber relativePassNumber) {
        this.relativePassNumber = relativePassNumber;
        return this;
    }

    public PolarisationMode getPolarisationMode() {
        return polarisationMode;
    }

    public void setPolarisationMode(PolarisationMode polarisationMode) {
        this.polarisationMode = polarisationMode;
    }

    public Acquisition withPolarisationMode(PolarisationMode polarisationMode) {
        this.polarisationMode = polarisationMode;
        return this;
    }

    public PolarisationChannels getPolarisationChannels() {
        return polarisationChannels;
    }

    public void setPolarisationChannels(PolarisationChannels polarisationChannels) {
        this.polarisationChannels = polarisationChannels;
    }

    public Acquisition withPolarisationChannels(PolarisationChannels polarisationChannels) {
        this.polarisationChannels = polarisationChannels;
        return this;
    }
    
    public WrsLongitudeGrid getWrsLongitudeGrid() {
        return wrsLongitudeGrid;
    }

    public void setWrsLongitudeGrid(WrsLongitudeGrid wrsLongitudeGrid) {
        this.wrsLongitudeGrid = wrsLongitudeGrid;
    }

    public Acquisition withWrsLongitudeGrid(WrsLongitudeGrid wrsLongitudeGrid) {
        this.wrsLongitudeGrid = wrsLongitudeGrid;
        return this;
    }

    public WrsLatitudeGrid getWrsLatitudeGrid() {
        return wrsLatitudeGrid;
    }

    public void setWrsLatitudeGrid(WrsLatitudeGrid wrsLatitudeGrid) {
        this.wrsLatitudeGrid = wrsLatitudeGrid;
    }

    public Acquisition withWrsLatitudeGrid(WrsLatitudeGrid wrsLatitudeGrid) {
        this.wrsLatitudeGrid = wrsLatitudeGrid;
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
