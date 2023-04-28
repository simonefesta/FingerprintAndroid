package it.bff.biometricprompt.protectedActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import it.bff.biometricprompt.R;


public class ProtectedActivity extends AppCompatActivity {

    static final String INDEX_FILE = "index";
    static final String PICT_DEF_NAME = "picture";
    static final int GET_PICTURE_CODE = 100;
    static final int TAKE_PICTURE_CODE = 101;

    private ProtectedActivityHolder holder;

    public ProtectedActivityHolder getHolder() { return this.holder; }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);

        holder = new ProtectedActivityHolder(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED) {
            return;
        }

        // picture selected from gallery
        if(requestCode == GET_PICTURE_CODE) {
            if(data == null) {
                holder.printToastMessage(getResources().getString(R.string.toast_select_photo_err_1));
                return;
            }
            Uri imageUri = data.getData();

            if(imageUri == null) {
                holder.printToastMessage(getResources().getString(R.string.toast_select_photo_err_1));
                return;
            }

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);

            if(cursor == null) {
                holder.printToastMessage(getResources().getString(R.string.toast_select_photo_err_2));
                return;
            }

            cursor.moveToFirst();
            int columnIndexPath = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndexPath);
            String pictureTitle = parseLastNameFromPath(picturePath);

            InputStream is = null;
            try {
                is = getContentResolver().openInputStream(imageUri);
            }catch (FileNotFoundException e) {
                holder.printToastMessage(getResources().getString(R.string.toast_select_photo_err_2));
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);

            storeNewPhoto(bitmap, pictureTitle);

            cursor.close();
        }
        // picture taken by camera
        else if(requestCode == TAKE_PICTURE_CODE) {
            if(data == null) {
                holder.printToastMessage(getResources().getString(R.string.toast_take_photo_err));
                return;
            }
            Bitmap bitmap = data.getParcelableExtra("data");

            storeNewPhoto(bitmap, PICT_DEF_NAME);
        }
    }

    private void storeNewPhoto(Bitmap bitmap, String newPhotoName) {


        // retrieve the name of stored photos
        List<String> imageNameList = StoreManager.loadTextFile(INDEX_FILE, StoreManager.FileFormat.FORMAT_DATA, StoreManager.FileType.TYPE_DOCUMENT, this);

        newPhotoName = createUniqueNameInList(imageNameList, newPhotoName, 0);

        // store the bitmap
        boolean stored = StoreManager.storeImageBitmap(bitmap, newPhotoName, StoreManager.ImageFormat.FORMAT_JPEG, StoreManager.FileType.TYPE_PICTURES, this);

        if(stored)
        {

            StringBuilder text = new StringBuilder();

            if (imageNameList == null)
                imageNameList = new ArrayList<>();

            // build a string adding the new photo to the older
            for (int i = 0; i < imageNameList.size(); i++)
            {
                text.append(imageNameList.get(i)).append("\n");
            }
            text.append(newPhotoName);

            // write the index file with the new photo
            StoreManager.storeTextFile(ProtectedActivity.INDEX_FILE, text.toString(), StoreManager.FileFormat.FORMAT_DATA, StoreManager.FileType.TYPE_DOCUMENT, this);

            // re-build the recycler view
            holder.buildRecyclerViewGallery();
        }

    }

    String createUniqueNameInList(List<String> stringList, String string, int num) {

        String toReturn = string;

        if(stringList == null)
            return string;

        if(stringList.size() == 0)
            return string;

        if(num != 0)
            toReturn = toReturn + "-" + num;

        for(int i=0; i<stringList.size(); i++) {
            if(stringList.get(i).equals(toReturn))
                return createUniqueNameInList(stringList, string, num + 1);
        }

        return toReturn;
    }

    private String parseLastNameFromPath(String path) {

        // split and get the last name of the path
        String[] words = path.split("/");
        String lastName = words[words.length - 1];

        // spilt to remove the .* part
        words = lastName.split("\\.");
        return words[0];
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(PermissionManager.isAllGranted(grantResults)) {
            if(requestCode == PermissionManager.REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA) {
                launchActivity(PermissionManager.REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA);
            }
        }
        else {
            holder.printToastMessage(getResources().getString(R.string.toast_permission_denied));
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    void launchActivity(int requestCode) {

        if (requestCode == PermissionManager.REQUEST_CODE_PERMISSION_ACTIVITY_CAMERA) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, TAKE_PICTURE_CODE);
            } else {
                holder.printToastMessage(getResources().getString(R.string.toast_error_opening_camera));
            }
        }
    }

}
