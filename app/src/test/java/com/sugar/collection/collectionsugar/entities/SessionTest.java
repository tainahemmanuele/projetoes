package com.sugar.collection.collectionsugar.entities;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tainah on 06/08/2017.
 */
public class SessionTest {
    Session session = new Session(1234, true);
    Session session2 = new Session(1235, true);

    @Test
    public void getIdUser() throws Exception {
        Assert.assertEquals(session.getIdUser(),1234);
        Assert.assertNotSame(session2.getIdUser(),"1235");

    }

    @Test
    public void setIdUser() throws Exception {
        session.setIdUser(1212);
        Assert.assertEquals(session.getIdUser(),1212);

    }

    @Test
    public void isActive() throws Exception {
        Assert.assertEquals(session.isActive, true);
        Assert.assertEquals(session2.isActive,true);
        Assert.assertNotSame(session.isActive,false);

    }

    @Test
    public void setActive() throws Exception {
        session.setActive(false);
        Assert.assertEquals(session.isActive,false);
        Assert.assertNotSame(session.isActive,"false");

    }

}