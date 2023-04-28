package it.bff.biometricprompt.mainActivity;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import it.bff.biometricprompt.R;

class ModalDialogManager
{

    // Open the criticalAlert Dialog
    static void openCriticalAlert(final MainActivity activity)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setCancelable(false);
        alert.setTitle(R.string.dialog_critical_title);
        alert.setMessage(R.string.dialog_critical_message);
        alert.setPositiveButton(R.string.dialog_critical_btn_close, new Holder(activity));

        alert.show();
    }

    // Listener for criticalAlert Dialog (positive button)
    static class Holder implements DialogInterface.OnClickListener
    {
        private MainActivity activity;
        Holder(MainActivity activity)
        {
            this.activity = activity;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            activity.finish();
        }
    }

    // Open the enrollmentAlert Dialog
    static void openEnrollmentAlert(final MainActivity activity)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setCancelable(false);
        alert.setTitle(R.string.dialog_enroll_title);
        alert.setMessage(R.string.dialog_enroll_message);
        alert.setPositiveButton(R.string.dialog_enroll_btn_ok, new HolderEnrollment(activity));
        alert.setNegativeButton(R.string.dialog_enroll_btn_back, new HolderEnrollmentBackBtn());

        alert.show();
    }

    // Listener for enrollmentAlert Dialog (positive button)
    static class HolderEnrollment implements DialogInterface.OnClickListener
    {
        private MainActivity activity;
        HolderEnrollment(MainActivity activity)
        {
            this.activity = activity;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            activity.startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
        }
    }

    // Listener for enrollmentAlert Dialog (negative button)
    static class HolderEnrollmentBackBtn implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            dialogInterface.cancel();
        }
    }
}
