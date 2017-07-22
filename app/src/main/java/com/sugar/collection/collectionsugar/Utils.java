package com.sugar.collection.collectionsugar;

import com.sugar.collection.collectionsugar.services.CategoryService;

/**
 * Created by caiom on 21/07/2017.
 */

public final class Utils {

    public static final String VAZIO = "";
    public static final int ZERO = 0;
    public static final int PASSWORD_MIN = 5;
    public static final int LOGIN_MIN = 5;


    public static void generateCategoryDefault() {
        if (CategoryService.getCategoryById(1) == null) {
            CategoryService.saveCategory("Categoria 1");
            CategoryService.saveCategory("Categoria 2");
            CategoryService.saveCategory("Categoria 3");
        }
    }
}
