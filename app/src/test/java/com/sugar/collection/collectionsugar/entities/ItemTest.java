package com.sugar.collection.collectionsugar.entities;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tainah on 05/08/2017.
 */
public class ItemTest {


    public static Category category = new Category("CD");
    public static Category category2 = new Category("VINIL");
    public static User user = new User("Tainah", "tainah", "123");
    public static Collection collection = new Collection("CD", category, user);
    public static Collection collection2 = new Collection("VINIL", category, user);
    public static Item item = new Item("tropicalia ou panis et circencis", collection, "26/07/2017");
    public static Item item2 = new Item("Tropicalia 2", collection2, "28/08/2016");

    @Test
    public void getCollection() throws Exception {
        Assert.assertEquals(collection, item.getCollection());
        Assert.assertNotSame(collection2, item.getCollection());

    }

    @Test
    public void setCollection() throws Exception {
        item2.setCollection(collection);
        Assert.assertEquals(item.getCollection(), collection);
        item.setCollection(collection2);
        Assert.assertNotSame(collection, item.getCollection());

    }


    @Test
    public void setName() throws Exception {
        item.setName("Tropicalia ou Panis et Circencis");
        Assert.assertEquals(item.getName(), "Tropicalia ou Panis et Circencis");


    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals(item.getName(),"tropicalia ou panis et circencis");
        Assert.assertNotSame(item.getName(),"Tropicalia ou Panis et Circencis");

    }

}