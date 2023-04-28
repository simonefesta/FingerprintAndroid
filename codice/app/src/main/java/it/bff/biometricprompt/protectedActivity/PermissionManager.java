package it.bff.biometricprompt.protectedActivity;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

class PermissionManager {

    static final int REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA = 500;

    // return true if the caller activity already have all the below permissions
    static boolean askPermission(String[] allPermissions, Activity activity, int requestCode)
    {

        List<String> neededPermissions = new ArrayList<>();

        for(String s: allPermissions) {
            if(ContextCompat.checkSelfPermission(activity.getBaseContext(), s) != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(s);
            }
        }

        if(!neededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    activity,
                    neededPermissions.toArray(new String[neededPermissions.size()]),
                    requestCode);
            return false;
        }

        return true;
    }

    static boolean isAllGranted(@NonNull int[] grantResults) {

        for (int grant: grantResults) {
            if(grant == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }

        return true;
    }

}
