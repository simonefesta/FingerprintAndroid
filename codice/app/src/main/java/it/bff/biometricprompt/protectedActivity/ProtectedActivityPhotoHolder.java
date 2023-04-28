package it.bff.biometricprompt.protectedActivity;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import it.bff.biometricprompt.R;
import it.bff.biometricprompt.mainActivity.AnimationInterpolator;

class ProtectedActivityPhotoHolder extends RecyclerView.ViewHolder {

    private ProtectedActivity activity;
    private ImageView ivPhoto;
    private TextView tvName;
    private String path;
    private Animation animOnLongClick;

    ProtectedActivityPhotoHolder(@NonNull ConstraintLayout cl, ProtectedActivity activity) {
        super(cl);

        this.activity = activity;
        ivPhoto = cl.findViewById(R.id.ivPhoto);
        tvName = cl.findViewById(R.id.tvName);

        // create the listeners
        ProtectedActivityPhotoListener listener = new ProtectedActivityPhotoListener(activity, this);
        ProtectedActivityPhotoLongListener listenerLong = new ProtectedActivityPhotoLongListener(activity, this);

        ivPhoto.setOnClickListener(listener);
        cl.setOnClickListener(listener);
        cl.setOnLongClickListener(listenerLong);

        // create the animation
        animOnLongClick = AnimationUtils.loadAnimation(activity, R.anim.on_press_animation);
        AnimationInterpolator interpolator = new AnimationInterpolator(0.1, 10);
        animOnLongClick.setInterpolator(interpolator);

        ProtectedActivityAnimListener animListener = new ProtectedActivityAnimListener(activity, this);
        animOnLongClick.setAnimationListener(animListener);
    }

    int getConstraintLayoutId() {
        return R.id.clRow;
    }
    int getIvPhotoId() {
        return R.id.ivPhoto;
    }

    public ProtectedActivity getActivity() { return this.activity; }
    Animation getAnimOnLongClick() { return animOnLongClick; }

    String getPath() {
        return path;
    }

    void setIvPhotoByPath(String path) {

        this.path = path;

        // retrieve the photo from internal storage
        Bitmap bitmap = StoreManager.loadImageBitmap(path,
                StoreManager.ImageFormat.FORMAT_JPEG,
                StoreManager.FileType.TYPE_PICTURES,
                activity);

        setIvPhoto(bitmap);
    }

    private void setIvPhoto(Bitmap bitmap) {
        ivPhoto.setImageBitmap(bitmap);
    }

    void setTvNameText(String text) {
        tvName.setText(text);
    }

    boolean deletePhoto()
    {
        if(StoreManager.deleteSpecificRowTextFile(this.getPath(),activity))
            return StoreManager.deleteImage(this.getPath(), StoreManager.ImageFormat.FORMAT_JPEG,
                StoreManager.FileType.TYPE_PICTURES,
                activity);
        return false;
    }
}
