
package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Query {

    private String GeoBox;
    private String Role;
    private String TimeEnd;
    private String TimeStart;


    /**
     * @return The GeoBox
     */
    public String getGeoBox() {
        return GeoBox;
    }

    /**
     * @param GeoBox The _geo:box
     */
    public void setGeoBox(String GeoBox) {
        this.GeoBox = GeoBox;
    }

    /**
     * @return The Role
     */
    public String getRole() {
        return Role;
    }

    /**
     * @param Role The _role
     */
    public void setRole(String Role) {
        this.Role = Role;
    }

    /**
     * @return The TimeEnd
     */
    public String getTimeEnd() {
        return TimeEnd;
    }

    /**
     * @param TimeEnd The _time:end
     */
    public void setTimeEnd(String TimeEnd) {
        this.TimeEnd = TimeEnd;
    }

    /**
     * @return The TimeStart
     */
    public String getTimeStart() {
        return TimeStart;
    }

    /**
     * @param TimeStart The _time:start
     */
    public void setTimeStart(String TimeStart) {
        this.TimeStart = TimeStart;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(GeoBox).append(Role).append(TimeEnd).append(TimeStart).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Query)) {
            return false;
        }
        Query rhs = ((Query) other);
        return new EqualsBuilder().append(GeoBox, rhs.GeoBox).append(Role, rhs.Role).append(TimeEnd, rhs.TimeEnd).append(TimeStart, rhs.TimeStart).isEquals();
    }

}
