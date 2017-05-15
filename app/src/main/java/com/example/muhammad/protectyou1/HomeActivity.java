package com.example.muhammad.protectyou1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.muhammad.protectyou1.EmergencyContacts.ViewEmergencyContactsActivity;

/**
 * Ashley Menhennett
 */
public class HomeActivity extends AppCompatActivity {

    private Button viewContactsBtn, editEmergencySMSMessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        viewContactsBtn = (Button) findViewById(R.id.viewContactsBtn);
        editEmergencySMSMessageBtn = (Button) findViewById(R.id.editEmergencySMSMessageBtn);

        viewContactsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewEmergencyContactsActivity.class);
                startActivity(i);
            }
        });

        editEmergencySMSMessageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            // go to edit emergency sms message activity
            }
        });
    }
}
