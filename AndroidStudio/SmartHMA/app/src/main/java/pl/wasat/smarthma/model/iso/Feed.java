package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.wasat.smarthma.model.feed.Author;
import pl.wasat.smarthma.model.feed.ItemsPerPage;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.feed.Query;
import pl.wasat.smarthma.model.feed.StartIndex;
import pl.wasat.smarthma.model.feed.TotalResults;
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
    private List<Link> link = new ArrayList<Link>();
    private List<EntryISO> entries = new ArrayList<EntryISO>();

    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The totalResults
     */
    public TotalResults getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The totalResults
     */
    public void setTotalResults(TotalResults totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The startIndex
     */
    public StartIndex getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex The startIndex
     */
    public void setStartIndex(StartIndex startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return The itemsPerPage
     */
    public ItemsPerPage getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @param itemsPerPage The itemsPerPage
     */
    public void setItemsPerPage(ItemsPerPage itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return The Query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * @param query The Query
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return The generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * @param generator The generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier The identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * @param updated The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * @return The link
     */
    public List<Link> getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(List<Link> link) {
        this.link = link;
    }

    /**
     * @return the entries
     */
    public List<EntryISO> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<EntryISO> entries) {
        this.entries = entries;
    }


    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
