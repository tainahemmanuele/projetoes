package com.sugar.collection.collectionsugar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sugar.collection.collectionsugar.R.id;
import com.sugar.collection.collectionsugar.R.layout;
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.activities.collection.AddCollectionActivity;
import com.sugar.collection.collectionsugar.activities.item.ListItemActivity;
import com.sugar.collection.collectionsugar.adapters.ListCollectionAdapter;
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.SessionService;
import com.sugar.collection.collectionsugar.services.SettingsService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_main);
        Utils.generateCategoryDefault();
        List<Collection> listCollectionUser = CollectionService.getCollectionsByUser
                (SettingsService.USER_TEMPORARY_SESSION.getId().intValue());
        ListView listViewCollection = (ListView) this.findViewById(id.lv_collection);
        ListCollectionAdapter customAdapter = new ListCollectionAdapter(this, layout
                .item_list_row, listCollectionUser);
        listViewCollection.setAdapter(customAdapter);

        listViewCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Collection selectedCollection = (Collection) a.getItemAtPosition(position);
                goToViewCollectionList(selectedCollection.getId().intValue());
            }
        });

        FloatingActionButton btnLogout = (FloatingActionButton) this.findViewById(id.fab_logout);
        FloatingActionButton btnAddCollection = (FloatingActionButton) this.findViewById(id.fab);

        // Faz logout por enquanto.
        btnLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.logout();
                goToLogin();
            }
        });

        btnAddCollection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddCollection();
            }
        });
    }


    public void goToViewCollectionList(int id) {
        Intent i = new Intent(getApplicationContext(), ListItemActivity.class);
        i.putExtra("id", String.valueOf(id));
        startActivity(i);
        finish();
    }


    public void goToAddCollection() {
        Intent i = new Intent(getApplicationContext(), AddCollectionActivity.class);
        startActivity(i);
    }

    public void goToLogin() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void logout() {
        SessionService.deleteAllSessions();
        SettingsService.USER_TEMPORARY_SESSION = null;
    }

}
