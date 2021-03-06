package com.sugar.collection.collectionsugar.services;

import com.orm.SugarRecord;
import com.sugar.collection.collectionsugar.entities.Attribute;
import com.sugar.collection.collectionsugar.entities.Item;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class AttributeService {

    public static Attribute getAttributeById(int id) {
        Attribute attribute = SugarRecord.findById(Attribute.class, id);
        return attribute;
    }

    public static long saveAttribute(String name, Item item, String value) {
        Attribute attribute = new Attribute(name, item, value);
        return attribute.save();
    }

    public static long updateAttribute(int id, String name, Item item, String value) {
        Attribute attribute = SugarRecord.findById(Attribute.class, id);
        attribute.setName(name);
        attribute.setItem(item);
        attribute.setValue(value);
        return attribute.save();
    }

    public static boolean deleteAttribute(int id) {
        Attribute attribute = SugarRecord.findById(Attribute.class, id);
        return attribute.delete();
    }


    public static List<Attribute> getAllAttributes() {
        List<Attribute> attributes = SugarRecord.listAll(Attribute.class);
        return attributes;
    }

    public static int deleteAllAttributes() {
        return SugarRecord.deleteAll(Attribute.class);
    }
}
