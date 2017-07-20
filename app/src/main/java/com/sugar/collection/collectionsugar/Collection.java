/**
 * Created by Caio F M Barbosa.
 *
 * @author Caio F M Barbosa.
 */
package com.sugar.collection.collectionsugar;

import com.orm.SugarRecord;

/**
 * This class represents a Item model.
 */
public class Collection extends SugarRecord {
    /**
     * This is a name of item.
     */
    String name;

    Category category;

    User user;

    /**
     * Constructor for Item class.
     */
    public Collection() {
    }

    public Collection(String name, Category category, User user) {
        this.name = name;
        this.category = category;
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
