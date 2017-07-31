package com.sugar.collection.collectionsugar.activities.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.activities.collection.ViewCollectionActivity;
import com.sugar.collection.collectionsugar.adapters.ListItemAdapter;
import com.sugar.collection.collectionsugar.entities.Item;
import com.sugar.collection.collectionsugar.services.ItemService;

import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    private String id_collection;

    private FloatingActionButton fab_item;

    private FloatingActionButton fab_edit_collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            id_collection = (String) b.get("id");
        } else {
            finish();
        }
        List<Item> listItemUser = ItemService.getItensByCollection(Integer.parseInt(id_collection));
        ListView listViewItem = (ListView) this.findViewById(R.id.lv_item);
        ListItemAdapter customAdapter = new ListItemAdapter(this, R.layout.item_list_row,
                listItemUser);
        listViewItem.setAdapter(customAdapter);

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Item selectedItem = (Item) a.getItemAtPosition(position);
                goToViewItem(selectedItem.getId().intValue());
            }
        });

        fab_edit_collection = (FloatingActionButton) this.findViewById(R.id.fab_edit);

        fab_item = (FloatingActionButton) this.findViewById(R.id.fab_item);

        fab_edit_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToViewCollection(id_collection);
            }
        });

        fab_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddItem();
            }
        });
    }


    public void goToViewCollection(String id) {
        Intent i = new Intent(getApplicationContext(), ViewCollectionActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }


    public void goToViewItem(int id) {
        Intent i = new Intent(getApplicationContext(), ViewItemActivity.class);
        i.putExtra("id", String.valueOf(id));
        startActivity(i);
        finish();
    }


    public void goToAddItem() {
        Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
        i.putExtra("id", id_collection);
        startActivity(i);
        finish();
    }
}
