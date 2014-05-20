package pl.wasat.smarthma.model.dataseries;

import java.io.Serializable;
import java.util.ArrayList;


public class DataSeriesFeeds implements Serializable {

	public static final String KEY_EO_RSS_FEEDS = "EO_RSS_FEEDS";

	private static final long serialVersionUID = 1L;
	private String title;
	private String subtitle;
	private String generator;
	private String updated;
	private String id;
	private ArrayList<String> link;
	private int totalResults;
	private int startIndex;
	private int itemsPerPage;
	private String Query;
	private ArrayList<Entry> entries;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the generator
	 */
	public String getGenerator() {
		return generator;
	}

	/**
	 * @param generator
	 *            the generator to set
	 */
	public void setGenerator(String generator) {
		this.generator = generator;
	}

	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the link
	 */
	public ArrayList<String> getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(ArrayList<String> link) {
		this.link = link;
	}

	/**
	 * @return the totalResults
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * @param totalResults
	 *            the totalResults to set
	 */
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the itemsPerPage
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @param itemsPerPage
	 *            the itemsPerPage to set
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return Query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		Query = query;
	}

	/**
	 * @return the entries
	 */
	public ArrayList<Entry> getEntries() {
		return entries;
	}

	/**
	 * @param entries
	 *            the entries to set
	 */
	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
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
		result = prime * result + ((Query == null) ? 0 : Query.hashCode());
		result = prime * result + ((entries == null) ? 0 : entries.hashCode());
		result = prime * result
				+ ((generator == null) ? 0 : generator.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + itemsPerPage;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + startIndex;
		result = prime * result
				+ ((subtitle == null) ? 0 : subtitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + totalResults;
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
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
		DataSeriesFeeds other = (DataSeriesFeeds) obj;
		if (Query == null) {
			if (other.Query != null)
				return false;
		} else if (!Query.equals(other.Query))
			return false;
		if (entries == null) {
			if (other.entries != null)
				return false;
		} else if (!entries.equals(other.entries))
			return false;
		if (generator == null) {
			if (other.generator != null)
				return false;
		} else if (!generator.equals(other.generator))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemsPerPage != other.itemsPerPage)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (startIndex != other.startIndex)
			return false;
		if (subtitle == null) {
			if (other.subtitle != null)
				return false;
		} else if (!subtitle.equals(other.subtitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (totalResults != other.totalResults)
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
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
		return "RssFeeds [title=" + title + ", subtitle=" + subtitle
				+ ", generator=" + generator + ", updated=" + updated + ", id="
				+ id + ", link=" + link + ", totalResults=" + totalResults
				+ ", startIndex=" + startIndex + ", itemsPerPage="
				+ itemsPerPage + ", Query=" + Query + ", entries=" + entries
				+ "]";
	}

}