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
        Assert.assertEquals(user1.getName(),"Tainah");
        Assert.assertEquals(user2.getName(), "Saulo");
        Assert.assertNotSame(user1.getName(), "tainah");

    }

    @Test
    public void setName() throws Exception {
        user1.setName("Tainah Emmanuele");
        Assert.assertEquals(user1.getName(),"Tainah Emmanuele");
        Assert.assertNotSame(user1.getName(),"Tainah");

    }

    @Test
    public void getLogin() throws Exception {
        Assert.assertEquals(user1.getLogin(),"tainah");
        Assert.assertEquals(user2.getLogin(),"saulo");
        Assert.assertNotSame(user2.getLogin(),"Saulo");

    }

    @Test
    public void setLogin() throws Exception {
        user1.setLogin("tainahemmanuele");
        Assert.assertEquals(user1.getLogin(), "tainahemmanuele");
        Assert.assertNotSame(user1.getLogin(), "TainahEmmanuele");

    }

    @Test
    public void getPassword() throws Exception {
        Assert.assertEquals(user1.getPassword(), "123");
        Assert.assertEquals(user2.getPassword(),"abc");
        Assert.assertNotSame(user2.getPassword(),"ABC");

    }

    @Test
    public void setPassword() throws Exception {
        user1.setPassword("567");
        Assert.assertEquals(user1.getPassword(), "567");
        user2.setPassword("Abc");
        Assert.assertEquals(user2.getPassword(),"Abc");
        Assert.assertNotSame(user2.getPassword(),"ABC");

    }

}