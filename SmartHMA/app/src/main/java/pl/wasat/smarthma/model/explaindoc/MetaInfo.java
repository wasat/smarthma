package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;

public class MetaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String dateModified;

    public MetaInfo() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * @param dateModified the dateModified to set
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
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
        result = prime * result
                + ((dateModified == null) ? 0 : dateModified.hashCode());
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
        MetaInfo other = (MetaInfo) obj;
        if (dateModified == null) {
            if (other.dateModified != null)
                return false;
        } else if (!dateModified.equals(other.dateModified))
            return false;
        return id == other.id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MetaInfo [dateModified=" + dateModified + "]";
    }

}
