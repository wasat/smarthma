
package pl.wasat.smarthma.model.feed;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Query {

    private String __prefix;
    private String _count;
    private String _dc_subject;
    private String _eo_parentIdentifier;
    private String _role;
    private String searchTerms;
    private String _sru_recordSchema;
    private String _startIndex;
    private String _time_end;
    private String _time_start;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public Query with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public String get_count() {
        return _count;
    }

    public void set_count(String _count) {
        this._count = _count;
    }

    public Query with_count(String _count) {
        this._count = _count;
        return this;
    }

    public String get_dc_subject() {
        return _dc_subject;
    }

    public void set_dc_subject(String _dc_subject) {
        this._dc_subject = _dc_subject;
    }

    public Query with_dc_subject(String _dc_subject) {
        this._dc_subject = _dc_subject;
        return this;
    }

    public String get_eo_parentIdentifier() {
        return _eo_parentIdentifier;
    }

    public void set_eo_parentIdentifier(String _eo_parentIdentifier) {
        this._eo_parentIdentifier = _eo_parentIdentifier;
    }

    public Query with_eo_parentIdentifier(String _eo_parentIdentifier) {
        this._eo_parentIdentifier = _eo_parentIdentifier;
        return this;
    }

    public String get_role() {
        return _role;
    }

    public void set_role(String _role) {
        this._role = _role;
    }

    public Query with_role(String _role) {
        this._role = _role;
        return this;
    }
    
    public String get_searchTerms() {
        return searchTerms;
    }

    public void set_searchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Query with_searchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
        return this;
    }

    public String get_sru_recordSchema() {
        return _sru_recordSchema;
    }

    public void set_sru_recordSchema(String _sru_recordSchema) {
        this._sru_recordSchema = _sru_recordSchema;
    }

    public Query with_sru_recordSchema(String _sru_recordSchema) {
        this._sru_recordSchema = _sru_recordSchema;
        return this;
    }

    public String get_startIndex() {
        return _startIndex;
    }

    public void set_startIndex(String _startIndex) {
        this._startIndex = _startIndex;
    }

    public Query with_startIndex(String _startIndex) {
        this._startIndex = _startIndex;
        return this;
    }

    public String get_time_end() {
        return _time_end;
    }

    public void set_time_end(String _time_end) {
        this._time_end = _time_end;
    }

    public Query with_time_end(String _time_end) {
        this._time_end = _time_end;
        return this;
    }

    public String get_time_start() {
        return _time_start;
    }

    public void set_time_start(String _time_start) {
        this._time_start = _time_start;
    }

    public Query with_time_start(String _time_start) {
        this._time_start = _time_start;
        return this;
    }

    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
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
