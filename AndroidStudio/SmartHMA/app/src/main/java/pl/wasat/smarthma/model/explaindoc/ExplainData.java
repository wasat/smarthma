package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;

public class ExplainData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private ServerInfo serverInfo;
    private DatabaseInfo databaseInfo;
    private MetaInfo metaInfo;
    private IndexInfo indexInfo;
    private SchemaInfo schemaInfo;
    private ConfigInfo configInfo;
    private ResponseFormat responseFormat;

    public ExplainData() {
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
     * @return the serverInfo
     */
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    /**
     * @param serverInfo the serverInfo to set
     */
    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    /**
     * @return the databaseInfo
     */
    public DatabaseInfo getDatabaseInfo() {
        return databaseInfo;
    }

    /**
     * @param databaseInfo the databaseInfo to set
     */
    public void setDatabaseInfo(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    /**
     * @return the metaInfo
     */
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    /**
     * @param metaInfo the metaInfo to set
     */
    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    /**
     * @return the indexInfo
     */
    public IndexInfo getIndexInfo() {
        return indexInfo;
    }

    /**
     * @param indexInfo the indexInfo to set
     */
    public void setIndexInfo(IndexInfo indexInfo) {
        this.indexInfo = indexInfo;
    }

    /**
     * @return the schemaInfo
     */
    public SchemaInfo getSchemaInfo() {
        return schemaInfo;
    }

    /**
     * @param schemaInfo the schemaInfo to set
     */
    public void setSchemaInfo(SchemaInfo schemaInfo) {
        this.schemaInfo = schemaInfo;
    }

    /**
     * @return the configInfo
     */
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    /**
     * @param configInfo the configInfo to set
     */
    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    /**
     * @return the responseFormat
     */
    public ResponseFormat getResponseFormat() {
        return responseFormat;
    }

    /**
     * @param responseFormat the responseFormat to set
     */
    public void setResponseFormat(ResponseFormat responseFormat) {
        this.responseFormat = responseFormat;
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
                + ((configInfo == null) ? 0 : configInfo.hashCode());
        result = prime * result
                + ((databaseInfo == null) ? 0 : databaseInfo.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((indexInfo == null) ? 0 : indexInfo.hashCode());
        result = prime * result
                + ((metaInfo == null) ? 0 : metaInfo.hashCode());
        result = prime * result
                + ((responseFormat == null) ? 0 : responseFormat.hashCode());
        result = prime * result
                + ((schemaInfo == null) ? 0 : schemaInfo.hashCode());
        result = prime * result
                + ((serverInfo == null) ? 0 : serverInfo.hashCode());
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
        ExplainData other = (ExplainData) obj;
        if (configInfo == null) {
            if (other.configInfo != null)
                return false;
        } else if (!configInfo.equals(other.configInfo))
            return false;
        if (databaseInfo == null) {
            if (other.databaseInfo != null)
                return false;
        } else if (!databaseInfo.equals(other.databaseInfo))
            return false;
        if (id != other.id)
            return false;
        if (indexInfo == null) {
            if (other.indexInfo != null)
                return false;
        } else if (!indexInfo.equals(other.indexInfo))
            return false;
        if (metaInfo == null) {
            if (other.metaInfo != null)
                return false;
        } else if (!metaInfo.equals(other.metaInfo))
            return false;
        if (responseFormat == null) {
            if (other.responseFormat != null)
                return false;
        } else if (!responseFormat.equals(other.responseFormat))
            return false;
        if (schemaInfo == null) {
            if (other.schemaInfo != null)
                return false;
        } else if (!schemaInfo.equals(other.schemaInfo))
            return false;
        if (serverInfo == null) {
            if (other.serverInfo != null)
                return false;
        } else if (!serverInfo.equals(other.serverInfo))
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
        return "ExplainData [serverInfo=" + serverInfo + ", databaseInfo="
                + databaseInfo + ", metaInfo=" + metaInfo + ", indexInfo="
                + indexInfo + ", schemaInfo=" + schemaInfo + ", configInfo="
                + configInfo + ", responseFormat=" + responseFormat + "]";
    }

}
