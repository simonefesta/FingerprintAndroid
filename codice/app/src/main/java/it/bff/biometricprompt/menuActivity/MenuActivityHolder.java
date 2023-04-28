package it.bff.biometricprompt.menuActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import it.bff.biometricprompt.R;

class MenuActivityHolder {

    private ImageView ivInfoChanged;
    private Animation animationIvInfo;

    MenuActivityHolder(MenuActivity activity) {

        CardView cvDebug = activity.findViewById(getCvDebugId());
        CardView cvInfo = activity.findViewById(getCvInfoId());
        ivInfoChanged = activity.findViewById(getIvInfoChangedId());

        // click listener
        MenuActivityBtnListener listener = new MenuActivityBtnListener(activity, this);

        cvDebug.setOnClickListener(listener);
        cvInfo.setOnClickListener(listener);

        // animation
        MenuActivityAnimationListener animListener = new MenuActivityAnimationListener(activity);

        animationIvInfo = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.fade_in);
        animationIvInfo.setAnimationListener(animListener);
    }

    int getCvDebugId() {
        return R.id.cvDebug;
    }
    int getCvInfoId() {
        return R.id.cvInfo;
    }
    private int getIvInfoChangedId() {
        return R.id.ivInfoChanged;
    }

    void startIvInfoAnimation() {

        ivInfoChanged.setVisibility(View.VISIBLE);
        ivInfoChanged.startAnimation(animationIvInfo);
    }

}
