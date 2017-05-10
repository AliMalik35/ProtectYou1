package com.example.muhammad.protectyou1;

/**
 * Created by Muhammad on 18/04/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Registers a user
 */
public class SignUPActivity extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    AccountDataBaseAdapter accountDataBaseAdapter;
    Button btnCreateAccount;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (! password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                } else {

                    accountDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(SignUPActivity.this,
                            WelcomeActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        accountDataBaseAdapter.close();
    }
}
