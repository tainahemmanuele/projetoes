package com.sugar.collection.collectionsugar.services;

import com.orm.SugarRecord;
import com.sugar.collection.collectionsugar.entities.Session;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class SessionService {

    public static Session getSessionById(int id) {
        Session session = SugarRecord.findById(Session.class, id);
        return session;
    }

    public static long saveSession(int idUser, boolean isActive) {
        Session session = new Session(idUser, isActive);
        return session.save();
    }

    public static long updateSession(int id, int idUser, boolean isActive) {
        Session session = SugarRecord.findById(Session.class, id);
        session.setActive(isActive);
        session.setIdUser(idUser);
        return session.save();
    }

    public static boolean deleteSession(int id) {
        Session session = SugarRecord.findById(Session.class, id);
        return session.delete();
    }


    public static List<Session> getAllSessions() {
        List<Session> sessions = SugarRecord.listAll(Session.class);
        return sessions;
    }

    public static int deleteAllSessions() {
        return SugarRecord.deleteAll(Session.class);
    }
}
