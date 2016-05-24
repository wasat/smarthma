package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * Created by Daniel on 2016-05-20.
 * This file is a part of module SmartHMA NavIn project.
 */
public class MultiGeometry implements Serializable {

    private static final long serialVersionUID = 1L;

    private GeometryMembers geometryMembers;
    private String GmlId;
    private String Prefix;

    /**
     * @return The geometryMembers
     */
    public GeometryMembers getGeometryMembers() {
        return geometryMembers;
    }

    /**
     * @param geometryMembers The geometryMembers
     */
    public void setGeometryMembers(GeometryMembers geometryMembers) {
        this.geometryMembers = geometryMembers;
    }

    /**
     * @return The GmlId
     */
    public String getGmlId() {
        return GmlId;
    }

    /**
     * @param GmlId The _gml:id
     */
    public void setGmlId(String GmlId) {
        this.GmlId = GmlId;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The __prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
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
