package it.bff.biometricprompt.mainActivity;

import androidx.biometric.BiometricManager;

class CheckFingerPrint
{
    private MainActivity activity;

    CheckFingerPrint(MainActivity activity)
    {
        this.activity = activity;
    }

    boolean check() {
        BiometricManager biometricManager =
                BiometricManager.from(activity.getApplicationContext());
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                //  App can authenticate using biometrics
                return true;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                // Biometric features are currently unavailable
                // No biometric features available on this device
                ModalDialogManager.openCriticalAlert(activity);
                return false;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // The user hasn't associated any biometric credentials with their account
                ModalDialogManager.openEnrollmentAlert(activity);
                return false;
        }

        return false;
    }
}

