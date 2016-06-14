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

package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Pos.
 */
public class Pos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private String _text;
    private transient LatLngExt latLng;


    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String get_prefix() {
        return _prefix;
    }

    /**
     * Sets prefix.
     *
     * @param _prefix the prefix
     */
    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    /**
     * Gets text.
     *
     * @return the text
     */
    public String get_text() {
        return _text;
    }

    /**
     * Sets text.
     *
     * @param _text the text
     */
    public void set_text(String _text) {
        _text = _text.replaceAll(",", " ");
        this._text = _text;
        toLatLng();
    }


    /**
     * Gets lat lng.
     *
     * @return the lat lng
     */
    public LatLngExt getLatLng() {
        return latLng;
    }

    /**
     * Sets lat lng.
     *
     * @param latLng the lat lng
     */
    public void setLatLng(LatLngExt latLng) {
        this.latLng = latLng;
        this._text = latLng.latitude + " " + latLng.longitude;

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


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(latLng.latitude);
        out.writeDouble(latLng.longitude);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        latLng = new LatLngExt(in.readDouble(), in.readDouble());
    }

    private void toLatLng() {
        latLng = new LatLngExt(Double.valueOf(_text.split(" ")[0]),
                Double.valueOf(_text.split(" ")[1]));
    }
}
