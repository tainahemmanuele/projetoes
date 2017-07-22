package com.sugar.collection.collectionsugar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.entities.Category;
import com.sugar.collection.collectionsugar.services.CategoryService;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.SettingsService;

import java.util.ArrayList;
import java.util.List;

public class AddCollectionActivity extends AppCompatActivity {

    private EditText nameCollection;
    private Spinner categoryCollection;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);
        List<String> categories = getCategoriesByListModel(CategoryService.getAllCategories());
        categoryCollection = (Spinner) findViewById(R.id.category_collection);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryCollection.setAdapter(dataAdapter);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // tratar com toast quando nao der certo. e validar os campos quando for vazio.
                saveCollection();
                Toast toast = Toast.makeText(getApplicationContext(), R.string
                        .create_collection_success, Toast.LENGTH_LONG);
                toast.show();
                finish();
            }

        });
    }


    private void saveCollection() {
        String name = this.nameCollection.getText().toString();
        Category category = CategoryService.findCategoryByName(this.categoryCollection
                .getSelectedItem().toString());
        CollectionService.saveCollection(name, category, SettingsService.USER_TEMPORARY_SESSION);
    }

    private List<String> getCategoriesByListModel(List<Category> list) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getName());
        }
        return newList;
    }

}
