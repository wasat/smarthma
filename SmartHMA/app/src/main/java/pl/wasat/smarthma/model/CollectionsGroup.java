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

package pl.wasat.smarthma.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Collections group.
 */
public class CollectionsGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String groupName;
    private String standard;
    private ArrayList<Collection> collections;

    /**
     * Instantiates a new Collections group.
     */
    public CollectionsGroup() {
        collections = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets group name.
     *
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets group name.
     *
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public ArrayList<Collection> getCollections() {
        return collections;
    }

    /**
     * Sets collections.
     *
     * @param collections the collections to set
     */
    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
    }

    /**
     * Gets standard.
     *
     * @return the standard
     */
    public String getStandard() {
        return standard;
    }

    /**
     * Sets standard.
     *
     * @param standard the standard to set
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * Add item.
     *
     * @param coll the coll
     */
    public void addItem(Collection coll) {
        collections.add(coll);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((collections == null) ? 0 : collections.hashCode());
        result = prime * result
                + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((standard == null) ? 0 : standard.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CollectionsGroup other = (CollectionsGroup) obj;
        if (collections == null) {
            if (other.collections != null)
                return false;
        } else if (!collections.equals(other.collections))
            return false;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        if (id != other.id)
            return false;
        if (standard == null) {
            if (other.standard != null)
                return false;
        } else if (!standard.equals(other.standard))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CollectionsGroup [groupName=" + groupName + ", standard="
                + standard + ", collections=" + collections + "]";
    }

    /**
     * The type List.
     */
    public static class List {
        private static final long serialVersionUID = 6836514467436078182L;

        private ArrayList<CollectionsGroup> collectionsGroupList = new ArrayList<>();

        /**
         * Gets collections group list.
         *
         * @return the collectionGroupList
         */
        public ArrayList<CollectionsGroup> getCollectionsGroupList() {
            return collectionsGroupList;
        }

        /**
         * Sets collection group list.
         *
         * @param collectionsGroupList the collectionGroupList to set
         */
        public void setCollectionGroupList(
                ArrayList<CollectionsGroup> collectionsGroupList) {
            this.collectionsGroupList = collectionsGroupList;
        }

        /**
         * Add item.
         *
         * @param collGroup the coll group
         */
        public void addItem(CollectionsGroup collGroup) {
            this.collectionsGroupList.add(collGroup);
        }

        /**
         * Gets serialversionuid.
         *
         * @return the serialversionuid
         */
        public static long getSerialversionuid() {
            return serialVersionUID;
        }
    }

}
