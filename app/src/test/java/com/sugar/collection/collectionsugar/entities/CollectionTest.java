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
        Assert.assertEquals(collection.getCategory().getName(), "CD");
        Assert.assertEquals(collection.getCategory(), category);

    }

    @Test
    public void setCategory() throws Exception {
        collection.setCategory(category2);
        Assert.assertEquals(collection.getCategory(),category2);
        Assert.assertNotSame(collection.getCategory(),category);
        collection.setCategory(category);

    }

    @Test
    public void getUser() throws Exception {
        Assert.assertEquals(collection.getUser(),user);

    }

    @Test
    public void setUser() throws Exception {
        collection2.setUser(user2);
        Assert.assertEquals(collection2.getUser(), user2);
        Assert.assertNotSame(collection2.getUser(), user);

    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals(collection.getName(), "CD");
        Assert.assertNotSame(collection2.getName(),"CD");


    }

    @Test
    public void setName() throws Exception {
        collection.setName("VINIL");
        Assert.assertEquals(collection.getName(), "VINIL");
        Assert.assertNotSame(collection.getName(),"CD");

    }

}