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
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammad.protectyou1.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStoragePublicDirectory;
/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */
/**
 * Allows users to deleted selected files from a ListView.
 */
public class DeleteImageFilesActivity extends AppCompatActivity {
    private ListView imageFilesListView;
    private Button backBtn;
    private TextView noFilesTextView;
    private ArrayAdapter<File> adapter;
    private List<File> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_image_files);

        imageFilesListView = (ListView) findViewById(R.id.imageFilesListView);
        noFilesTextView = (TextView) findViewById(R.id.noFilesTextView);
        backBtn = (Button) findViewById(R.id.backBtn);

        files = getListFiles(getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

        if (! files.isEmpty()) {
            adapter = new ArrayAdapter<File>(this, R.layout.files_list_item, R.id.fileTextView, files);
            imageFilesListView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            imageFilesListView.setAdapter(adapter);
        } else {
            noFilesTextView.setText("There are no image files to delete..");
            imageFilesListView.setVisibility(View.INVISIBLE);
        }

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

    /**
     * Recursively get all image files, starting at dir
     *
     * @param  dir - the root directory
     * @return List<File>
     */
    private List<File> getListFiles(File dir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".jpg") ||
                        file.getName().endsWith(".jpeg") ||
                        file.getName().endsWith(".png") ||
                        file.getName().endsWith(".gif")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    /**
     * Deletes a file and removes it from the ArrayList
     *
     * @param position
     * @return boolean
     */
    private boolean deleteFile(int position) {
        File file = files.get(position);
        files.remove(position);
        if (files.isEmpty()) {
            noFilesTextView.setText("There are no image files to delete..");
            imageFilesListView.setVisibility(View.INVISIBLE);
        }
        adapter.notifyDataSetChanged();
        return file.delete();
    }
}
