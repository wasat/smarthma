
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

/**
 * The type Opml.
 */
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
     * Gets head.
     *
     * @return The head
     */
    public Head getHead() {
        return head;
    }

    /**
     * Sets head.
     *
     * @param head The head
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * Gets body.
     *
     * @return The body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body The body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Gets version.
     *
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
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
