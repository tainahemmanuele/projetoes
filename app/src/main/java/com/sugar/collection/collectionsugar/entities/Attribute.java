/**
 * Created by Caio F M Barbosa.
 *
 * @author Caio F M Barbosa.
 */
package com.sugar.collection.collectionsugar.entities;

import com.orm.SugarRecord;

/**
 * This class represents a Category model.
 */
public class Attribute extends SugarRecord {
    /**
     * This is a name of item.
     * <p>
     * This name represents the category for any collection.
     */
    String name;

    /**
     * Item model for one-to-many relationship.
     */
    Item item;

    /**
     * A String formated value for the Attribute.
     */
    String value;

    /**
     * Constructor for Item class.
     * <p>
     * This is important for Sugar Record Model, by th docs in the blog.
     */
    public Attribute() {
    }

    /**
     * Other constructor for item class.
     *
     * @param name A String name of item.
     */
    public Attribute(String name, Item item, String value) {
        this.name = name;
        this.item = item;
        this.value = value;
    }

    /**
     * This method get a name of item.
     *
     * @return A String name of item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method set a name of item.
     *
     * @param name The new name of item in String format.
     */
    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
