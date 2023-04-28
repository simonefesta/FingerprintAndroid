package it.bff.biometricprompt.mainActivity;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;

import it.bff.biometricprompt.R;
import it.bff.biometricprompt.protectedActivity.ProtectedActivity;

public class BiometricOurAuthentication extends BiometricPrompt.AuthenticationCallback
{
    private MainActivity activity;

    BiometricOurAuthentication(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString)
    {
        super.onAuthenticationError(errorCode, errString);
        Toast.makeText(activity.getApplicationContext(), errString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result)
    {
        super.onAuthenticationSucceeded(result);

        Intent intent = new Intent(activity, ProtectedActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onAuthenticationFailed()
    {
        super.onAuthenticationFailed();
        Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.toast_auth_err), Toast.LENGTH_SHORT).show();
    }
}
