package com.sugar.collection.collectionsugar.activities.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.ItemService;
import com.sugar.collection.collectionsugar.services.SettingsService;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameItem;
    /**
     * Button submit form.
     */
    private Button btnSubmit;

    private String id_collection;

    private Collection collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            id_collection = (String) b.get("id");
            collection = CollectionService.getCollectionById(Integer.parseInt(id_collection));
        } else {
            finish();
        }

        nameItem = (EditText) findViewById(R.id.name_item);

        btnSubmit = (Button) findViewById(R.id.btn_add_item);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveItem()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .create_item_success, Toast.LENGTH_LONG);
                    toast.show();
                    goToListCollection();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .create_item_error, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }


    private boolean saveItem() {
        String name = this.nameItem.getText().toString();
        if (name.trim().length() == Utils.ZERO) {
            this.nameItem.setError("Insira um nome");
            this.nameItem.requestFocus();
            return false;
        } else {
            Log.i("USER", SettingsService.USER_TEMPORARY_SESSION.getId().toString() + " " +
                    SettingsService.USER_TEMPORARY_SESSION.getLogin().toString());
            ItemService.saveItem(name, collection, String.valueOf(System.currentTimeMillis()));
            return true;
        }
    }


    private void goToListCollection() {
        Intent i = new Intent(getApplicationContext(), ListItemActivity.class);
        i.putExtra("id", id_collection);
        startActivity(i);
        finish();
    }
}
