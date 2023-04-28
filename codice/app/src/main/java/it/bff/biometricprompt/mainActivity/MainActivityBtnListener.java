package it.bff.biometricprompt.mainActivity;

import android.content.Intent;
import android.view.View;

import it.bff.biometricprompt.menuActivity.MenuActivity;

class MainActivityBtnListener implements View.OnClickListener
{
    private MainActivity activity;
    private MainActivityHolder holder;

    MainActivityBtnListener(MainActivity activity, MainActivityHolder holder) {
        this.holder = holder;
        this.activity = activity;
    }

    @Override
    public void onClick(View v)
    {
        // Authentication button
        if(v.getId() == holder.getBtnAuthId()) {
            // Start animation
            /*
            * The authentication actions are implemented into 'MainActivityAnimListener' class
            * The system check for the authentication permissions only when the animation is terminated
            * At the end of the animation, 'onAnimationEnd' is called
            * */
            v.startAnimation(holder.getAnimBubble());
        }
        // Menu button
        else if(v.getId() == holder.getBtnMenu())
        {
            Intent intent = new Intent(activity, MenuActivity.class);
            activity.startActivity(intent);
        }
    }
}
