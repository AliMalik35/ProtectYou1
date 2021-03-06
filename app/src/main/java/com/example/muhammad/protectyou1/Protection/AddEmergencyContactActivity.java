package com.example.muhammad.protectyou1.Protection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.R;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */

/**
 * Allows user to edit their emergency message.
 */
public class AddEmergencyContactActivity extends AppCompatActivity {
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private EditText nameTextView, relationTextView, phoneTextView;
    private Button addContactBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_emergency_contact);

        nameTextView = (EditText) findViewById(R.id.contactNameTextView);
        relationTextView = (EditText) findViewById(R.id.contactRelationTextView);
        phoneTextView = (EditText) findViewById(R.id.contactPhoneTextView);

        addContactBtn = (Button) findViewById(R.id.createContactBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            accountDataBaseAdapter.insertContact(nameTextView.getText().toString(), relationTextView.getText().toString(), phoneTextView.getText().toString());
            Toast.makeText(getApplicationContext(), "Contact Added", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(AddEmergencyContactActivity.this, ViewEmergencyContactsActivity.class));
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(AddEmergencyContactActivity.this, ViewEmergencyContactsActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }
}
