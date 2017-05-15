package com.example.muhammad.protectyou1.EmergencyContacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.muhammad.protectyou1.Model.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.HomeActivity;
import com.example.muhammad.protectyou1.R;

import java.util.List;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */

public class ViewEmergencyContactsActivity extends AppCompatActivity {
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private Button addContactsBtn, backBtn;
    private ListView contactsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_emergency_contacts);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        addContactsBtn = (Button) findViewById(R.id.addContactsBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        contactsListView = (ListView) findViewById(R.id.contactsListView);

        List<EmergencyContact> contacts = accountDataBaseAdapter.getAllContactsForCurrentUser();

        if (contacts != null) {
            ArrayAdapter<EmergencyContact> adapter = new ArrayAdapter<EmergencyContact>(this, R.layout.emergency_contacts_list_item, R.id.emergencyContactTextView, contacts);

            contactsListView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            contactsListView.setAdapter(adapter);
        }

        addContactsBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            Intent i = new Intent(ViewEmergencyContactsActivity.this, AddEmergencyContactActivity.class);
            startActivity(i);
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewEmergencyContactsActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }
}
