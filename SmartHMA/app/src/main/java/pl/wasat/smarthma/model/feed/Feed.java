package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.dc.EntryDC;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.iso.EntryISO;
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
    private String identifier;
    private String title;
    private String updated;
    private List<Link> link = new ArrayList<>();
    private EOPrefixes eoPrefixes;
    private ISOPrefixes isoPrefixes;
    private DCPrefixes dcPrefixes;
    private List<Entry> entries = new ArrayList<>();
    private List<Entry> entriesEO = new ArrayList<>();
    private List<EntryISO> entriesISO = new ArrayList<>();
    private List<EntryDC> entriesDC = new ArrayList<>();


    public TotalResults getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(TotalResults totalResults) {
        this.totalResults = totalResults;
    }

    public StartIndex getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(StartIndex startIndex) {
        this.startIndex = startIndex;
    }

    public ItemsPerPage getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(ItemsPerPage itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public EOPrefixes getEoPrefixes() {
        return eoPrefixes;
    }

    public void setEoPrefixes(EOPrefixes eoPrefixes) {
        this.eoPrefixes = eoPrefixes;
    }

    public ISOPrefixes getIsoPrefixes() {
        return isoPrefixes;
    }

    public void setIsoPrefixes(ISOPrefixes isoPrefixes) {
        this.isoPrefixes = isoPrefixes;
    }

    public DCPrefixes getDCPrefixes() {
        return dcPrefixes;
    }

    public void setDCPrefixes(DCPrefixes dcPrefixes) {
        this.dcPrefixes = dcPrefixes;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }


    public List<Entry> getEntriesEO() {
        return entriesEO;
    }

    public void setEntriesEO(List<Entry> entriesEO) {
        this.entriesEO = entriesEO;
    }

    public List<EntryISO> getEntriesISO() {
        return entriesISO;
    }

    public void setEntriesISO(List<EntryISO> entriesISO) {
        this.entriesISO = entriesISO;
    }


    public List<EntryDC> getEntryDC() {
        return entriesDC;
    }

    public void setEntryDC(List<EntryDC> entriesDC) {
        this.entriesDC = entriesDC;
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
