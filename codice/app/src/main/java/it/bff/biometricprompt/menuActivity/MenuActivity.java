package it.bff.biometricprompt.menuActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import it.bff.biometricprompt.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        new MenuActivityHolder(this);
    }
}
