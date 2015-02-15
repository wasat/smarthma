package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Name implements Serializable {

    private static final long serialVersionUID = 1L;
    private String _text;
    private String _xmlns;
    private String codeSpace;


    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public Name with_text(String _text) {
        this._text = _text;
        return this;
    }

    public String get_xmlns() {
        return _xmlns;
    }

    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public Name with_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
        return this;
    }

    public String getcodeSpace() {
        return codeSpace;
    }

    public void setcodeSpace(String codeSpace) {
        this.codeSpace = codeSpace;
    }

    public Name withcodeSpace(String codeSpace) {
        this.codeSpace = codeSpace;
        return this;
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

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }


}
