package com.sugar.collection.collectionsugar.activities.collection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.services.CategoryService;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.SettingsService;

import java.util.ArrayList;
import java.util.List;

public class ViewCollectionActivity extends AppCompatActivity {

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

    private FloatingActionButton btnDelete;

    private EditText idCollection;

    private String id_collection;

    private Collection collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            id_collection = (String) b.get("id");
            collection = CollectionService.getCollectionById(Integer.parseInt(id_collection));
        } else {
            finish();
        }
        // get all categories in the app.
        // TODO: filter just categories created by user.
        Log.i("coll", collection.getName());
        List<String> categories = getCategoriesByListModel(CategoryService.getAllCategories());
        idCollection = (EditText) findViewById(R.id.id_collection);
        idCollection.setText(String.valueOf(collection.getId()));
        idCollection.setEnabled(false);
        nameCollection = (EditText) findViewById(R.id.name_item);
        nameCollection.setText(collection.getName());
        categoryCollection = (Spinner) findViewById(R.id.category_collection);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryCollection.setAdapter(dataAdapter);
        categoryCollection.setSelection(dataAdapter.getPosition(
                collection.getCategory().getName()));

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateCollection()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .update_collection_success, Toast.LENGTH_LONG);
                    toast.show();
                    goToMainActivity();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .update_collection_error, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btnDelete = (FloatingActionButton) findViewById(R.id.fab_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDelete();
            }
        });

    }


    public void showAlertDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente deletar esta coleção ?")
                .setCancelable(false)
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (CollectionService.deleteCollection(collection.getId().intValue())) {
                            Toast toast = Toast.makeText(getApplicationContext(), R.string
                                            .item_delete_success,
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            goToMainActivity();
                            finish();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), R.string
                                            .item_delete_error,
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
    private boolean updateCollection() {
        String id = this.idCollection.getText().toString();
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
            CollectionService.updateCollection(Integer.parseInt(id), name, category, SettingsService
                    .USER_TEMPORARY_SESSION);
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
