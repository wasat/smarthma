package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public String getDcSubject() {
        return dcSubject;
    }

    public void setDcSubject(String dcSubject) {
        this.dcSubject = dcSubject;
    }


    public String getEoParentIdentifier() {
        return eoParentIdentifier;
    }

    public void setEoParentIdentifier(String eoParentIdentifier) {
        this.eoParentIdentifier = eoParentIdentifier;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public String getSruRecordSchema() {
        return sruRecordSchema;
    }

    public void setSruRecordSchema(String sruRecordSchema) {
        this.sruRecordSchema = sruRecordSchema;
    }


    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }


    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }


    /**
     * @return the geoBox
     */
    public String getGeoBox() {
        return geoBox;
    }

    /**
     * @param geoBox the geoBox to set
     */
    public void setGeoBox(String geoBox) {
        this.geoBox = geoBox;
    }


    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getGeoUid() {
        return geoUid;
    }

    public void setGeoUid(String geoUid) {
        this.geoUid = geoUid;
    }

    public ArrayList<String> getParamValueList() {
        return paramValueList;
    }

    public void setParamValueList(ArrayList<String> paramValueList) {
        this.paramValueList = paramValueList;
    }

    public ArrayList<String> getParamNameList() {
        return paramNameList;
    }

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
