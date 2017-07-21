package com.sugar.collection.collectionsugar.services;

import com.sugar.collection.collectionsugar.entities.Session;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class SessionService {

    public static Session getSessionById(int id) {
        Session session = Session.findById(Session.class, id);
        return session;
    }

    public static long saveSession(int idUser, boolean isActive) {
        Session session = new Session(idUser, isActive);
        return session.save();
    }

    public static long updateSession(int id, int idUser, boolean isActive) {
        Session session = Session.findById(Session.class, id);
        session.setActive(isActive);
        session.setIdUser(idUser);
        return session.save();
    }

    public static boolean deleteSession(int id) {
        Session session = Session.findById(Session.class, id);
        return session.delete();
    }


    public static List<Session> getAllSessions() {
        List<Session> sessions = Session.listAll(Session.class);
        return sessions;
    }

    public static int deleteAllSessions() {
        return Session.deleteAll(Session.class);
    }
}
