package it.bff.biometricprompt.menuActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;

import it.bff.biometricprompt.infoActivity.InfoActivity;
import it.bff.biometricprompt.protectedActivity.ProtectedActivity;

public class MenuActivityBtnListener implements View.OnClickListener {

    private MenuActivity activity;
    private MenuActivityHolder holder;

    MenuActivityBtnListener(MenuActivity activity, MenuActivityHolder holder) {

        this.activity = activity;
        this.holder = holder;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == holder.getCvDebugId()) {
            Intent intent = new Intent(activity, ProtectedActivity.class);
            activity.startActivity(intent);
        }
        if(v.getId() == holder.getCvInfoId()) {

            holder.startIvInfoAnimation();
        }

    }
}
