package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


public class Url {

    private String Rel;
    private String Template;
    private String Type;
    private String IndexOffset;
    private String PageOffset;
    private List<Parameter> Parameters = new ArrayList<>();


    /**
     * @return The Rel
     */
    public String getRel() {
        return Rel;
    }

    /**
     * @param Rel The _rel
     */
    public void setRel(String Rel) {
        this.Rel = Rel;
    }

    /**
     * @return The Template
     */
    public String getTemplate() {
        return Template;
    }

    /**
     * @param Template The _template
     */
    public void setTemplate(String Template) {
        this.Template = Template;
    }

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The _type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    public String getIndexOffset() {
        return IndexOffset;
    }

    public void setIndexOffset(String indexOffset) {
        IndexOffset = indexOffset;
    }

    public String getPageOffset() {
        return PageOffset;
    }

    public void setPageOffset(String pageOffset) {
        PageOffset = pageOffset;
    }

    /**
     * @return The Parameter
     */
    public List<Parameter> getParameters() {
        return Parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        Parameters = parameters;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Rel).append(Template).append(Type).append(IndexOffset).append(PageOffset).append(Parameters).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Url)) {
            return false;
        }
        Url rhs = ((Url) other);
        return new EqualsBuilder().append(Rel, rhs.Rel).append(Template, rhs.Template).append(Type, rhs.Type).append(IndexOffset, rhs.IndexOffset).append(PageOffset, rhs.PageOffset).append(Parameters, rhs.Parameters).isEquals();
    }

}
