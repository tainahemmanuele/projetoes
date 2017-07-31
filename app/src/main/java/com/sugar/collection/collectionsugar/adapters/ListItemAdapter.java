package com.sugar.collection.collectionsugar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.R.layout;
import com.sugar.collection.collectionsugar.entities.Collection;
import com.sugar.collection.collectionsugar.entities.Item;

import java.util.List;

/**
 * Created by caiom on 21/07/2017.
 */

public class ListItemAdapter extends ArrayAdapter<Item> {

    public ListItemAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListItemAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(layout.item_list_row, null);
        }

        Item p = this.getItem(position);

        if (p != null) {
            TextView id = (TextView) v.findViewById(R.id.id);
            TextView title = (TextView) v.findViewById(R.id.title_item);
            id.setText(String.valueOf(p.getId().intValue()));
            title.setText(p.getName());
        }

        return v;
    }

}