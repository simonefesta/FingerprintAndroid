package it.bff.biometricprompt.mainActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import it.bff.biometricprompt.R;

class MainActivityHolder {

    private Animation animAuthBtn;
    private Animation animInfoBtnIn;

    MainActivityHolder(MainActivity activity)
    {

        Button btnAuth = activity.findViewById(getBtnAuthId());
        ImageButton btnMenu = activity.findViewById(getBtnMenu());

        // Animation setting
        animAuthBtn = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.bubble_effect);
        AnimationInterpolator interpolator = new AnimationInterpolator(0.8, 5);
        MainActivityAnimListener animListener = new MainActivityAnimListener(activity, this);
        animAuthBtn.setInterpolator(interpolator);
        animAuthBtn.setAnimationListener(animListener);

        animInfoBtnIn = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.fade_in);
        animInfoBtnIn.setAnimationListener(animListener);

        // Listener
        MainActivityBtnListener listener = new MainActivityBtnListener(activity, this);

        btnAuth.setOnClickListener(listener);
        btnMenu.setOnClickListener(listener);
    }

    int getBtnAuthId() {
        return R.id.btnAuth;
    }
    int getBtnMenu() {
        return R.id.btnMenu;
    }
    Animation getAnimFadeIn() {
        return animInfoBtnIn;
    }
    Animation getAnimBubble() {
        return animAuthBtn;
    }

}
