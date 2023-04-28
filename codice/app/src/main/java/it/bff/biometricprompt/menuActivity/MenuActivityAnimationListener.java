package it.bff.biometricprompt.menuActivity;

import android.content.Intent;
import android.view.animation.Animation;

import it.bff.biometricprompt.infoActivity.InfoActivity;

public class MenuActivityAnimationListener implements Animation.AnimationListener {

    MenuActivity activity;

    MenuActivityAnimationListener(MenuActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(activity, InfoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
