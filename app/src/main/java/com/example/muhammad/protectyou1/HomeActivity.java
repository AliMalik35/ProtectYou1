package com.example.muhammad.protectyou1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.muhammad.protectyou1.DataAccess.AccountDataBaseAdapter;
import com.example.muhammad.protectyou1.Protect.ProtectionHomeActivity;

public class HomeActivity extends AppCompatActivity {
    private Button protectionBtn, privacyBtn, logOutBtn;
    private AccountDataBaseAdapter accountDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        if (! accountDataBaseAdapter.userIsLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
        }

        //startService(new Intent(getApplicationContext(), PowerButtonService.class));

        protectionBtn = (Button) findViewById(R.id.protectionBtn);
        privacyBtn = (Button) findViewById(R.id.privacyBtn);
        //logOutBtn = (Button) findViewById(R.id.logOutBtn);

        protectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProtectionHomeActivity.class));
            }
        });

//        logOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logOut();
//            }
//        });
    }

//    private void logOut() {
//        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
//        accountDataBaseAdapter = accountDataBaseAdapter.open();
//
//        accountDataBaseAdapter.clearCurrentUser();
//        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
//    }
}
