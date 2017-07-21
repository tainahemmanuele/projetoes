package com.sugar.collection.collectionsugar.services;

import com.orm.SugarRecord;
import com.sugar.collection.collectionsugar.entities.Category;
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.entities.User;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class CollectionService {

    public static Collection getCollectionById(int id) {
        Collection collection = SugarRecord.findById(Collection.class, id);
        return collection;
    }

    public static long saveCollection(String name, Category category, User user) {
        Collection collection = new Collection(name, category, user);
        return collection.save();
    }

    public static long updateCollection(int id, String name, Category category, User user) {
        Collection collection = SugarRecord.findById(Collection.class, id);
        collection.setCategory(category);
        collection.setName(name);
        collection.setUser(user);
        return collection.save();
    }

    public static boolean deleteCollection(int id) {
        Collection collection = SugarRecord.findById(Collection.class, id);
        return collection.delete();
    }


    public static List<Collection> getAllCollections() {
        List<Collection> collections = SugarRecord.listAll(Collection.class);
        return collections;
    }

    public static List<Collection> getCollectionsByUser(int idUser) {
        List<Collection> collections = SugarRecord.find(Collection.class, "user = ?",
                String.valueOf(idUser));
        return collections;
    }


    public static int deleteAllCollections() {
        return SugarRecord.deleteAll(Collection.class);
    }
}
