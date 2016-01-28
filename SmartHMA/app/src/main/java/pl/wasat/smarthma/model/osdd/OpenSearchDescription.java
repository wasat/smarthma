package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


public class OpenSearchDescription {

    private String ShortName;
    private String LongName;
    private String Description;
    private List<pl.wasat.smarthma.model.osdd.Url> Url = new ArrayList<>();
    //private List<pl.wasat.smarthma.model.osdd.Parameter> Parameter = new ArrayList<>();
    private List<pl.wasat.smarthma.model.osdd.Query> Query = new ArrayList<>();
    private String Tags;
    private List<pl.wasat.smarthma.model.osdd.Image> Image = new ArrayList<>();
    private String Developer;
    private String Attribution;
    private String SyndicationRight;
    private String AdultContent;
    private String Language;
    private String OutputEncoding;
    private String InputEncoding;
    private String Xmlns;
    private String XmlnsDc;
    private String XmlnsEo;
    private String XmlnsGeo;
    private String XmlnsParam;
    private String XmlnsSru;
    private String XmlnsTime;


    /**
     * @return The ShortName
     */
    public String getShortName() {
        return ShortName;
    }

    /**
     * @param ShortName The ShortName
     */
    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    /**
     * @return The LongName
     */
    public String getLongName() {
        return LongName;
    }

    /**
     * @param LongName The LongName
     */
    public void setLongName(String LongName) {
        this.LongName = LongName;
    }

    /**
     * @return The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return The Url
     */
    public List<pl.wasat.smarthma.model.osdd.Url> getUrl() {
        return Url;
    }

    /**
     * @param Url The Url
     */
    public void setUrl(List<pl.wasat.smarthma.model.osdd.Url> Url) {
        this.Url = Url;
    }

/*    *//**
     * @return The Parameter
     *//*
    public List<pl.wasat.smarthma.model.osdd.Parameter> getParameter() {
        return Parameter;
    }*/

/*    *//**
     * @param Parameter The Parameter
     *//*
    public void setParameter(List<pl.wasat.smarthma.model.osdd.Parameter> Parameter) {
        this.Parameter = Parameter;
    }*/

    /**
     * @return The Query
     */
    public List<pl.wasat.smarthma.model.osdd.Query> getQuery() {
        return Query;
    }

    /**
     * @param Query The Query
     */
    public void setQuery(List<pl.wasat.smarthma.model.osdd.Query> Query) {
        this.Query = Query;
    }

    /**
     * @return The Tags
     */
    public String getTags() {
        return Tags;
    }

    /**
     * @param Tags The Tags
     */
    public void setTags(String Tags) {
        this.Tags = Tags;
    }

    /**
     * @return The Image
     */
    public List<pl.wasat.smarthma.model.osdd.Image> getImage() {
        return Image;
    }

    /**
     * @param Image The Image
     */
    public void setImage(List<pl.wasat.smarthma.model.osdd.Image> Image) {
        this.Image = Image;
    }

    /**
     * @return The Developer
     */
    public String getDeveloper() {
        return Developer;
    }

    /**
     * @param Developer The Developer
     */
    public void setDeveloper(String Developer) {
        this.Developer = Developer;
    }

    /**
     * @return The Attribution
     */
    public String getAttribution() {
        return Attribution;
    }

    /**
     * @param Attribution The Attribution
     */
    public void setAttribution(String Attribution) {
        this.Attribution = Attribution;
    }

    /**
     * @return The SyndicationRight
     */
    public String getSyndicationRight() {
        return SyndicationRight;
    }

    /**
     * @param SyndicationRight The SyndicationRight
     */
    public void setSyndicationRight(String SyndicationRight) {
        this.SyndicationRight = SyndicationRight;
    }

    /**
     * @return The AdultContent
     */
    public String getAdultContent() {
        return AdultContent;
    }

    /**
     * @param AdultContent The AdultContent
     */
    public void setAdultContent(String AdultContent) {
        this.AdultContent = AdultContent;
    }

    /**
     * @return The Language
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * @param Language The Language
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     * @return The OutputEncoding
     */
    public String getOutputEncoding() {
        return OutputEncoding;
    }

