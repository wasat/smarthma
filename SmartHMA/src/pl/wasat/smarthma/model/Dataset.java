package pl.wasat.smarthma.model;

import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

public class Dataset implements Parcelable, Comparator<Object> {

	public static Creator<Dataset> CREATOR = new Creator<Dataset>() {
		@Override
		public Dataset createFromParcel(Parcel source) {
			return new Dataset(source);
		}

		@Override
		public Dataset[] newArray(int size) {
			return new Dataset[size];
		}
	};

	private int id;
	private String name;

	public Dataset(Parcel source) {
		this.id = source.readInt();
		this.name = source.readString();
	}

	@Override
	public int compare(Object dataset1, Object dataset2) {
		Dataset ds1 = (Dataset) dataset1;
		Dataset ds2 = (Dataset) dataset2;
		return ds1.name.compareToIgnoreCase(ds2.name);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
