package com.example.muhammad.protectyou1.Protection;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.HomeActivity;
import com.example.muhammad.protectyou1.Models.EmergencyContact;
import com.example.muhammad.protectyou1.R;

import java.util.List;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */
public class ProtectionHomeActivity extends AppCompatActivity {

    private Button viewContactsBtn, editEmergencySMSMessageBtn, protectionTriggerBtn, backBtn;
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private GPSTracker gps;

    private boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protection_home);

        // request permissions
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS,
                Manifest.permission.CALL_PHONE},1);

        gps = new GPSTracker(this);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        viewContactsBtn = (Button) findViewById(R.id.viewContactsBtn);
        editEmergencySMSMessageBtn = (Button) findViewById(R.id.editEmergencySMSMessageBtn);
        protectionTriggerBtn = (Button) findViewById(R.id.protectionTriggerBtn);
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

        protectionTriggerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protectUser();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }

    private boolean protectUser() {

        String message = accountDataBaseAdapter.getUserMessage();
        String username = accountDataBaseAdapter.getCurrentUsername();

        if (message == null) {
            message = "This is an emergency message sent from " + username + ". A Google Maps location is appended to this message. Please send Help!";
        }

        if (DEBUG) {
            // removes actual emergency msg, as to not fasely alarm people during testing
            message = "";
        }

        message += "\nhttps://www.google.com.au/maps/@"+ String.valueOf(gps.getLatitude()) +","+ String.valueOf(gps.getLongitude()) +",14z";

        List<EmergencyContact> contacts = accountDataBaseAdapter.getAllContacts();
        if (contacts == null) {
            Toast.makeText(getApplicationContext(), "No help was requested!\nAdd emergency contacts to fix this!", Toast.LENGTH_LONG).show();
            return false;
        }

        int msgCount = sendSMS(contacts, message);
        Toast.makeText(getApplicationContext(),
                "Help has been requested!\n"+ msgCount +" message(s) have been sent!", Toast.LENGTH_SHORT)
                .show();

        placeCall(contacts.get(0));

        return true;
    }

    /**
     * Sends an SMS to all contacts.
     *
     * @param contacts
     * @param message
     * @return int
     */
    private int sendSMS(List<EmergencyContact> contacts, String message) {
        int counter = 0;

        SmsManager sms = SmsManager.getDefault();
        for (EmergencyContact contact : contacts) {
            if (! contact.getPhone().isEmpty()) {
                counter++;
                sms.sendTextMessage(contact.getPhone(), null, message, null, null);
            }
        }

        return counter;
    }

    /**
     * Initiates a phone call to contact.
     *
     * @param contact
     */
    private void placeCall(EmergencyContact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + contact.getPhone()));
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            // permission error
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }

}
