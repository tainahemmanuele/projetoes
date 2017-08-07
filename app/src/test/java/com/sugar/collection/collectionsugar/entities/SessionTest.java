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

    /*      Testes de Unidade        */

    @Test
    public void getIdUser() throws Exception {
        Assert.assertEquals(1234,session.getIdUser());
        Assert.assertNotSame("1235",session2.getIdUser());

    }

    @Test
    public void setIdUser() throws Exception {
        session.setIdUser(1212);
        Assert.assertEquals(1212,session.getIdUser());

    }

    @Test
    public void isActive() throws Exception {
        Assert.assertTrue(session.isActive);
        Assert.assertTrue(session2.isActive);
        Assert.assertNotSame(false,session.isActive);

    }

    @Test
    public void setActive() throws Exception {
        session.setActive(false);
        Assert.assertFalse(session.isActive);
        Assert.assertNotSame("false",session.isActive);

    }

}