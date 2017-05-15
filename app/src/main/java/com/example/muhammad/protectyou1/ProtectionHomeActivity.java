package com.example.muhammad.protectyou1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.muhammad.protectyou1.EmergencyContacts.ViewEmergencyContactsActivity;

/**
 * Ashley Menhennett
 */
public class ProtectionHomeActivity extends AppCompatActivity {

    private Button viewContactsBtn, editEmergencySMSMessageBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protection_home);

        viewContactsBtn = (Button) findViewById(R.id.viewContactsBtn);
        editEmergencySMSMessageBtn = (Button) findViewById(R.id.editEmergencySMSMessageBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        viewContactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), ViewEmergencyContactsActivity.class));
            }
        });

        editEmergencySMSMessageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), EditEmergencyMessageActivity.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
}
