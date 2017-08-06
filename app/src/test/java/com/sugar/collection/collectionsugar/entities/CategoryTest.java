package com.sugar.collection.collectionsugar.entities;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tainah on 05/08/2017.
 */
public class CategoryTest {
    public static Category category = new Category("CD");
    public static Category category2 = new Category("VINIL");
    public static Category category3 = new Category("LIVRO");

    @Test
    public void getName() throws Exception {
        Assert.assertEquals(category.getName(), "CD");
        Assert.assertNotSame(category.getName(), "cd");

    }

    @Test
    public void setName() throws Exception {
        category2.setName("vinil");
        Assert.assertEquals(category2.getName(), "vinil");
        category3.setName("book");
        Assert.assertNotSame("livro", category3.getName());

    }

}