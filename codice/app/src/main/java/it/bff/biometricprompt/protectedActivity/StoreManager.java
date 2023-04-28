package it.bff.biometricprompt.protectedActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class StoreManager {

    public static List<String> loadTextFile(String fileName, FileFormat format, FileType fileType, Activity activity) {

        File path = buildPath(fileType, activity);
        if (path == null)
            return null;

        List<String> lines = new ArrayList<>();
        try {

            InputStream is = new FileInputStream(path + "/" + fileName + format.getExtName());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            is.close();
        } catch (IOException e) {
            return null;
        }

        return lines;
    }

    public static boolean storeTextFile(String fileName, String text, FileFormat format, FileType fileType, Activity activity) {

        File path = buildPath(fileType, activity);
        if (path == null)
            return false;

        try {
            FileOutputStream fos = new FileOutputStream(path + "/" + fileName + format.getExtName());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static Bitmap loadImageBitmap(String pictureName, ImageFormat format, FileType fileType, Activity activity) {

        File path = buildPath(fileType, activity);
        if (path == null)
            return null;

        File file = new File(path, pictureName + format.getExtName());
        InputStream is;
        Bitmap bitmap;
        try {
            is = new FileInputStream(file);

            byte[] data = new byte[is.available()];

            if (is.read(data) <= 0)
                return null;

            is.close();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (IOException e) {
            return null;
        }

        return bitmap;
    }

    public static boolean storeImageViewBackground(ImageView iv, @Nullable String pictureName, ImageFormat format, FileType fileType, Activity activity) {
        Bitmap bitmap;
        try {
            bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
        } catch (Exception e) {
            return false;
        }

        if (pictureName == null)
            pictureName = Integer.toString(iv.getId());

        return storeImageBitmap(bitmap, pictureName, format, fileType, activity);
    }

    public static boolean storeImageBitmap(Bitmap bitmap, String pictureName, ImageFormat format, FileType fileType, Activity activity) {

        File path = buildPath(fileType, activity);
        if (path == null)
            return false;

        File file = new File(path, pictureName + format.getExtName());
        OutputStream os;
        try {
            os = new FileOutputStream(file);
            bitmap.compress(format.getFormat(), 100, os);
            os.flush();
            os.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }


    public static boolean deleteSpecificRowTextFile(String specificRow, Activity activity)
    {
        List<String> listNames;
        StringBuilder text = new StringBuilder();
        listNames = loadTextFile(ProtectedActivity.INDEX_FILE, StoreManager.FileFormat.FORMAT_DATA, StoreManager.FileType.TYPE_DOCUMENT, activity);
        for (int i = 0; i < listNames.size(); i++) {
            if (!listNames.get(i).equals(specificRow))
                text.append(listNames.get(i)).append("\n");
        }
        return storeTextFile(ProtectedActivity.INDEX_FILE, text.toString(), StoreManager.FileFormat.FORMAT_DATA, StoreManager.FileType.TYPE_DOCUMENT, activity);

    }

    public static boolean deleteImage(String pictureName, ImageFormat format, FileType fileType, Activity activity)
    {
        File path = buildPath(fileType, activity);
        if (path == null)
            return false;

        File file = new File(path, pictureName + format.getExtName());
        return file.delete();
    }
    private static File buildPath(FileType fileType, Activity activity) {

        File path;
        //  /data/data/it.bff.biometricprompt/files/saveLocation
        switch(fileType) {
            case TYPE_PICTURES:
                path = new File(activity.getFilesDir() + "/" + FileType.TYPE_PICTURES.getName());
                break;
            case TYPE_DOWNLOAD:
                path = new File(activity.getFilesDir() + "/" + FileType.TYPE_DOWNLOAD.getName());
                break;
            case TYPE_DOCUMENT:
                path = new File(activity.getFilesDir() + "/" + FileType.TYPE_DOCUMENT.getName());
                break;
            default:
                return null;
        }

        if(!path.exists()) {
            if(!path.mkdir())
                return null;
        }

        return path;
    }

    public enum FileType {
        TYPE_PICTURES("Pictures"),
        TYPE_DOWNLOAD("Download"),
        TYPE_DOCUMENT("Documents");

        private String name;

        FileType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum ImageFormat {
        FORMAT_PNG(Bitmap.CompressFormat.PNG, ".png"),
        FORMAT_WEBP(Bitmap.CompressFormat.WEBP, ".webp"),
        FORMAT_JPEG(Bitmap.CompressFormat.JPEG, ".jpg"),
        FORMAT_GIF(Bitmap.CompressFormat.JPEG, ".gif");

        Bitmap.CompressFormat format;
        String extName;

        ImageFormat(Bitmap.CompressFormat format, String extName) {
            this.format = format;
            this.extName = extName;
        }

        public Bitmap.CompressFormat getFormat() {
            return format;
        }
        public String getExtName() {
            return extName;
        }
    }

    public enum FileFormat {
        FORMAT_TXT(".txt"),
        FORMAT_DATA(".data");

        String extName;

        FileFormat(String extName) {
            this.extName = extName;
        }

        public String getExtName() {
            return extName;
        }
    }

}
