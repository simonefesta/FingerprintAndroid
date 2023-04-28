package it.bff.biometricprompt.protectedActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import it.bff.biometricprompt.R;

public class ProtectedActivityPhotoZoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected_photo_zoom);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        ProtectedActivityPhotoZoomHolder holder = new ProtectedActivityPhotoZoomHolder(this);
        holder.setIvPhotoByPath(path);
    }
}
