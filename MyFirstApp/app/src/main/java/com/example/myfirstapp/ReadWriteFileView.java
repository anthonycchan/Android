package com.example.myfirstapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadWriteFileView extends AppCompatActivity {

    private final String filename = "myfile.txt";
    private final String fileContents = "Hello world!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write_file);

        try {
            TextView tv6 = findViewById(R.id.textView6);
            File newFile = null;

            switch (3) {
                case 1:
                    //
                    // Write file to internal storage (written to /data/user/0/com.example.myfirstapp/files)
                    //
                    File newDIR = new File(getFilesDir(), "newDIR");
                    newDIR.mkdirs();
                    newFile = writeFile(newDIR, "myfile.txt");
                    break;

                case 2:
                    //
                    // Write to cache (written to /data/user/0/com.example.myfirstapp/cache)
                    //
                    File cacheDIR = new File(getCacheDir(), "cacheDIR");
                    cacheDIR.mkdirs();
                    newFile = writeTempFile(cacheDIR, "myfile.txt");
                    break;

                case 3:
                    //
                    // Write to external storage
                    //
                    requestAppPermissions();

                    File extDIR = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "extDIR");
                    extDIR.mkdirs();
                    newFile = writeExtFile(extDIR, "myfile.txt");

                    break;

                case 4:
                    // Write to internal storage (alternate implementation)
                    FileOutputStream outputStream = null;
                    outputStream = openFileOutput(filename, getApplicationContext().MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();

                    FileInputStream fIn = openFileInput("myfile.txt");
                    InputStreamReader isr = new InputStreamReader(fIn);

                    /* Prepare a char-Array that will
                     * hold the chars we read back in. */
                    char[] inputBuffer = new char[12];

                    // Fill the Buffer with data from the file
                    isr.read(inputBuffer);
                    fIn.close();

                    // Transform the chars to a String
                    String readString = new String(inputBuffer);

                    tv6.setText(readString);
                    break;
            }

            //
            // Read from file
            //
            FileInputStream fIn2 = new FileInputStream(newFile);
            InputStreamReader isr2 = new InputStreamReader(fIn2);
            BufferedReader br2 = new BufferedReader(isr2);

            StringBuffer strBuff = new StringBuffer();
            String line;
            while ((line = br2.readLine()) != null)
                strBuff.append(line);
            fIn2.close();
            tv6.setText(strBuff.toString());


            } catch(Exception e){
                e.printStackTrace();
            }
    }

    private File writeFile(File newDIR, String fileName) throws IOException {
        File newFile = new File(newDIR.getAbsolutePath(), fileName);
        FileOutputStream fos = new FileOutputStream(newFile, true);

        fos.write(fileContents.getBytes());
        fos.close();

        return newFile;
    }

    private File writeTempFile(File newDIR, String fileName) throws IOException {
        // Creates a temp file in the cache folder with numbers appended after the filename
        File newFile = File.createTempFile(fileName, null, newDIR);
        FileOutputStream fos = new FileOutputStream(newFile, true);

        fos.write(fileContents.getBytes());
        fos.close();

        return newFile;
    }

    private File writeExtFile(File newDIR, String fileName) throws IOException {
        File newFile = new File(newDIR,"myfile");
        FileOutputStream fos = new FileOutputStream(newFile, true);

        fos.write(fileContents.getBytes());
        fos.close();

        return newFile;
    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 0); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
}
