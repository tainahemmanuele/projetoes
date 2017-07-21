/**
 * Created by Caio F M Barbosa.
 *
 * @author Caio F M Barbosa.
 */
package com.sugar.collection.collectionsugar.entities;

import com.orm.SugarRecord;

/**
 * This class represents a Collection model.
 */
public class Collection extends SugarRecord {
    /**
     * This is a name of item.
     */
    String name;

    /**
     * Category of Collection.
     */
    Category category;

    /**
     * Collection owner.
     */
    User user;

    /**
     * Constructor for Collection class.
     */
    public Collection() {
    }

    /**
     * Complete Constructor for Collection class.
     */
    public Collection(String name, Category category, User user) {
        this.name = name;
        this.category = category;
        this.user = user;
    }

    /**
     * This method get the category.
     *
     * @return The Category Model.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * This method set the category model of collection.
     *
     * @param category A Category Model created by user.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * This method get de User.
     *
     * @return A User model.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * This method set the user model, owner of collection.
     *
     * @param user A User Model.
     */
    public void setUser(User user) {
        this.user = user;
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
