package com.example.muhammad.protectyou1.Privacy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.muhammad.protectyou1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class DeleteImageFilesActivity extends AppCompatActivity {
    private ListView imageFilesListView;
    private Button backBtn;
    private ArrayAdapter<File> adapter;
    private List<File> files;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_image_files);

        imageFilesListView = (ListView) findViewById(R.id.imageFilesListView);

        files = getListFiles(getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

        if (files != null) {
            adapter = new ArrayAdapter<File>(this, R.layout.files_list_item, R.id.fileTextView, files);
            imageFilesListView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            imageFilesListView.setAdapter(adapter);
        }

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyHomeActivity.class));
            }
        });

        imageFilesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                boolean result = deleteFile(position);
                if (result) {
                    Toast.makeText(getApplicationContext(),
                            "File Deleted!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error..", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private List<File> getListFiles(File dir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".jpg") ||
                        file.getName().endsWith(".jpeg") ||
                        file.getName().endsWith(".png")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    private boolean deleteFile(int position) {
        File file = files.get(position);
        files.remove(position);
        adapter.notifyDataSetChanged();
        return file.delete();
    }
}
