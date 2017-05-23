package com.example.muhammad.protectyou1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.Privacy.PrivacyHomeActivity;
import com.example.muhammad.protectyou1.Protection.ProtectionHomeActivity;
/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>, original by Ali, has been recreated since
 */
public class HomeActivity extends AppCompatActivity {
    private Button protectionBtn, privacyBtn, logOutBtn;
    private AccountDataBaseAdapter accountDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        protectionBtn = (Button) findViewById(R.id.protectionBtn);
        privacyBtn = (Button) findViewById(R.id.privacyBtn);

        protectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProtectionHomeActivity.class));
            }
        });

        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PrivacyHomeActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }
}
