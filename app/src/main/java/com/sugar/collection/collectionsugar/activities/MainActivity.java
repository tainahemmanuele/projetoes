package com.sugar.collection.collectionsugar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.adapters.ListCollectionAdapter;
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.services.CollectionService;
import com.sugar.collection.collectionsugar.services.SettingsService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Collection> listCollectionUser = CollectionService.getCollectionsByUser(SettingsService.USER_TEMPORARY_SESSION.getId().intValue());
        ListView lisViewCollection = (ListView) findViewById(R.id.lv_collection);
        ListCollectionAdapter customAdapter = new ListCollectionAdapter(this, R.layout.item_list_row, listCollectionUser);
        lisViewCollection.setAdapter(customAdapter);
    }

}
