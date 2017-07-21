package com.sugar.collection.collectionsugar.services;

import com.sugar.collection.collectionsugar.entities.User;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class UserService {

    public static User getUserById(int id) {
        User user = User.findById(User.class, id);
        return user;
    }

    public static List<User> findUsersByName(String name) {
        List<User> list = User.find(User.class, "name=?", new String[]{String.valueOf(name)}, null, null, null);
        if (list.isEmpty()) return null;
        return list;
    }

    public static List<User> findUsersByLogin(String login) {
        List<User> list = User.find(User.class, "login=?", new String[]{String.valueOf(login)}, null, null, null);
        if (list.isEmpty()) return null;
        return list;
    }

    public static User checkUserByLogin(String login, String password) {
        List<User> list = User.find(User.class, "login=?", new String[]{String.valueOf(login)}, null, null, null);
        if (list.isEmpty()) return null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(login) && list.get(i).getPassword().equals(password)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static boolean saveUser(String name, String login, String password) {
        if (findUsersByLogin(login).isEmpty()) {
            User user = new User(name, login, password);
            user.save();
            return true;
        }
        return false;
    }

    public static boolean updateUser(int id, String name, String login, String password) {
        User user = User.findById(User.class, id);
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
        User user = User.findById(User.class, id);
        return user.delete();
    }


    public static List<User> getAllUsers() {
        List<User> users = User.listAll(User.class);
        return users;
    }

    public static int deleteAllUsers() {
        return User.deleteAll(User.class);
    }
}
