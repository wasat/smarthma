
package pl.wasat.smarthma.model.opml;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Opml {

    @SerializedName("head")
    @Expose
    private Head head;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * @return The head
     */
    public Head getHead() {
        return head;
    }

    /**
     * @param head The head
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * @return The body
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body The body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
