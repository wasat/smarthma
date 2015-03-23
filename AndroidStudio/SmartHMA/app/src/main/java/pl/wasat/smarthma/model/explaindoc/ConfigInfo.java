package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;
import java.util.ArrayList;

public class ConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String default_type;
    private ArrayList<String> supports;
    private String setting;

    public ConfigInfo() {
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
     * @return the default_type
     */
    public String getDefault_type() {
        return default_type;
    }

    /**
     * @param default_type the default_type to set
     */
    public void setDefault_type(String default_type) {
        this.default_type = default_type;
    }

    /**
     * @return the supports
     */
    public ArrayList<String> getSupports() {
        return supports;
    }

    /**
     * @param supports the supports to set
     */
    public void setSupports(ArrayList<String> supports) {
        this.supports = supports;
    }

    /**
     * @return the setting
     */
    public String getSetting() {
        return setting;
    }

    /**
     * @param setting the setting to set
     */
    public void setSetting(String setting) {
        this.setting = setting;
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
                + ((default_type == null) ? 0 : default_type.hashCode());
        result = prime * result + id;
        result = prime * result + ((setting == null) ? 0 : setting.hashCode());
        result = prime * result
                + ((supports == null) ? 0 : supports.hashCode());
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
        ConfigInfo other = (ConfigInfo) obj;
        if (default_type == null) {
            if (other.default_type != null)
                return false;
        } else if (!default_type.equals(other.default_type))
            return false;
        if (id != other.id)
            return false;
        if (setting == null) {
            if (other.setting != null)
                return false;
        } else if (!setting.equals(other.setting))
            return false;
        if (supports == null) {
            if (other.supports != null)
                return false;
        } else if (!supports.equals(other.supports))
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
        return "ConfigInfo [default_type=" + default_type + ", supports="
                + supports + ", setting=" + setting + "]";
    }

}
