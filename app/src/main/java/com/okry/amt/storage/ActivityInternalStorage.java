package com.okry.amt.storage;

import android.content.Context;
import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;
import org.apache.http.util.EncodingUtils;

import java.io.*;

/**
 * Created by MR on 2014/3/25.
 */
public class ActivityInternalStorage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testPrivateFile();
        testPrivateFile();
        testAppendFile();
        testReadFile();
        testWorldFile();
        testCacheFile();
        printPath();
    }

    private static final String PATH = "FilePath:[%s]=[%s]";

    private void printPath() {
        L.d(PATH, "getFilesDir()", getFilesDir());
        L.d(PATH, "getCacheDir()", getCacheDir());
        L.d(PATH, "getDir()", getDir("test_get_dir", MODE_PRIVATE));
        String [] files = fileList();
        for(int i = 0; i < files.length; i++) {
            L.d(PATH, "fileList" + i, files[i]);
        }
    }

    private void testPrivateFile() {
        String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void testAppendFile() {
        String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(string.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void testReadFile() {
        String FILENAME = "hello_file";

        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(openFileInput(FILENAME));
            int length = in.available();
            byte[] bytes = new byte[length];
            in.read(bytes);
            String str = EncodingUtils.getString(bytes, "UTF-8");
            L.d("read file[%s]", str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void testWorldFile() {
        String FILENAME = "hello_world_file";
        String string = "hello world writeable";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_WORLD_WRITEABLE | MODE_WORLD_READABLE);
            fos.write(string.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void testCacheFile() {
        String CACHEFILENAME = "hello_cache_file";
        String string = "hello world cache file";

        FileOutputStream fos = null;
        try {
            File f = new File(getCacheDir().getPath() + File.separatorChar + CACHEFILENAME);
            fos = new FileOutputStream(f);
            fos.write(string.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
