package pl.wasat.smarthma.model;

import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable, Comparator<Object> {

	public static Creator<Product> CREATOR = new Creator<Product>() {
		@Override
		public Product createFromParcel(Parcel source) {
			return new Product(source);
		}

		@Override
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

	private int id;
	private String name;

	public Product(Parcel source) {
		this.id = source.readInt();
		this.name = source.readString();
	}

	@Override
	public int compare(Object product1, Object product2) {
		Product prod1 = (Product) product1;
		Product prod2 = (Product) product2;
		return prod1.name.compareToIgnoreCase(prod2.name);
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
