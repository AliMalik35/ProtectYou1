package com.example.muhammad.protectyou1;

/**
 * Created by Muhammad on 18/04/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Allows user to go to register page or invoke login dialog
 */
public class WelcomeActivity extends Activity {
    Button btnSignIn, btnSignUp;
    AccountDataBaseAdapter accountDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(WelcomeActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        final EditText editTextUserName = (EditText) dialog
                .findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog
                .findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                String storedPassword = accountDataBaseAdapter
                        .getSinlgeEntry(userName);

                if (password.equals(storedPassword)) {
                    Toast.makeText(WelcomeActivity.this,
                            "Congrats: Login Successfull", Toast.LENGTH_LONG)
                            .show();
                    dialog.dismiss();

                    //Intent main = new Intent(WelcomeActivity.this, Welcome.class);
                    //startActivity(main);
                } else {
                    Toast.makeText(WelcomeActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDataBaseAdapter.close();
    }
}
