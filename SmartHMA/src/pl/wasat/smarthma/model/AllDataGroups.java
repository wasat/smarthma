package pl.wasat.smarthma.model;

import java.util.ArrayList;

public class AllDataGroups {

	private int id;
	private ArrayList<CollectionsGroup> allData;

	public AllDataGroups() {
		allData = new ArrayList<CollectionsGroup>();
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
	 * @return the allData
	 */
	public ArrayList<CollectionsGroup> getAllData() {
		return allData;
	}

	/**
	 * @param allData the allData to set
	 */
	public void setAllData(ArrayList<CollectionsGroup> allData) {
		this.allData = allData;
	}

	public void addItem(CollectionsGroup collGroup) {
		allData.add(collGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allData == null) ? 0 : allData.hashCode());
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllDataGroups other = (AllDataGroups) obj;
		if (allData == null) {
			if (other.allData != null)
				return false;
		} else if (!allData.equals(other.allData))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllDataGroups [allData=" + allData + "]";
	}

}
