package it.bff.biometricprompt.mainActivity;

import android.content.Intent;
import android.view.animation.Animation;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

import it.bff.biometricprompt.R;
import it.bff.biometricprompt.infoActivity.InfoActivity;

class MainActivityAnimListener implements Animation.AnimationListener {

    private MainActivity activity;
    private MainActivityHolder holder;

    MainActivityAnimListener(MainActivity activity, MainActivityHolder holder) {
        this.activity = activity;
        this.holder = holder;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if(animation == holder.getAnimBubble())
        {
            // Check fingerprint configuration
            CheckFingerPrint checkFingerPrint = new CheckFingerPrint(activity);
            if (!checkFingerPrint.check())
                return;

            // Create a Biometric Prompt
            Executor executor = ContextCompat.getMainExecutor(activity.getApplicationContext());
            BiometricPrompt biometricPrompt = new BiometricPrompt(activity, executor, new BiometricOurAuthentication(activity));

            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle(activity.getResources().getString(R.string.prompt_auth_title))
                    .setSubtitle(activity.getResources().getString(R.string.prompt_auth_subtitle))
                    .setNegativeButtonText(activity.getResources().getString(R.string.prompt_auth_btn_back))
                    .build();

            biometricPrompt.authenticate(promptInfo);

        }
        if(animation == holder.getAnimFadeIn())
        {
            Intent intent = new Intent(activity, InfoActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
