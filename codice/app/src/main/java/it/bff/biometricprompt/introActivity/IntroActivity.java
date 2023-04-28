package it.bff.biometricprompt.introActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import it.bff.biometricprompt.R;
import it.bff.biometricprompt.mainActivity.MainActivity;


public class IntroActivity  extends AppCompatActivity
{

    private static int TIMER_INTRO = 2200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, TIMER_INTRO);
    }
}
