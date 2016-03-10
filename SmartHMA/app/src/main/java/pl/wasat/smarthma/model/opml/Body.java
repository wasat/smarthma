
package pl.wasat.smarthma.model.opml;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Body {

    @SerializedName("outline")
    @Expose
    private List<Outline> outline = new ArrayList<Outline>();

    /**
     * @return The outline
     */
    public List<Outline> getOutline() {
        return outline;
    }

    /**
     * @param outline The outline
     */
    public void setOutline(List<Outline> outline) {
        this.outline = outline;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
