package com.example.muhammad.protectyou1.Privacy;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.muhammad.protectyou1.HomeActivity;
import com.example.muhammad.protectyou1.R;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */
/**
 * Gives options for user to invoke privacy functionality
 */
public class PrivacyHomeActivity extends AppCompatActivity {
    private Button deleteDCIMImagesBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_home);

        // reqeust permissions for user to delete images in DeleteImageFilesActivity
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        deleteDCIMImagesBtn = (Button) findViewById(R.id.deleteDCIMImagesBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        deleteDCIMImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeleteImageFilesActivity.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }

}
