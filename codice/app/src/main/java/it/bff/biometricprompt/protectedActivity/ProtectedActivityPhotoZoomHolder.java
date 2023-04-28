package it.bff.biometricprompt.protectedActivity;

import android.graphics.Bitmap;
import android.widget.ImageView;

import it.bff.biometricprompt.R;

class ProtectedActivityPhotoZoomHolder {

    ProtectedActivityPhotoZoom activity;
    private ImageView ivPhoto;

    ProtectedActivityPhotoZoomHolder(ProtectedActivityPhotoZoom activity) {

        this.activity = activity;

        ivPhoto = activity.findViewById(R.id.ivPhoto);
    }

    private void setIvPhoto(Bitmap bitmap) {
        ivPhoto.setImageBitmap(bitmap);
    }

    void setIvPhotoByPath(String path) {


        // retrieve the photo from internal storage
        Bitmap bitmap = StoreManager.loadImageBitmap(path,
                StoreManager.ImageFormat.FORMAT_JPEG,
                StoreManager.FileType.TYPE_PICTURES,
                activity);

        setIvPhoto(bitmap);
    }
}
