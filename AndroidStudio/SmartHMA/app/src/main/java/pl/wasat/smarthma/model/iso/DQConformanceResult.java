package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DQConformanceResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Specification specification;
    private Explanation explanation;
    private Pass pass;
    private String Prefix;


    /**
     * @return The specification
     */
    public Specification getSpecification() {
        return specification;
    }

    /**
     * @param specification The specification
     */
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    /**
     * @return The explanation
     */
    public Explanation getExplanation() {
        return explanation;
    }

    /**
     * @param explanation The explanation
     */
    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    /**
     * @return The pass
     */
    public Pass getPass() {
        return pass;
    }

    /**
     * @param pass The pass
     */
    public void setPass(Pass pass) {
        this.pass = pass;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The _prefix
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
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
