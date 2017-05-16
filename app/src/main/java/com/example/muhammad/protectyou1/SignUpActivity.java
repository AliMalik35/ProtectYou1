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

import com.example.muhammad.protectyou1.Model.AccountDataBaseAdapter;

/**
 * Registers a user
 */
public class SignUpActivity extends Activity {
    private EditText usernameEditText, passwordEditText, confirmPasswordEditText;
    private AccountDataBaseAdapter accountDataBaseAdapter;
    private Button createAccountBtn;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);

        createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // TODO check username isn't taken

                if (username.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Fields are Empty!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (accountDataBaseAdapter.usernameExists(username)) {
                    Toast.makeText(getApplicationContext(), "Username taken!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (! password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    accountDataBaseAdapter.insertNewUser(username, password);

                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created!", Toast.LENGTH_LONG)
                            .show();

                    Intent i = new Intent(SignUpActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }
}
