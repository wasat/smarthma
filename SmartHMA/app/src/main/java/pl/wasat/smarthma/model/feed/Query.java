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

package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


/**
 * The type Query.
 */
public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prefix;
    private String count;
    private String dcSubject;
    private String dcType;
    private String eoParentIdentifier;
    private String role;
    private String searchTerms;
    private String sruRecordSchema;
    private String startIndex;
    private String timeEnd;
    private String timeStart;
    private String geoBox;
    private String geoUid;
    private ArrayList<String> paramValueList;
    private ArrayList<String> paramNameList;

    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets prefix.
     *
     * @param prefix the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    /**
     * Gets count.
     *
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(String count) {
        this.count = count;
    }


    /**
     * Gets dc subject.
     *
     * @return the dc subject
     */
    public String getDcSubject() {
        return dcSubject;
    }

    /**
     * Sets dc subject.
     *
     * @param dcSubject the dc subject
     */
    public void setDcSubject(String dcSubject) {
        this.dcSubject = dcSubject;
    }


    /**
     * Gets eo parent identifier.
     *
     * @return the eo parent identifier
     */
    public String getEoParentIdentifier() {
        return eoParentIdentifier;
    }

    /**
     * Sets eo parent identifier.
     *
     * @param eoParentIdentifier the eo parent identifier
     */
    public void setEoParentIdentifier(String eoParentIdentifier) {
        this.eoParentIdentifier = eoParentIdentifier;
    }


    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets search terms.
     *
     * @return the search terms
     */
    public String getSearchTerms() {
        return searchTerms;
    }

    /**
     * Sets search terms.
     *
     * @param searchTerms the search terms
     */
    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    /**
     * Gets sru record schema.
     *
     * @return the sru record schema
     */
    public String getSruRecordSchema() {
        return sruRecordSchema;
    }

    /**
     * Sets sru record schema.
     *
     * @param sruRecordSchema the sru record schema
     */
    public void setSruRecordSchema(String sruRecordSchema) {
        this.sruRecordSchema = sruRecordSchema;
    }


    /**
     * Gets start index.
     *
     * @return the start index
     */
    public String getStartIndex() {
        return startIndex;
    }

    /**
     * Sets start index.
     *
     * @param startIndex the start index
     */
    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets time end.
     *
     * @return the time end
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * Sets time end.
     *
     * @param timeEnd the time end
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }


    /**
     * Gets time start.
     *
     * @return the time start
     */
    public String getTimeStart() {
        return timeStart;
    }

    /**
     * Sets time start.
     *
     * @param timeStart the time start
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }


    /**
     * Gets geo box.
     *
     * @return the geoBox
     */
    public String getGeoBox() {
        return geoBox;
    }

    /**
     * Sets geo box.
     *
     * @param geoBox the geoBox to set
     */
    public void setGeoBox(String geoBox) {
        this.geoBox = geoBox;
    }


    /**
     * Gets dc type.
     *
     * @return the dc type
     */
    public String getDcType() {
        return dcType;
    }

    /**
     * Sets dc type.
     *
     * @param dcType the dc type
     */
    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    /**
     * Gets geo uid.
     *
     * @return the geo uid
     */
    public String getGeoUid() {
        return geoUid;
    }

    /**
     * Sets geo uid.
     *
     * @param geoUid the geo uid
     */
    public void setGeoUid(String geoUid) {
        this.geoUid = geoUid;
    }

    /**
     * Gets param value list.
     *
     * @return the param value list
     */
    public ArrayList<String> getParamValueList() {
        return paramValueList;
    }

    /**
     * Sets param value list.
     *
     * @param paramValueList the param value list
     */
    public void setParamValueList(ArrayList<String> paramValueList) {
        this.paramValueList = paramValueList;
    }

    /**
     * Gets param name list.
     *
     * @return the param name list
     */
    public ArrayList<String> getParamNameList() {
        return paramNameList;
    }

    /**
     * Sets param name list.
     *
     * @param paramNameList the param name list
     */
    public void setParamNameList(ArrayList<String> paramNameList) {
        this.paramNameList = paramNameList;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }


}
