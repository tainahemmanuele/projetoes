package com.sugar.collection.collectionsugar.entities;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tainah on 06/08/2017.
 */
public class UserTest {
    User user1 = new User("Tainah", "tainah", "123");
    User user2 = new User("Saulo", "saulo", "abc");

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("Tainah", user1.getName());
        Assert.assertEquals("Saulo", user2.getName());
        Assert.assertNotSame("tainah", user1.getName());

    }

    @Test
    public void setName() throws Exception {
        user1.setName("Tainah Emmanuele");
        Assert.assertEquals("Tainah Emmanuele", user1.getName());
        Assert.assertNotSame("Tainah", user1.getName());

    }

    @Test
    public void getLogin() throws Exception {
        Assert.assertEquals("tainah", user1.getLogin());
        Assert.assertEquals("saulo", user2.getLogin());
        Assert.assertNotSame("Saulo", user2.getLogin());

    }

    @Test
    public void setLogin() throws Exception {
        user1.setLogin("tainahemmanuele");
        Assert.assertEquals("tainahemmanuele", user1.getLogin());
        Assert.assertNotSame("TainahEmmanuele", user1.getLogin());

    }

    @Test
    public void getPassword() throws Exception {
        Assert.assertEquals("123", user1.getPassword());
        Assert.assertEquals("abc", user2.getPassword());
        Assert.assertNotSame("ABC", user2.getPassword());

    }

    @Test
    public void setPassword() throws Exception {
        user1.setPassword("567");
        Assert.assertEquals("567", user1.getPassword());
        user2.setPassword("Abc");
        Assert.assertEquals("Abc", user2.getPassword());
        Assert.assertNotSame("ABC", user2.getPassword());

    }

}