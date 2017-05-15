package com.example.muhammad.protectyou1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button protectionBtn, privacyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        protectionBtn = (Button) findViewById(R.id.protectionBtn);
        privacyBtn = (Button) findViewById(R.id.privacyBtn);

        protectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProtectionHomeActivity.class));
            }
        });
    }
}
