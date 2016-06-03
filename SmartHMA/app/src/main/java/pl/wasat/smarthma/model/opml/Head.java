
package pl.wasat.smarthma.model.opml;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.wasat.smarthma.utils.time.DateUtils;

public class Head {

    public Head() {
        invoke();
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("ownerEmail")
    @Expose
    private String ownerEmail;
    @SerializedName("expansionState")
    @Expose
    private String expansionState;
    @SerializedName("vertScrollState")
    @Expose
    private String vertScrollState;
    @SerializedName("windowTop")
    @Expose
    private String windowTop;
    @SerializedName("windowLeft")
    @Expose
    private String windowLeft;
    @SerializedName("windowBottom")
    @Expose
    private String windowBottom;
    @SerializedName("windowRight")
    @Expose
    private String windowRight;

    private void invoke() {
        this.dateCreated = DateUtils.timestampToDateTimeStr(System.currentTimeMillis());
        this.dateModified = DateUtils.timestampToDateTimeStr(System.currentTimeMillis());
        this.expansionState = "";
        this.ownerEmail = "smarthma@wasat.pl";
        this.ownerName = "SmartHMA - FEDEO Clearinghouse";
        this.title = "SmartHMA EO products OPML";
        this.vertScrollState = "1";
        this.windowBottom = "0";
        this.windowLeft = "0";
        this.windowRight = "0";
        this.windowTop = "0";
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated The dateCreated
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * @param dateModified The dateModified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * @return The ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName The ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return The ownerEmail
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * @param ownerEmail The ownerEmail
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * @return The expansionState
     */
    public String getExpansionState() {
        return expansionState;
    }

    /**
     * @param expansionState The expansionState
     */
    public void setExpansionState(String expansionState) {
        this.expansionState = expansionState;
    }

    /**
     * @return The vertScrollState
     */
    public String getVertScrollState() {
        return vertScrollState;
    }

    /**
     * @param vertScrollState The vertScrollState
     */
    public void setVertScrollState(String vertScrollState) {
        this.vertScrollState = vertScrollState;
    }

    /**
     * @return The windowTop
     */
    public String getWindowTop() {
        return windowTop;
    }

    /**
     * @param windowTop The windowTop
     */
    public void setWindowTop(String windowTop) {
        this.windowTop = windowTop;
    }

    /**
     * @return The windowLeft
     */
    public String getWindowLeft() {
        return windowLeft;
    }

    /**
     * @param windowLeft The windowLeft
     */
    public void setWindowLeft(String windowLeft) {
        this.windowLeft = windowLeft;
    }

    /**
     * @return The windowBottom
     */
    public String getWindowBottom() {
        return windowBottom;
    }

    /**
     * @param windowBottom The windowBottom
     */
    public void setWindowBottom(String windowBottom) {
        this.windowBottom = windowBottom;
    }

    /**
     * @return The windowRight
     */
    public String getWindowRight() {
        return windowRight;
    }

    /**
     * @param windowRight The windowRight
     */
    public void setWindowRight(String windowRight) {
        this.windowRight = windowRight;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
