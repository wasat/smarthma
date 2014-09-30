package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.android.gms.maps.model.LatLng;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class LinearRing implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private PosString posString;
	private List<Pos> posList;
	private List<LatLng> posLatLngList = new ArrayList<LatLng>();
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public LinearRing with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public PosString getPosString() {
		return posString;
	}

	public void setPosString(PosString posString) {
		this.posString = posString;
	}

	public LinearRing withPosString(PosString posString) {
		this.posString = posString;
		return this;
	}

	public List<Pos> getPosList() {
		return posList;
	}

	public void setPosList(List<Pos> posList) {
		this.posList = posList;
		toPosLatLngList();
	}

	public LinearRing withPosList(List<Pos> posList) {
		this.posList = posList;
		return this;
	}

	public List<LatLng> getPosLatLngList() {
		return posLatLngList;
	}

	public void setPosLatLngList(List<LatLng> posLatLngList) {
		this.posLatLngList = posLatLngList;
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
	
	private void toPosLatLngList(){
		for (int j = 0; j < posList.size(); j++) {
			String posStr = posList.get(j).get__text();
			LatLng ftPt = new LatLng(Double.valueOf(posStr.split(" ")[0]),
					Double.valueOf(posStr.split(" ")[1]));
			posLatLngList.add(ftPt);
		}
		
	}

}
