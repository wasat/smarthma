
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

package pl.wasat.smarthma.model.opml;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * The type Head.
 */
public class Head {

    /**
     * Instantiates a new Head.
     */
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
     * Gets title.
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets date created.
     *
     * @return The dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated The dateCreated
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets date modified.
     *
     * @return The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * Sets date modified.
     *
     * @param dateModified The dateModified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * Gets owner name.
     *
     * @return The ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets owner name.
     *
     * @param ownerName The ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets owner email.
     *
     * @return The ownerEmail
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets owner email.
     *
     * @param ownerEmail The ownerEmail
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * Gets expansion state.
     *
     * @return The expansionState
     */
    public String getExpansionState() {
        return expansionState;
    }

    /**
     * Sets expansion state.
     *
     * @param expansionState The expansionState
     */
    public void setExpansionState(String expansionState) {
        this.expansionState = expansionState;
    }

    /**
     * Gets vert scroll state.
     *
     * @return The vertScrollState
     */
    public String getVertScrollState() {
        return vertScrollState;
    }

    /**
     * Sets vert scroll state.
     *
     * @param vertScrollState The vertScrollState
     */
    public void setVertScrollState(String vertScrollState) {
        this.vertScrollState = vertScrollState;
    }

    /**
     * Gets window top.
     *
     * @return The windowTop
     */
    public String getWindowTop() {
        return windowTop;
    }

    /**
     * Sets window top.
     *
     * @param windowTop The windowTop
     */
    public void setWindowTop(String windowTop) {
        this.windowTop = windowTop;
    }

    /**
     * Gets window left.
     *
     * @return The windowLeft
     */
    public String getWindowLeft() {
        return windowLeft;
    }

    /**
     * Sets window left.
     *
     * @param windowLeft The windowLeft
     */
    public void setWindowLeft(String windowLeft) {
        this.windowLeft = windowLeft;
    }

    /**
     * Gets window bottom.
     *
     * @return The windowBottom
     */
    public String getWindowBottom() {
        return windowBottom;
    }

    /**
     * Sets window bottom.
     *
     * @param windowBottom The windowBottom
     */
    public void setWindowBottom(String windowBottom) {
        this.windowBottom = windowBottom;
    }

    /**
     * Gets window right.
     *
     * @return The windowRight
     */
    public String getWindowRight() {
        return windowRight;
    }

    /**
     * Sets window right.
     *
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
