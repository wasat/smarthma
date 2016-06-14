
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

package pl.wasat.smarthma.model.osdd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The type Image.
 */
public class Image {

    private String Height;
    private String Type;
    private String Width;
    private String Text;


    /**
     * Gets height.
     *
     * @return The Height
     */
    public String getHeight() {
        return Height;
    }

    /**
     * Sets height.
     *
     * @param Height The _height
     */
    public void setHeight(String Height) {
        this.Height = Height;
    }

    /**
     * Gets type.
     *
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * Sets type.
     *
     * @param Type The _type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * Gets width.
     *
     * @return The Width
     */
    public String getWidth() {
        return Width;
    }

    /**
     * Sets width.
     *
     * @param Width The _width
     */
    public void setWidth(String Width) {
        this.Width = Width;
    }

    /**
     * Gets text.
     *
     * @return The Text
     */
    public String getText() {
        return Text;
    }

    /**
     * Sets text.
     *
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
