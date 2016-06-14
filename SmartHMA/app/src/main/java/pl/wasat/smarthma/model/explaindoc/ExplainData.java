/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.model.explaindoc;

import java.io.Serializable;

/**
 * The type Explain data.
 */
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

    /**
     * Instantiates a new Explain data.
     */
    public ExplainData() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets server info.
     *
     * @return the serverInfo
     */
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    /**
     * Sets server info.
     *
     * @param serverInfo the serverInfo to set
     */
    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    /**
     * Gets database info.
     *
     * @return the databaseInfo
     */
    public DatabaseInfo getDatabaseInfo() {
        return databaseInfo;
    }

    /**
     * Sets database info.
     *
     * @param databaseInfo the databaseInfo to set
     */
    public void setDatabaseInfo(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    /**
     * Gets meta info.
     *
     * @return the metaInfo
     */
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    /**
     * Sets meta info.
     *
     * @param metaInfo the metaInfo to set
     */
    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    /**
     * Gets index info.
     *
     * @return the indexInfo
     */
    public IndexInfo getIndexInfo() {
        return indexInfo;
    }

    /**
     * Sets index info.
     *
     * @param indexInfo the indexInfo to set
     */
    public void setIndexInfo(IndexInfo indexInfo) {
        this.indexInfo = indexInfo;
    }

    /**
     * Gets schema info.
     *
     * @return the schemaInfo
     */
    public SchemaInfo getSchemaInfo() {
        return schemaInfo;
    }

    /**
     * Sets schema info.
     *
     * @param schemaInfo the schemaInfo to set
     */
    public void setSchemaInfo(SchemaInfo schemaInfo) {
        this.schemaInfo = schemaInfo;
    }

    /**
     * Gets config info.
     *
     * @return the configInfo
     */
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    /**
     * Sets config info.
     *
     * @param configInfo the configInfo to set
     */
    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    /**
     * Gets response format.
     *
     * @return the responseFormat
     */
    public ResponseFormat getResponseFormat() {
        return responseFormat;
    }

    /**
     * Sets response format.
     *
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
