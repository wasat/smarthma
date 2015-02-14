package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Query with_prefix(String _prefix) {
        this.prefix = _prefix;
        return this;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Query with_count(String _count) {
        this.count = _count;
        return this;
    }

    public String getDcSubject() {
        return dcSubject;
    }

    public void setDcSubject(String dcSubject) {
        this.dcSubject = dcSubject;
    }

    public Query with_dc_subject(String _dc_subject) {
        this.dcSubject = _dc_subject;
        return this;
    }

    public String getEoParentIdentifier() {
        return eoParentIdentifier;
    }

    public void setEoParentIdentifier(String eoParentIdentifier) {
        this.eoParentIdentifier = eoParentIdentifier;
    }

    public Query with_eo_parentIdentifier(String _eo_parentIdentifier) {
        this.eoParentIdentifier = _eo_parentIdentifier;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Query with_role(String _role) {
        this.role = _role;
        return this;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Query with_searchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
        return this;
    }

    public String getSruRecordSchema() {
        return sruRecordSchema;
    }

    public void setSruRecordSchema(String sruRecordSchema) {
        this.sruRecordSchema = sruRecordSchema;
    }

    public Query with_sru_recordSchema(String _sru_recordSchema) {
        this.sruRecordSchema = _sru_recordSchema;
        return this;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public Query with_startIndex(String _startIndex) {
        this.startIndex = _startIndex;
        return this;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Query with_time_end(String _time_end) {
        this.timeEnd = _time_end;
        return this;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Query with_time_start(String _time_start) {
        this.timeStart = _time_start;
        return this;
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

    public Query with_geo_box(String _geo_box) {
        this.geoBox = _geo_box;
        return this;
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

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
