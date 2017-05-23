package com.example.muhammad.protectyou1.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.muhammad.protectyou1.Models.EmergencyContact;
import com.example.muhammad.protectyou1.R;

import java.util.ArrayList;


/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */
/**
 * List Adapter for an ArratList<EmergencyContact>
 */
public class EmergencyContactsListAdapter extends ArrayAdapter<EmergencyContact> {

    private ArrayList<EmergencyContact> contacts;

    public EmergencyContactsListAdapter(Context ctx, int textViewResourceId, ArrayList<EmergencyContact> contacts) {
        super(ctx, textViewResourceId, contacts);
        this.contacts = contacts;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emergency_contacts_list_item, parent, false);
        }

        return convertView;
    }

}