    /**
     * @param OutputEncoding The OutputEncoding
     */
    public void setOutputEncoding(String OutputEncoding) {
        this.OutputEncoding = OutputEncoding;
    }

    /**
     * @return The InputEncoding
     */
    public String getInputEncoding() {
        return InputEncoding;
    }

    /**
     * @param InputEncoding The InputEncoding
     */
    public void setInputEncoding(String InputEncoding) {
        this.InputEncoding = InputEncoding;
    }

    /**
     * @return The Xmlns
     */
    public String getXmlns() {
        return Xmlns;
    }

    /**
     * @param Xmlns The _xmlns
     */
    public void setXmlns(String Xmlns) {
        this.Xmlns = Xmlns;
    }

    /**
     * @return The XmlnsDc
     */
    public String getXmlnsDc() {
        return XmlnsDc;
    }

    /**
     * @param XmlnsDc The _xmlns:dc
     */
    public void setXmlnsDc(String XmlnsDc) {
        this.XmlnsDc = XmlnsDc;
    }

    /**
     * @return The XmlnsEo
     */
    public String getXmlnsEo() {
        return XmlnsEo;
    }

    /**
     * @param XmlnsEo The _xmlns:eo
     */
    public void setXmlnsEo(String XmlnsEo) {
        this.XmlnsEo = XmlnsEo;
    }

    /**
     * @return The XmlnsGeo
     */
    public String getXmlnsGeo() {
        return XmlnsGeo;
    }

    /**
     * @param XmlnsGeo The _xmlns:geo
     */
    public void setXmlnsGeo(String XmlnsGeo) {
        this.XmlnsGeo = XmlnsGeo;
    }

    /**
     * @return The XmlnsParam
     */
    public String getXmlnsParam() {
        return XmlnsParam;
    }

    /**
     * @param XmlnsParam The _xmlns:param
     */
    public void setXmlnsParam(String XmlnsParam) {
        this.XmlnsParam = XmlnsParam;
    }

    /**
     * @return The XmlnsSru
     */
    public String getXmlnsSru() {
        return XmlnsSru;
    }

    /**
     * @param XmlnsSru The _xmlns:sru
     */
    public void setXmlnsSru(String XmlnsSru) {
        this.XmlnsSru = XmlnsSru;
    }

    /**
     * @return The XmlnsTime
     */
    public String getXmlnsTime() {
        return XmlnsTime;
    }

    /**
     * @param XmlnsTime The _xmlns:time
     */
    public void setXmlnsTime(String XmlnsTime) {
        this.XmlnsTime = XmlnsTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ShortName).append(LongName).append(Description).append(Url).append(Query).append(Tags).append(Image).append(Developer).append(Attribution).append(SyndicationRight).append(AdultContent).append(Language).append(OutputEncoding).append(InputEncoding).append(Xmlns).append(XmlnsDc).append(XmlnsEo).append(XmlnsGeo).append(XmlnsParam).append(XmlnsSru).append(XmlnsTime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof OpenSearchDescription)) {
            return false;
        }
        OpenSearchDescription rhs = ((OpenSearchDescription) other);
        return new EqualsBuilder().append(ShortName, rhs.ShortName).append(LongName, rhs.LongName).append(Description, rhs.Description).append(Url, rhs.Url).append(Query, rhs.Query).append(Tags, rhs.Tags).append(Image, rhs.Image).append(Developer, rhs.Developer).append(Attribution, rhs.Attribution).append(SyndicationRight, rhs.SyndicationRight).append(AdultContent, rhs.AdultContent).append(Language, rhs.Language).append(OutputEncoding, rhs.OutputEncoding).append(InputEncoding, rhs.InputEncoding).append(Xmlns, rhs.Xmlns).append(XmlnsDc, rhs.XmlnsDc).append(XmlnsEo, rhs.XmlnsEo).append(XmlnsGeo, rhs.XmlnsGeo).append(XmlnsParam, rhs.XmlnsParam).append(XmlnsSru, rhs.XmlnsSru).append(XmlnsTime, rhs.XmlnsTime).isEquals();
    }

}
