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
public class Category extends SugarRecord {
    /**
     * This is a name of item.
     * <p>
     * This name represents the category for any collection.
     */
    String name;

    /**
     * Constructor for Item class.
     * <p>
     * This is important for Sugar Record Model, by th docs in the blog.
     */
    public Category() {
    }

    /**
     * Other constructor for item class.
     *
     * @param name A String name of item.
     */
    public Category(String name) {
        this.name = name;
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
}
