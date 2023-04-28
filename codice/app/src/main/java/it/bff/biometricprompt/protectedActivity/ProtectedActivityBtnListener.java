package it.bff.biometricprompt.protectedActivity;

import android.Manifest;
import android.content.Intent;
import android.view.View;

class ProtectedActivityBtnListener implements View.OnClickListener {

    private ProtectedActivity activity;
    private ProtectedActivityHolder holder;

    ProtectedActivityBtnListener(ProtectedActivity activity, ProtectedActivityHolder holder) {
        this.holder = holder;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == holder.getFabGetPhotoId()) {

            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(intent , ProtectedActivity.GET_PICTURE_CODE);
        }
        else if(v.getId() == holder.getFabTakePhotoId()) {

            String[] allPermissions = {Manifest.permission.CAMERA};
            if(PermissionManager.askPermission(allPermissions, activity, PermissionManager.REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA)) {
                activity.launchActivity(PermissionManager.REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA);
            }

        }
    }

}
