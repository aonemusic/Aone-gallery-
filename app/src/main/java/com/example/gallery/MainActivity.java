package com.example.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        } else {
            loadImages();
        }
    }

    private void loadImages() {
        images = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        
        if (cursor != null) {
            while (cursor.moveToNext()) {
                images.add(cursor.getString(0));
            }
            cursor.close();
        }
        // ഇവിടെ ഒരു Adapter സെറ്റ് ചെയ്താൽ ഫോട്ടോകൾ സ്ക്രീനിൽ കാണാം
        Toast.makeText(this, "Found " + images.size() + " Images", Toast.LENGTH_SHORT).show();
    }
}
