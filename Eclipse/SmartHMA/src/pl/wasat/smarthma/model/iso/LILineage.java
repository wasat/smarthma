package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class LILineage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Statement statement;
    private String Prefix;


    /**
     * @return The statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * @param statement The statement
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
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
        return new HashCodeBuilder().append(statement).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LILineage)) {
            return false;
        }
        LILineage rhs = ((LILineage) other);
        return new EqualsBuilder().append(statement, rhs.statement)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
