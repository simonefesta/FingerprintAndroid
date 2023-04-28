package it.bff.biometricprompt.protectedActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import it.bff.biometricprompt.R;

public class ProtectedActivityPhotoListener implements View.OnClickListener {

    private ProtectedActivity activity;
    private ProtectedActivityPhotoHolder holder;

    ProtectedActivityPhotoListener(ProtectedActivity activity, ProtectedActivityPhotoHolder holder) {
        this.activity = activity;
        this.holder = holder;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == holder.getIvPhotoId()) {

            Intent intent = new Intent(activity, ProtectedActivityPhotoZoom.class);
            intent.putExtra("path", holder.getPath());
            activity.startActivity(intent);
            return;
        }
        if(v.getId() == holder.getConstraintLayoutId()) {

            Toast.makeText(activity.getApplicationContext(),
                    activity.getResources().getString(R.string.tv_hint_delete_photo),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
