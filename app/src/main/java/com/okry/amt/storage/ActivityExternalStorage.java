package com.okry.amt.storage;

import android.os.Bundle;
import android.os.Environment;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/3/25.
 */
public class ActivityExternalStorage extends BaseActivity {

    private static final String EXTERNAL_ACCESS = "ExternalStorageAccess:[%s]=[%s]";
    private static final String EXTERNAL_DIR = "CommonDir:[%s]=[%s]";
    private static final String EXTERNAL_PUBLIC = "PublicExternalStorage:[%s]=[%s]";
    private static final String EXTERNAL_PRIVATE = "PrivateExternalStorage:[%s]=[%s]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printAccessable();
        printCommonDir();
        printExternalStoragePublicDir();
        printPrivateExternalStorage();
    }

    private void printPrivateExternalStorage() {
        L.d(EXTERNAL_PRIVATE, "getExternalCacheDir", getExternalCacheDir());
        L.d(EXTERNAL_PRIVATE, "getExternalFilesDir", getExternalFilesDir(null));
        L.d(EXTERNAL_PRIVATE, "getExternalFilesDir(DIRECTORY_DCIM)", getExternalFilesDir(Environment.DIRECTORY_DCIM));

    }

    private void printCommonDir() {
        L.d(EXTERNAL_DIR, "Environment.getExternalStorageState()", Environment.getExternalStorageState());
        L.d(EXTERNAL_DIR, "Environment.getRootDirectory()", Environment.getRootDirectory());
        L.d(EXTERNAL_DIR, "Environment.getExternalStorageDirectory()", Environment.getExternalStorageDirectory());
        L.d(EXTERNAL_DIR, "Environment.getDownloadCacheDirectory()", Environment.getDownloadCacheDirectory());
        L.d(EXTERNAL_DIR, "Environment.getDataDirectory()", Environment.getDataDirectory());
    }

    private void printExternalStoragePublicDir() {
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_DCIM", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_ALARMS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_DOWNLOADS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_MOVIES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_MUSIC", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_NOTIFICATIONS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_PICTURES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_PODCASTS", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
        L.d(EXTERNAL_PUBLIC, "DIRECTORY_RINGTONES", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    private void printAccessable() {
        L.d(EXTERNAL_ACCESS, "isExternalStorageReadable()", ExternalUtils.isExternalStorageReadable());
        L.d(EXTERNAL_ACCESS, "isExternalStorageWritable()", ExternalUtils.isExternalStorageWritable());
    }


}
