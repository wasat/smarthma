
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

import java.util.ArrayList;
import java.util.List;

/**
 * The type Body.
 */
public class Body {

    @SerializedName("outline")
    @Expose
    private List<Outline> outline = new ArrayList<>();

    /**
     * Gets outline.
     *
     * @return The outline
     */
    public List<Outline> getOutline() {
        return outline;
    }

    /**
     * Sets outline.
     *
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
