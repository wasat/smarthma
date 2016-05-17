package pl.wasat.smarthma.model.feed;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class Link implements Serializable {

    public static final String REL_ALTERNATE = "alternate";
    public static final String REL_SEARCH = "search";
    public static final String REL_RESULTS = "results";
    public static final String REL_COLLECTION = "collection";
    public static final String TYPE_ATOM_XML = "application/atom+xml";
    public static final String TYPE_OSDD_XML = "application/opensearchdescription+xml";
    public static final String REL_SELF = "self";
    public static final String REL_FIRST = "first";
    public static final String REL_NEXT = "next";
    public static final String REL_LAST = "last";
    public static final String REL_PREVIOUS = "previous";

    private static final long serialVersionUID = 1L;

    private String href;
    private String rel;
    private String title;
    private String type;

    public String getHref() {
        validateHref();
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getTitle() {
        if (title == null) return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void validateHref() {
        this.href = this.href.replaceAll("\\+", "%2B");
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
