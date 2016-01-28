package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;
import java.util.ArrayList;

public class IndexInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private ArrayList<Index> indexes;

    public IndexInfo() {
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
     * @return the indexes
     */
    public ArrayList<Index> getIndexes() {
        return indexes;
    }

    /**
     * @param indexes the indexes to set
     */
    public void setIndexes(ArrayList<Index> indexes) {
        this.indexes = indexes;
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
        result = prime * result + id;
        result = prime * result + ((indexes == null) ? 0 : indexes.hashCode());
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
        IndexInfo other = (IndexInfo) obj;
        if (id != other.id)
            return false;
        if (indexes == null) {
            if (other.indexes != null)
                return false;
        } else if (!indexes.equals(other.indexes))
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
        return "IndexInfo [indexes=" + indexes + "]";
    }

}
