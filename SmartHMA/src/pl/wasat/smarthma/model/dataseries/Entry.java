/**
 * 
 */
package pl.wasat.smarthma.model.dataseries;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Wasat Sp. z o.o
 * 
 */
public class Entry implements Serializable {
	/**
	 * 
	 */
	public static final String KEY_RSS_ENTRY = "KEY_RSS_ENTRY";

	private static final long serialVersionUID = -8995609562150813178L;
	private String id;
	private String guid;
	private String title;
	private String published;
	private String updated;
	private String identifier;
	private String date;
	private Where where;
	private ArrayList<String> link;
	private String encodedContent;
	private boolean read;
	private boolean offline;
	private long dbId;



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the published
	 */
	public String getPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(String published) {
		this.published = published;
	}

	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the where
	 */
	public Where getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(Where where) {
		this.where = where;
	}

	/**
	 * @return the link
	 */
	public ArrayList<String> getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(ArrayList<String> link) {
		this.link = link;
	}

	/**
	 * @return the encodedContent
	 */
	public String getEncodedContent() {
		return encodedContent;
	}

	/**
	 * @param encodedContent the encodedContent to set
	 */
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = extractCData(encodedContent);
	}

	/**
	 * @return the read
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(boolean read) {
		this.read = read;
	}

	/**
	 * @return the offline
	 */
	public boolean isOffline() {
		return offline;
	}

	/**
	 * @param offline the offline to set
	 */
	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	/**
	 * @return the dbId
	 */
	public long getDbId() {
		return dbId;
	}

	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	private String extractCData(String data) {
		data = data.replaceAll("<!\\[CDATA\\[", "");
		data = data.replaceAll("\\]\\]>", "");
		return data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (dbId ^ (dbId >>> 32));
		result = prime * result
				+ ((encodedContent == null) ? 0 : encodedContent.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + (offline ? 1231 : 1237);
		result = prime * result
				+ ((published == null) ? 0 : published.hashCode());
		result = prime * result + (read ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Entry other = (Entry) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dbId != other.dbId)
			return false;
		if (encodedContent == null) {
			if (other.encodedContent != null)
				return false;
		} else if (!encodedContent.equals(other.encodedContent))
			return false;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (offline != other.offline)
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (read != other.read)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [id=" + id + ", guid=" + guid + ", title=" + title
				+ ", published=" + published + ", updated=" + updated
				+ ", identifier=" + identifier + ", date=" + date + ", where="
				+ where + ", link=" + link + ", encodedContent="
				+ encodedContent + ", read=" + read + ", offline=" + offline
				+ ", dbId=" + dbId + "]";
	}



}
