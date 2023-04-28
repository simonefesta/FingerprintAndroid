package it.bff.biometricprompt.protectedActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import it.bff.biometricprompt.R;

class ProtectedActivityHolder {

    private ProtectedActivity activity;
    private RecyclerView rvGallery;
    private TextView tvNoPhotoLog;

    ProtectedActivityHolder(ProtectedActivity activity) {

        this.activity = activity;

        FloatingActionButton fabGetPhoto = activity.findViewById(getFabGetPhotoId());
        FloatingActionButton fabTakePhoto = activity.findViewById(getFabTakePhotoId());
        rvGallery = activity.findViewById(R.id.rvGallery);
        tvNoPhotoLog = activity.findViewById(R.id.tvNoPhotoLog);
        buildRecyclerViewGallery();

        ProtectedActivityBtnListener listener = new ProtectedActivityBtnListener(activity, this);

        fabGetPhoto.setOnClickListener(listener);
        fabTakePhoto.setOnClickListener(listener);
    }

    int getFabGetPhotoId() {
        return R.id.fabGetPhoto;
    }
    int getFabTakePhotoId() {
        return R.id.fabTakePhoto;
    }

    void buildRecyclerViewGallery() {

        // retrieve the images' name
        List<String> imageNameList = StoreManager.loadTextFile(ProtectedActivity.INDEX_FILE,
                StoreManager.FileFormat.FORMAT_DATA,
                StoreManager.FileType.TYPE_DOCUMENT,
                activity);

        if(imageNameList == null) {
            tvNoPhotoLog.setVisibility(View.VISIBLE);
        }
        else {
            // build the UI component
            tvNoPhotoLog.setVisibility(View.INVISIBLE);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
            RecyclerView.Adapter adapter = new ProtectedActivityPhotoAdapter(imageNameList, activity);

            rvGallery.setLayoutManager(layoutManager);
            rvGallery.setAdapter(adapter);
        }

    }

    void printToastMessage(CharSequence msg) {

        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.show();
    }

}
