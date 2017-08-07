package com.sugar.collection.collectionsugar.entities;

import com.orm.SugarRecord;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tainah on 05/08/2017.
 */
public class CollectionTest {
    public static Category category = new Category("CD");
    public static Category category2 = new Category("VINIL");
    public static User user = new User("Tainah", "tainah", "123");
    public static Collection collection = new Collection("CD", category, user);
    public static Collection collection2 = new Collection("VINIL", category, user);
    public static User user2 = new User("Saulo","saulo", "567");

    @Test
    public void getCategory() throws Exception {
        Assert.assertEquals("CD", collection.getCategory().getName());
        Assert.assertEquals(category, collection.getCategory());

    }

    @Test
    public void setCategory() throws Exception {
        collection.setCategory(category2);
        Assert.assertEquals(category2,collection.getCategory());
        Assert.assertNotSame(category,collection.getCategory());
        collection.setCategory(category);

    }

    @Test
    public void getUser() throws Exception {
        Assert.assertEquals(user,collection.getUser());

    }

    @Test
    public void setUser() throws Exception {
        collection2.setUser(user2);
        Assert.assertEquals(user2, collection2.getUser());
        Assert.assertNotSame( user,collection2.getUser());

    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("CD", collection.getName());
        Assert.assertNotSame("CD",collection2.getName());


    }

    @Test
    public void setName() throws Exception {
        collection.setName("VINIL");
        Assert.assertEquals("VINIL", collection.getName());
        Assert.assertNotSame("CD",collection.getName());

    }

}