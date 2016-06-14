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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

/**
 * The type Multi point.
 */
public class MultiPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private List<PointMember> pointMember = new ArrayList<>();
    private String _gml_id;
    private String _srsName;


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
     * Gets point member.
     *
     * @return the point member
     */
    public List<PointMember> getPointMember() {
        return pointMember;
    }

    /**
     * Sets point member.
     *
     * @param pointMember the point member
     */
    public void setPointMember(List<PointMember> pointMember) {
        this.pointMember = pointMember;
    }


    /**
     * Gets gml id.
     *
     * @return the gml id
     */
    public String get_gml_id() {
        return _gml_id;
    }

    /**
     * Sets gml id.
     *
     * @param _gml_id the gml id
     */
    public void set_gml_id(String _gml_id) {
        this._gml_id = _gml_id;
    }

    /**
     * Gets srs name.
     *
     * @return the srs name
     */
    public String get_srsName() {
        return _srsName;
    }

    /**
     * Sets srs name.
     *
     * @param _srsName the srs name
     */
    public void set_srsName(String _srsName) {
        this._srsName = _srsName;
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


}
