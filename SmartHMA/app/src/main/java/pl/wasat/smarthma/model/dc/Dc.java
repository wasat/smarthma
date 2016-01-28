
package pl.wasat.smarthma.model.dc;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Dc implements Serializable {

    private Title title;
    private Creator creator;
    private Subject subject;
    private Description description;
    private Publisher publisher;
    private Contributor contributor;
    private Date date;
    private Type type;
    private Format format;
    private Identifier identifier;
    private Source source;
    private Language language;
    private Relation relation;
    private Coverage coverage;
    private Rights rights;
    private String XmlnsSrwDc;
    private String XmlnsXsi;
    private String XsiSchemaLocation;
    private String Prefix;

    /**
     * @return The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return The creator
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * @param creator The creator
     */
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    /**
     * @return The subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject The subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @return The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * @return The publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * @param publisher The publisher
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * @return The contributor
     */
    public Contributor getContributor() {
        return contributor;
    }

    /**
     * @param contributor The contributor
     */
    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    /**
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return The type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return The format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * @return The identifier
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier The identifier
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    /**
     * @return The source
     */
    public Source getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * @return The language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * @return The relation
     */
    public Relation getRelation() {
        return relation;
    }

    /**
     * @param relation The relation
     */
    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    /**
     * @return The coverage
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * @param coverage The coverage
     */
    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    /**
     * @return The rights
     */
    public Rights getRights() {
        return rights;
    }

    /**
     * @param rights The rights
     */
    public void setRights(Rights rights) {
        this.rights = rights;
    }

    /**
     * @return The XmlnsSrwDc
     */
    public String getXmlnsSrwDc() {
        return XmlnsSrwDc;
    }

    /**
     * @param XmlnsSrwDc The _xmlns:srw_dc
     */
    public void setXmlnsSrwDc(String XmlnsSrwDc) {
        this.XmlnsSrwDc = XmlnsSrwDc;
    }

    /**
     * @return The XmlnsXsi
     */
    public String getXmlnsXsi() {
        return XmlnsXsi;
    }

    /**
     * @param XmlnsXsi The _xmlns:xsi
     */
    public void setXmlnsXsi(String XmlnsXsi) {
        this.XmlnsXsi = XmlnsXsi;
    }

    /**
     * @return The XsiSchemaLocation
     */
    public String getXsiSchemaLocation() {
        return XsiSchemaLocation;
    }

    /**
     * @param XsiSchemaLocation The _xsi:schemaLocation
     */
    public void setXsiSchemaLocation(String XsiSchemaLocation) {
        this.XsiSchemaLocation = XsiSchemaLocation;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The __prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(creator).append(subject).append(description).append(publisher).append(contributor).append(date).append(type).append(format).append(identifier).append(source).append(language).append(relation).append(coverage).append(rights).append(XmlnsSrwDc).append(XmlnsXsi).append(XsiSchemaLocation).append(Prefix).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Dc)) {
            return false;
        }
        Dc rhs = ((Dc) other);
        return new EqualsBuilder().append(title, rhs.title).append(creator, rhs.creator).append(subject, rhs.subject).append(description, rhs.description).append(publisher, rhs.publisher).append(contributor, rhs.contributor).append(date, rhs.date).append(type, rhs.type).append(format, rhs.format).append(identifier, rhs.identifier).append(source, rhs.source).append(language, rhs.language).append(relation, rhs.relation).append(coverage, rhs.coverage).append(rights, rhs.rights).append(XmlnsSrwDc, rhs.XmlnsSrwDc).append(XmlnsXsi, rhs.XmlnsXsi).append(XsiSchemaLocation, rhs.XsiSchemaLocation).append(Prefix, rhs.Prefix).isEquals();
    }

}
