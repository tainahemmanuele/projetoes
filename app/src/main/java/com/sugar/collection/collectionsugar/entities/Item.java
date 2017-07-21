/**
 * Created by Caio F M Barbosa.
 *
 * @author Caio F M Barbosa.
 */
package com.sugar.collection.collectionsugar.entities;

import com.orm.SugarRecord;

/**
 * This class represents a Item model.
 */
public class Item extends SugarRecord {
    /**
     * This is a name of item.
     */
    String name;

    Collection collection;

    String value;

    String created_at;

    /**
     * Constructor for Item class.
     */
    public Item() {
    }

    public Item(String name, Collection collection, String value, String created_at) {
        this.name = name;
        this.collection = collection;
        this.value = value;
        this.created_at = created_at;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * This method get a name of item.
     *
     * @return A String name of item.
     */
    public String getName() {
        return name;
    }

    /**
     * This method set a name of item.
     *
     * @param name The new name of item in String format.
     */
    public void setName(String name) {
        this.name = name;
    }
}
