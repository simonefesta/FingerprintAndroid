package it.bff.biometricprompt.protectedActivity;

import android.view.View;

public class ProtectedActivityPhotoLongListener implements View.OnLongClickListener {

    private ProtectedActivity activity;
    private ProtectedActivityPhotoHolder holder;

    ProtectedActivityPhotoLongListener(ProtectedActivity activity, ProtectedActivityPhotoHolder holder) {
        this.holder = holder;
        this.activity = activity;
    }

    @Override
    public boolean onLongClick(View view)
    {

        if(view.getId() == holder.getConstraintLayoutId())
        {

            holder.deletePhoto();

            holder.getActivity().getHolder().buildRecyclerViewGallery();
        }

        return true;
    }
}
