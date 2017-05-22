package com.example.muhammad.protectyou1.Protect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.R;
import com.example.muhammad.protectyou1.WelcomeActivity;

public class EditEmergencyMessageActivity extends AppCompatActivity {
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private EditText emergencyMessageEditText;
    private Button saveMessageBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_emergency_message);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        if (! accountDataBaseAdapter.userIsLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        }

        emergencyMessageEditText = (EditText) findViewById(R.id.emergencyMessageEditText);
        saveMessageBtn = (Button) findViewById(R.id.saveMessageBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        String message = accountDataBaseAdapter.getUserMessage();
        if (message != null) {
            emergencyMessageEditText.setText(message);
        }

        saveMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountDataBaseAdapter.updateMessage(emergencyMessageEditText.getText().toString());

                Toast.makeText(getApplicationContext(),
                        "Message Saved!", Toast.LENGTH_LONG)
                        .show();

                startActivity(new Intent(getApplicationContext(), ProtectionHomeActivity.class));
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProtectionHomeActivity.class));
            }
        });

    }
}
