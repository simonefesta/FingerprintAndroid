package it.bff.biometricprompt.protectedActivity;
import android.view.animation.Animation;

import static java.lang.Thread.sleep;


public class ProtectedActivityAnimListener  implements Animation.AnimationListener
{
    private ProtectedActivity activity;
    private ProtectedActivityPhotoHolder holder;

    ProtectedActivityAnimListener(ProtectedActivity activity, ProtectedActivityPhotoHolder holder) {
        this.activity = activity;
        this.holder = holder;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if(animation == holder.getAnimOnLongClick())
        {


        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
