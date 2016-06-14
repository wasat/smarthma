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

import java.util.ArrayList;
import java.util.List;


/**
 * The type Url.
 */
public class Url {

    private String Rel;
    private String Template;
    private String Type;
    private String IndexOffset;
    private String PageOffset;
    private List<Parameter> Parameters = new ArrayList<>();


    /**
     * Gets rel.
     *
     * @return The Rel
     */
    public String getRel() {
        return Rel;
    }

    /**
     * Sets rel.
     *
     * @param Rel The _rel
     */
    public void setRel(String Rel) {
        this.Rel = Rel;
    }

    /**
     * Gets template.
     *
     * @return The Template
     */
    public String getTemplate() {
        return Template;
    }

    /**
     * Sets template.
     *
     * @param Template The _template
     */
    public void setTemplate(String Template) {
        this.Template = Template;
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
     * Gets index offset.
     *
     * @return the index offset
     */
    public String getIndexOffset() {
        return IndexOffset;
    }

    /**
     * Sets index offset.
     *
     * @param indexOffset the index offset
     */
    public void setIndexOffset(String indexOffset) {
        IndexOffset = indexOffset;
    }

    /**
     * Gets page offset.
     *
     * @return the page offset
     */
    public String getPageOffset() {
        return PageOffset;
    }

    /**
     * Sets page offset.
     *
     * @param pageOffset the page offset
     */
    public void setPageOffset(String pageOffset) {
        PageOffset = pageOffset;
    }

    /**
     * Gets parameters.
     *
     * @return The Parameter
     */
    public List<Parameter> getParameters() {
        return Parameters;
    }

    /**
     * Sets parameters.
     *
     * @param parameters the parameters
     */
    public void setParameters(List<Parameter> parameters) {
        Parameters = parameters;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Rel).append(Template).append(Type).append(IndexOffset).append(PageOffset).append(Parameters).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Url)) {
            return false;
        }
        Url rhs = ((Url) other);
        return new EqualsBuilder().append(Rel, rhs.Rel).append(Template, rhs.Template).append(Type, rhs.Type).append(IndexOffset, rhs.IndexOffset).append(PageOffset, rhs.PageOffset).append(Parameters, rhs.Parameters).isEquals();
    }

}
