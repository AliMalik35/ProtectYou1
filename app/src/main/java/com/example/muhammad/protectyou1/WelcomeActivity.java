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

import com.example.muhammad.protectyou1.Model.AccountDataBaseAdapter;

/**
 * Allows user to go to sign up activity or invoke login dialog
 */
public class WelcomeActivity extends Activity {
    private Button signUpBtn;
    private AccountDataBaseAdapter accountDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        accountDataBaseAdapter = new AccountDataBaseAdapter(this);
        accountDataBaseAdapter = accountDataBaseAdapter.open();

        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(WelcomeActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");

        final EditText usernameEditText = (EditText) dialog.findViewById(R.id.usernameEditText);
        final EditText passwordEditText = (EditText) dialog.findViewById(R.id.passwordEditText);

        Button signInBtn = (Button) dialog.findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // TODO password should be hashed before checking, given that passwords are hashed in db
                String storedPassword = accountDataBaseAdapter
                        .getUserPasswordByUsername(username);

                if (password.equals(storedPassword)) {
                    Toast.makeText(WelcomeActivity.this,
                            "Congrats: Login Successful!", Toast.LENGTH_LONG)
                            .show();
                    dialog.dismiss();

                    // used to track who current user of application is
                    accountDataBaseAdapter.insertCurrentUser(username);

                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(WelcomeActivity.this,
                            "User Name or Password does not match!",
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
