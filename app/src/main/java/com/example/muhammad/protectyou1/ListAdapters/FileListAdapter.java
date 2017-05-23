package com.example.muhammad.protectyou1.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.muhammad.protectyou1.R;

import java.io.File;
import java.util.ArrayList;


/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */

/**
 * ArrayAdapter for ArrayList<File>
 */
public class FileListAdapter extends ArrayAdapter<File> {

    private ArrayList<File> files;

    public FileListAdapter(Context ctx, int textViewResourceId, ArrayList<File> files) {
        super(ctx, textViewResourceId, files);
        this.files = files;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.files_list_item, parent, false);
        }

        return convertView;
    }

}