
package pl.wasat.smarthma.model.eo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;


public class MaskInformation {

    private String __prefix;
    private Type type;
    private SubType subType;
    private Format format;
    private ReferenceSystemIdentifier referenceSystemIdentifier;
    private MultiExtentOf multiExtentOf;
        private FileName fileName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get__prefix() {
        return __prefix;
    }

    public void set__prefix(String __prefix) {
        this.__prefix = __prefix;
    }

    public MaskInformation with__prefix(String __prefix) {
        this.__prefix = __prefix;
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public MaskInformation withType(Type type) {
        this.type = type;
        return this;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public MaskInformation withSubType(SubType subType) {
        this.subType = subType;
        return this;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public MaskInformation withFormat(Format format) {
        this.format = format;
        return this;
    }

    public ReferenceSystemIdentifier getReferenceSystemIdentifier() {
        return referenceSystemIdentifier;
    }

    public void setReferenceSystemIdentifier(ReferenceSystemIdentifier referenceSystemIdentifier) {
        this.referenceSystemIdentifier = referenceSystemIdentifier;
    }

    public MaskInformation withReferenceSystemIdentifier(ReferenceSystemIdentifier referenceSystemIdentifier) {
        this.referenceSystemIdentifier = referenceSystemIdentifier;
        return this;
    }

    public MultiExtentOf getMultiExtentOf() {
        return multiExtentOf;
    }

    public void setMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
    }

    public MaskInformation withMultiExtentOf(MultiExtentOf multiExtentOf) {
        this.multiExtentOf = multiExtentOf;
        return this;
    }

    public FileName getFileName() {
        return fileName;
    }

    public void setFileName(FileName fileName) {
        this.fileName = fileName;
    }

    public MaskInformation withFileName(FileName fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public String toString() {
            	 ToStringStyle style = new SmartHMAStringStyle(); ToStringBuilder.setDefaultStyle(style); return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
