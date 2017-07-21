package com.sugar.collection.collectionsugar.services;

import android.util.Log;

import com.orm.SugarRecord;
import com.sugar.collection.collectionsugar.entities.User;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */
public final class UserService {

    public static User getUserById(int id) {
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static List<User> findUsersByName(String name) {
        List<User> list = SugarRecord.find(User.class, "name=?", String.valueOf(name));
        Log.i("findUsersByName", list.toArray().toString());
        return list;
    }

    public static List<User> findUsersByLogin(String login) {
        List<User> list = SugarRecord.find(User.class, "login=?", String.valueOf(login));
        Log.i("findUsersByLogin", list.toArray().toString());
        return list;
    }

    public static User checkUserByLogin(String login, String password) {
        List<User> list = SugarRecord.find(User.class, "login=?", String.valueOf(login));
        Log.i("checkUserByLogin", list.toArray().toString());
        if (list.isEmpty()) return null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(login) && list.get(i).getPassword().equals(password)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static boolean saveUser(String name, String login, String password) {
        List<User> list = UserService.findUsersByLogin(login);
        if (list.isEmpty()) {
            User user = new User(name, login, password);
            user.save();
            return true;
        }
        return false;
    }

    public static boolean updateUser(int id, String name, String login, String password) {
        User user = SugarRecord.findById(User.class, id);
        if (user != null) {
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            user.save();
            return true;
        }
        return false;
    }

    public static boolean deleteUser(int id) {
        User user = SugarRecord.findById(User.class, id);
        return user.delete();
    }


    public static List<User> getAllUsers() {
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

    public static int deleteAllUsers() {
        return SugarRecord.deleteAll(User.class);
    }
}
