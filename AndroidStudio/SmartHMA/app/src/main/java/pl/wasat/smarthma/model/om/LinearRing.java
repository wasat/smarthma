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

public class LinearRing implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private PosString posString;
    private List<Pos> posList = new ArrayList<>();


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
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

    public void setPosList(List<Pos> posList) {
        if (!posList.isEmpty()) {
            this.posList = posList;
        }
        // toPosLatLngList();
    }

    public List<Pos> setPosList(String pointsString) {
        String[] coorStr = pointsString.split(" ");
        List<Pos> latLngPosList = new ArrayList<>();

        //String tempStr = "";

        for (int j = 0; j < coorStr.length - 1; j = j + 2) {
            LatLngExt ftPt = new LatLngExt(Double.valueOf(coorStr[j]),
                    Double.valueOf(coorStr[j + 1]));
            Pos pos = new Pos();
            pos.setLatLng(ftPt);
            latLngPosList.add(pos);
            //tempStr = tempStr + coorStr[j] + "," + coorStr[j + 1] + ";"
            //  + SystemUtils.LINE_SEPARATOR;
        }
        //Log.i("FOOT", tempStr);


        return latLngPosList;
    }


 /*
     * public List<LatLng> getPosLatLngList() { return posLatLngList; }
  * 
  * public void setPosLatLngList(List<LatLng> posLatLngList) {
  * this.posLatLngList = posLatLngList; }
  */

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


    // private void toPosLatLngList(){
    // for (int j = 0; j < posList.size(); j++) {
    // String posStr = posList.get(j).getText();
    // LatLng ftPt = new LatLng(Double.valueOf(posStr.split(" ")[0]),
    // Double.valueOf(posStr.split(" ")[1]));
    // posLatLngList.add(ftPt);
    // }
    // }

}
