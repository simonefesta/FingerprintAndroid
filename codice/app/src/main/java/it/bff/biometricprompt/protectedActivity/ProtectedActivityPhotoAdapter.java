package it.bff.biometricprompt.protectedActivity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.bff.biometricprompt.R;

public class ProtectedActivityPhotoAdapter extends RecyclerView.Adapter<ProtectedActivityPhotoHolder> {

    private ProtectedActivity activity;
    private List<String> imageNameList;
    private int numberOfItem;

    ProtectedActivityPhotoAdapter(List<String> imageNameList, ProtectedActivity activity) {
        this.imageNameList = imageNameList;
        this.activity = activity;
        numberOfItem = imageNameList.size();
    }

    @NonNull
    @Override
    public ProtectedActivityPhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout cl = (ConstraintLayout)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_protected_photo, parent, false);
        return new ProtectedActivityPhotoHolder(cl, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ProtectedActivityPhotoHolder holder, int position) {

        holder.setIvPhotoByPath(imageNameList.get(position));
        holder.setTvNameText(imageNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return numberOfItem;
    }
}
