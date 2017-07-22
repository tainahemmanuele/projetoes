package com.sugar.collection.collectionsugar.services;

import com.orm.SugarRecord;
import com.sugar.collection.collectionsugar.entities.Category;

import java.util.List;

/**
 * Created by caiom on 20/07/2017.
 */

public final class CategoryService {

    public static Category getCategoryById(int id) {
        Category category = SugarRecord.findById(Category.class, id);
        return category;
    }

    public static Category findCategoryByName(String name) {
        List<Category> list = SugarRecord.find(Category.class, "name=?", String.valueOf(name));
        return list.get(0);
    }

    public static long saveCategory(String name) {
        Category category = new Category(name);
        return category.save();
    }

    public static long updateCategory(int id, String name) {
        Category category = SugarRecord.findById(Category.class, id);
        category.setName(name);
        return category.save();
    }

    public static boolean deleteCategory(int id) {
        Category category = SugarRecord.findById(Category.class, id);
        return category.delete();
    }


    public static List<Category> getAllCategories() {
        List<Category> categories = SugarRecord.listAll(Category.class);
        return categories;
    }

    public static int deleteAllCategorys() {
        return SugarRecord.deleteAll(Category.class);
    }
}
