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

public class Pos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private String _text;
    private transient LatLngExt latLng;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }


    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        _text = _text.replaceAll(",", " ");
        this._text = _text;
        toLatLng();
    }


    public LatLngExt getLatLng() {
        return latLng;
    }

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
