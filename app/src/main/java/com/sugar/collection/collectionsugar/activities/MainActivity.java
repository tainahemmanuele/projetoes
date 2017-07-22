package com.sugar.collection.collectionsugar.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.R.id;
import com.sugar.collection.collectionsugar.R.layout;
import com.sugar.collection.collectionsugar.Utils;
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
               // TODO: Navigate to view Item With delete button.
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

    public void goToAddCollection() {
        Intent i = new Intent(getApplicationContext(), AddCollectionActivity.class);
        startActivity(i);
        finish();
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

    // TODO: Method vai para View Item, pois botao de deletar estará lá.
    public void showAlertDelete(final int idCollection){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente deletar este item ?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(CollectionService.deleteCollection(idCollection)){
                            Toast toast = Toast.makeText(getApplicationContext(), R.string
                                            .item_delete_success,
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }else{
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


}
