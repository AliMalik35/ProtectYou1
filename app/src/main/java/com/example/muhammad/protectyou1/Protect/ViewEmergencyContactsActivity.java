package com.example.muhammad.protectyou1.Protect;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.Models.EmergencyContact;
import com.example.muhammad.protectyou1.R;

import java.util.List;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */

/**
 * Allows user to view emergency contatcs
 */
public class ViewEmergencyContactsActivity extends AppCompatActivity {
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private Button addContactsBtn, backBtn;
    private TextView noContactsTextView;
    private ListView contactsListView;
    private ArrayAdapter<EmergencyContact> adapter;

    private void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_emergency_contacts);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        addContactsBtn = (Button) findViewById(R.id.addContactsBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        noContactsTextView = (TextView) findViewById(R.id.noContactsTextView);
        contactsListView = (ListView) findViewById(R.id.contactsListView);

        final List<EmergencyContact> contacts = accountDataBaseAdapter.getAllContacts();

        if (contacts != null) {
            adapter = new ArrayAdapter<EmergencyContact>(this, R.layout.emergency_contacts_list_item, R.id.emergencyContactTextView, contacts);

            contactsListView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            contactsListView.setAdapter(adapter);
        } else {
            noContactsTextView.setText("There are no contacts to display!");
            contactsListView.setVisibility(View.INVISIBLE);
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
                Intent i = new Intent(ViewEmergencyContactsActivity.this, ProtectionHomeActivity.class);
                startActivity(i);
            }
        });

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(ViewEmergencyContactsActivity.this, R.style.deleteConfirmDialog));
                /**
                 * Enables confirmation dialog to confirm user deleting contact by clicking on the item
                 */
                final int pos = position;
                final EmergencyContact contact = contacts.get(pos);
                alertDialogBuilder.setTitle("Delete Contact?");

                alertDialogBuilder.setMessage("Delete " + contact.getName() + "?");
                alertDialogBuilder.setNegativeButton("Cancel", null);
                alertDialogBuilder.setPositiveButton("OK", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contacts.remove(pos);
                        if (contacts.isEmpty()) {
                            noContactsTextView.setText("There are no contacts to display!");
                            contactsListView.setVisibility(View.INVISIBLE);
                        }
                        accountDataBaseAdapter.deleteContact(contact.getId());
                        notifyDataChanged();
                    }
                });
                alertDialogBuilder.show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }
}
