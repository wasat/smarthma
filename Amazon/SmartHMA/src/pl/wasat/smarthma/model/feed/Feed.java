
package pl.wasat.smarthma.model.feed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Feed implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TotalResults totalResults;
    private StartIndex startIndex;
    private ItemsPerPage itemsPerPage;
    private Query query;
    private Author author;
    private String generator;
    private String id;
    private String title;
    private String updated;
    private List<Link> link = new ArrayList<Link>();
    private List<Entry> entries = new ArrayList<Entry>();
    //private Entry entry;
    private String _xmlns;
    private String _xmlns_dc;
    private String _xmlns_eo;
    private String _xmlns_geo;
    private String _xmlns_georss;
    private String _xmlns_media;
    private String _xmlns_os;
    private String _xmlns_sru;
    private String _xmlns_time;
    private String _xmlns_wrs;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public TotalResults getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(TotalResults totalResults) {
        this.totalResults = totalResults;
    }

    public Feed withTotalResults(TotalResults totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public StartIndex getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(StartIndex startIndex) {
        this.startIndex = startIndex;
    }

    public Feed withStartIndex(StartIndex startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public ItemsPerPage getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(ItemsPerPage itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Feed withItemsPerPage(ItemsPerPage itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Feed withQuery(Query query) {
        this.query = query;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Feed withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public Feed withGenerator(String generator) {
        this.generator = generator;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Feed withId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Feed withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Feed withUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Feed withLink(List<Link> link) {
        this.link = link;
        return this;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Feed withEntries(List<Entry> entries) {
        this.entries = entries;
        return this;
    }

    public String get_xmlns() {
        return _xmlns;
    }

    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public Feed with_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
        return this;
    }

    public String get_xmlns_dc() {
        return _xmlns_dc;
    }

    public void set_xmlns_dc(String _xmlns_dc) {
        this._xmlns_dc = _xmlns_dc;
    }

    public Feed with_xmlns_dc(String _xmlns_dc) {
        this._xmlns_dc = _xmlns_dc;
        return this;
    }

    public String get_xmlns_eo() {
        return _xmlns_eo;
    }

    public void set_xmlns_eo(String _xmlns_eo) {
        this._xmlns_eo = _xmlns_eo;
    }

    public Feed with_xmlns_eo(String _xmlns_eo) {
        this._xmlns_eo = _xmlns_eo;
        return this;
    }

    public String get_xmlns_geo() {
        return _xmlns_geo;
    }

    public void set_xmlns_geo(String _xmlns_geo) {
        this._xmlns_geo = _xmlns_geo;
    }

    public Feed with_xmlns_geo(String _xmlns_geo) {
        this._xmlns_geo = _xmlns_geo;
        return this;
    }

    public String get_xmlns_georss() {
        return _xmlns_georss;
    }

    public void set_xmlns_georss(String _xmlns_georss) {
        this._xmlns_georss = _xmlns_georss;
    }

    public Feed with_xmlns_georss(String _xmlns_georss) {
        this._xmlns_georss = _xmlns_georss;
        return this;
    }

    public String get_xmlns_media() {
        return _xmlns_media;
    }

    public void set_xmlns_media(String _xmlns_media) {
        this._xmlns_media = _xmlns_media;
    }

    public Feed with_xmlns_media(String _xmlns_media) {
        this._xmlns_media = _xmlns_media;
        return this;
    }

    public String get_xmlns_os() {
        return _xmlns_os;
    }

    public void set_xmlns_os(String _xmlns_os) {
        this._xmlns_os = _xmlns_os;
    }

    public Feed with_xmlns_os(String _xmlns_os) {
        this._xmlns_os = _xmlns_os;
        return this;
    }

    public String get_xmlns_sru() {
        return _xmlns_sru;
    }

    public void set_xmlns_sru(String _xmlns_sru) {
        this._xmlns_sru = _xmlns_sru;
    }

    public Feed with_xmlns_sru(String _xmlns_sru) {
        this._xmlns_sru = _xmlns_sru;
        return this;
    }

    public String get_xmlns_time() {
        return _xmlns_time;
    }

    public void set_xmlns_time(String _xmlns_time) {
        this._xmlns_time = _xmlns_time;
    }

    public Feed with_xmlns_time(String _xmlns_time) {
        this._xmlns_time = _xmlns_time;
        return this;
    }

    public String get_xmlns_wrs() {
        return _xmlns_wrs;
    }

    public void set_xmlns_wrs(String _xmlns_wrs) {
        this._xmlns_wrs = _xmlns_wrs;
    }

    public Feed with_xmlns_wrs(String _xmlns_wrs) {
        this._xmlns_wrs = _xmlns_wrs;
        return this;
    }

    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this, style);
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
