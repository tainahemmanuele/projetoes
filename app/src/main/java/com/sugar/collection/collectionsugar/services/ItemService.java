package com.sugar.collection.collectionsugar.services;

import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.entities.Item;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class ItemService {

    public static Item getItemById(int id) {
        Item item = Item.findById(Item.class, id);
        return item;
    }

    public static long saveItem(String name, Collection collection, String value, String created_at) {
        Item item = new Item(name, collection, value, created_at);
        return item.save();
    }

    public static long updateItem(int id, String name, Collection collection, String value, String created_at) {
        Item item = Item.findById(Item.class, id);
        item.setName(name);
        item.setCollection(collection);
        item.setCreated_at(created_at);
        item.setValue(value);
        return item.save();
    }

    public static boolean deleteItem(int id) {
        Item item = Item.findById(Item.class, id);
        return item.delete();
    }


    public static List<Item> getAllItems() {
        List<Item> items = Item.listAll(Item.class);
        return items;
    }

    public static int deleteAllItems() {
        return Item.deleteAll(Item.class);
    }
}
