package pl.wasat.smarthma.model;

import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.TileOverlay;

public class Collection implements Parcelable, Comparator<Object> {

	public static Creator<Collection> CREATOR = new Creator<Collection>() {
		@Override
		public Collection createFromParcel(Parcel source) {
			return new Collection(source);
		}

		@Override
		public Collection[] newArray(int size) {
			return new Collection[size];
		}
	};

	private int id;
	private String name;
	private String title;
	private String workspace;
	private String wmsUrl;
	private Boolean checked;
	private String legendUrl;
	private String[] times;
	private TileOverlay mapOverlay;

	public Collection() {
		this.id = -1;
		this.name = null;
		this.title = null;
		this.workspace =  null;
		this.wmsUrl = null;
		this.legendUrl= null;
		this.checked = null;
		this.times = null;
		this.mapOverlay = null;
	}

	public Collection(String layerTitle) {
		this.id = -1;
		this.name = null;
		this.title = layerTitle;
		this.workspace =  null;
		this.wmsUrl = null;
		this.legendUrl= null;
		this.checked = null;
		this.times = null;
		this.mapOverlay = null;
	}

/*	public WMSLayer(WMS_Capabilities.Layer layer) {
		this.id = -1;
		this.layerName = layer.Name;
		this.title = layer.Title;
		this.workspace = layer.Workspace;
		this.wmsUrl = layer.getMapUrl("image/png").toString();
		this.legendUrl = layer.style.LegendURL.toString();
		this.overlied = false;
		this.times = layer.ISO8601Times;
		this.mapOverlay = null;
	}*/
	
	public Collection(Parcel source) {
		this.id = source.readInt();
		this.name = source.readString();
		this.title = source.readString();
		this.workspace = source.readString();
		this.wmsUrl = source.readString();
		this.legendUrl = source.readString();
		this.checked = source.readByte() != 0;
		this.times = source.createStringArray();
		this.mapOverlay = (TileOverlay) source.readValue(null);		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object collection1, Object collection2) {
		Collection coll1 = (Collection) collection1;
		Collection coll2 = (Collection) collection2;
		return coll1.name.compareToIgnoreCase(coll2.name);
	}
	
	

	@Override
	public int describeContents() {
		return 0;
	}

	public int getId() {
		return id;
	}

	public String getCollectionName() {
		return name;
	}

	/**
	 * @return the legendUrl
	 */
	public String getLegendUrl() {
		return legendUrl;
	}

	public TileOverlay getMapOverlay() {
		return mapOverlay;
	}

	public String[] getTimes() {
		return times;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return the wmsUrl
	 */
	public String getWmsUrl() {
		return wmsUrl;
	}

	/**
	 * @return the workspace
	 */
	public String getWorkspace() {
		return workspace;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMapOverlay(TileOverlay mapOverlay) {
		this.mapOverlay = mapOverlay;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean hasTimes(){
		if (times==null)
		return false;
		else
			return true;		
	}
	
	public void toggleChecked() {
		checked = !checked;
	}

	public String toString() {
		return title;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(title);
		dest.writeString(workspace);
		dest.writeString(wmsUrl);
		dest.writeString(legendUrl);
		dest.writeByte((byte) (checked ? 1 : 0));
		dest.writeStringArray(times);
		dest.writeValue(mapOverlay);
	}
}
