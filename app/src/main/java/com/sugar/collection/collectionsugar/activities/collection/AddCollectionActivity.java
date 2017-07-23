package com.sugar.collection.collectionsugar.activities.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.activities.MainActivity;
import com.sugar.collection.collectionsugar.entities.Category;
import com.sugar.collection.collectionsugar.services.CategoryService;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.SettingsService;

import java.util.ArrayList;
import java.util.List;

public class AddCollectionActivity extends AppCompatActivity {
    /**
     * New name of collection.
     */
    private EditText nameCollection;
    /**
     * Choose category for the new collection.
     */
    private Spinner categoryCollection;
    /**
     * Button submit form.
     */
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);
        // get all categories in the app.
        // TODO: filter just categories created by user.
        List<String> categories = getCategoriesByListModel(CategoryService.getAllCategories());
        nameCollection = (EditText) findViewById(R.id.name_collection);
        categoryCollection = (Spinner) findViewById(R.id.category_collection);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryCollection.setAdapter(dataAdapter);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (saveCollection()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .create_collection_success, Toast.LENGTH_LONG);
                    toast.show();
                    goToMainActivity();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .create_collection_error, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    /**
     * Navigate to Main Activity if SettingsService.USER_TEMPORARY_SESSION diff null.
     */
    private void goToMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Save the new collection after
     *
     * @return True if save collection.
     */
    private boolean saveCollection() {
        String name = this.nameCollection.getText().toString();
        String categoryName = this.categoryCollection
                .getSelectedItem().toString();
        Category category = CategoryService.findCategoryByName(categoryName);
        if (name.trim().length() == Utils.ZERO) {
            this.nameCollection.setError("Insira um nome");
            this.nameCollection.requestFocus();
            return false;
        } else {
            Log.i("USER", SettingsService.USER_TEMPORARY_SESSION.getId().toString() + " " +
                    SettingsService.USER_TEMPORARY_SESSION.getLogin().toString());
            CollectionService.saveCollection(name, category,
                    SettingsService.USER_TEMPORARY_SESSION);
            return true;
        }
    }

    /**
     * This method get all categories.
     *
     * @param list A List Model with categories model.
     * @return A new String List with names of categories.
     */
    private List<String> getCategoriesByListModel(List<Category> list) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getName());
        }
        return newList;
    }

}
