
package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Image {

    private String Height;
    private String Type;
    private String Width;
    private String Text;


    /**
     * @return The Height
     */
    public String getHeight() {
        return Height;
    }

    /**
     * @param Height The _height
     */
    public void setHeight(String Height) {
        this.Height = Height;
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

    /**
     * @return The Width
     */
    public String getWidth() {
        return Width;
    }

    /**
     * @param Width The _width
     */
    public void setWidth(String Width) {
        this.Width = Width;
    }

    /**
     * @return The Text
     */
    public String getText() {
        return Text;
    }

    /**
     * @param Text The __text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Height).append(Type).append(Width).append(Text).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Image)) {
            return false;
        }
        Image rhs = ((Image) other);
        return new EqualsBuilder().append(Height, rhs.Height).append(Type, rhs.Type).append(Width, rhs.Width).append(Text, rhs.Text).isEquals();
    }

}
