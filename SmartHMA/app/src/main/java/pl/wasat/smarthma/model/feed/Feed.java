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
import java.util.List;

import pl.wasat.smarthma.model.dc.EntryDC;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Feed.
 */
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


    /**
     * Gets total results.
     *
     * @return the total results
     */
    public TotalResults getTotalResults() {
        if (totalResults == null) {
            totalResults = new TotalResults();
            totalResults.setText("0");
        }
        return totalResults;
    }

    /**
     * Sets total results.
     *
     * @param totalResults the total results
     */
    public void setTotalResults(TotalResults totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets start index.
     *
     * @return the start index
     */
    public StartIndex getStartIndex() {
        return startIndex;
    }

    /**
     * Sets start index.
     *
     * @param startIndex the start index
     */
    public void setStartIndex(StartIndex startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets items per page.
     *
     * @return the items per page
     */
    public ItemsPerPage getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets items per page.
     *
     * @param itemsPerPage the items per page
     */
    public void setItemsPerPage(ItemsPerPage itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Gets generator.
     *
     * @return the generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * Sets generator.
     *
     * @param generator the generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets updated.
     *
     * @return the updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * Sets updated.
     *
     * @param updated the updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public List<Link> getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(List<Link> link) {
        this.link = link;
    }

    /**
     * Gets eo prefixes.
     *
     * @return the eo prefixes
     */
    public EOPrefixes getEoPrefixes() {
        return eoPrefixes;
    }

    /**
     * Sets eo prefixes.
     *
     * @param eoPrefixes the eo prefixes
     */
    public void setEoPrefixes(EOPrefixes eoPrefixes) {
        this.eoPrefixes = eoPrefixes;
    }

    /**
     * Gets iso prefixes.
     *
     * @return the iso prefixes
     */
    public ISOPrefixes getIsoPrefixes() {
        return isoPrefixes;
    }

    /**
     * Sets iso prefixes.
     *
     * @param isoPrefixes the iso prefixes
     */
    public void setIsoPrefixes(ISOPrefixes isoPrefixes) {
        this.isoPrefixes = isoPrefixes;
    }

    /**
     * Gets dc prefixes.
     *
     * @return the dc prefixes
     */
    public DCPrefixes getDCPrefixes() {
        return dcPrefixes;
    }

    /**
     * Sets dc prefixes.
     *
     * @param dcPrefixes the dc prefixes
     */
    public void setDCPrefixes(DCPrefixes dcPrefixes) {
        this.dcPrefixes = dcPrefixes;
    }

    /**
     * Gets entries.
     *
     * @return the entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Sets entries.
     *
     * @param entries the entries
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }


    /**
     * Gets entries eo.
     *
     * @return the entries eo
     */
    public List<Entry> getEntriesEO() {
        return entriesEO;
    }

    /**
     * Sets entries eo.
     *
     * @param entriesEO the entries eo
     */
    public void setEntriesEO(List<Entry> entriesEO) {
        this.entriesEO = entriesEO;
    }

    /**
     * Gets entries iso.
     *
     * @return the entries iso
     */
    public List<EntryISO> getEntriesISO() {
        return entriesISO;
    }

    /**
     * Sets entries iso.
     *
     * @param entriesISO the entries iso
     */
    public void setEntriesISO(List<EntryISO> entriesISO) {
        this.entriesISO = entriesISO;
    }


    /**
     * Gets entry dc.
     *
     * @return the entry dc
     */
    public List<EntryDC> getEntryDC() {
        return entriesDC;
    }

    /**
     * Sets entry dc.
     *
     * @param entriesDC the entries dc
     */
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
