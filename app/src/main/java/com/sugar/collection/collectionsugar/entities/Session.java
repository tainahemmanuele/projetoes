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
public class Session extends SugarRecord {
    int idUser;

    boolean isActive;

    /**
     * Constructor for Item class.
     */
    public Session() {
    }

    public Session(int idUser, boolean isActive) {
        this.idUser = idUser;
        this.isActive = isActive;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
