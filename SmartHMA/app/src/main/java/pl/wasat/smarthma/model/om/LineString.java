package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class LineString implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private Coordinates coordinates;
    private String _gml_id;
    private String srsName;
    private PosString posString;
    private List<Pos> posList = new ArrayList<>();


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String get_gml_id() {
        return _gml_id;
    }

    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    public String getSrsName() {
        return srsName;
    }

    public void setSrsName(String srsName) {
        this.srsName = srsName;
    }

    public PosString getPosString() {
        return posString;
    }

    public void setPosString(PosString posString) {
        if (posString == null) {
            posString = new PosString();
            posString.setPointsString("");
        }
        this.posString = posString;

        if (!posString.getPointsString().isEmpty()
                && posString.getPointsString().length() < 200) {
            this.posList = setPosList(posString.getPointsString());
        }
    }

    public List<Pos> getPosList() {
        return posList;
    }

    private void setPosList(List<Pos> posList) {
        if (!posList.isEmpty()) {
            this.posList = posList;
        }
    }

    public List<Pos> setPosList(String pointsString) {
        String[] coorStr = pointsString.replaceAll("  ", " ").split(" ");
        List<Pos> latLngPosList = new ArrayList<>();

        for (int j = 0; j < coorStr.length - 1; j = j + 2) {
            LatLngExt ftPt = new LatLngExt(Double.valueOf(coorStr[j]),
                    Double.valueOf(coorStr[j + 1]));
            Pos pos = new Pos();
            pos.setLatLng(ftPt);
            latLngPosList.add(pos);
        }
        this.setPosList(latLngPosList);
        return latLngPosList;
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
