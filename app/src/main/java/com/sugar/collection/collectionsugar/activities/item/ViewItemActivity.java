package com.sugar.collection.collectionsugar.activities.item;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.entities.Item;
import com.sugar.collection.collectionsugar.services.ItemService;
import com.sugar.collection.collectionsugar.services.SettingsService;

public class ViewItemActivity extends AppCompatActivity {

    private EditText nameItem;
    /**
     * Button submit form.
     */
    private Button btnSubmit;

    private FloatingActionButton btnDelete;

    private String id_item;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            id_item = (String) b.get("id");
            item = ItemService.getItemById(Integer.parseInt(id_item));
        } else {
            finish();
        }
        nameItem = (EditText) findViewById(R.id.name_item);
        nameItem.setText(item.getName());


        btnSubmit = (Button) findViewById(R.id.btn_update_item);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (updateItem()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .update_item_success, Toast.LENGTH_LONG);
                    toast.show();
                    goToListCollection();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string
                            .update_item_error, Toast.LENGTH_LONG);
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
        builder.setMessage("Deseja realmente deletar este item ?")
                .setCancelable(false)
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (ItemService.deleteItem(item.getId().intValue())) {
                            Toast toast = Toast.makeText(getApplicationContext(), R.string
                                            .item_delete_success,
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            goToListCollection();
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


    private boolean updateItem() {
        String name = this.nameItem.getText().toString();
        if (name.trim().length() == Utils.ZERO) {
            this.nameItem.setError("Insira um nome");
            this.nameItem.requestFocus();
            return false;
        } else {
            Log.i("USER", SettingsService.USER_TEMPORARY_SESSION.getId().toString() + " " +
                    SettingsService.USER_TEMPORARY_SESSION.getLogin().toString());
            ItemService.updateItem(item.getId().intValue(), name, item.getCollection(), item
                    .getCreated_at());
            return true;
        }
    }

    private void goToListCollection() {
        Intent i = new Intent(getApplicationContext(), ListItemActivity.class);
        i.putExtra("id", item.getCollection().getId().toString());
        startActivity(i);
        finish();
    }
}
