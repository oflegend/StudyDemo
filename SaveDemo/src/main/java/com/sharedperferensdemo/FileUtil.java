package com.sharedperferensdemo;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by D&LL on 2017/3/26.
 */

public class FileUtil {
    public static String FILE_NAME = "file_data";
    public static String SD_FILE_NAME = "/sd_file_data";

    public static String toRead(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            while ((hasRead = fis.read(buff)) > 0) {
                sb.append(new String(buff, 0, hasRead));
            }
            fis.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toWrite(Context context, String string) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            PrintStream ps = new PrintStream(fos);
            ps.println(string);//存储格式，换行
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toReadSD(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File sdCardDir = Environment.getExternalStorageDirectory();
                FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + "/" + context.getResources().getString(R.string.app_name) + SD_FILE_NAME);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line = null;
                StringBuilder sb = new StringBuilder("");//存储文件内容的字符串
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                fis.close();
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void toWriteSD(Context context, String string) {
        createDir(context);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + "/" + context.getResources().getString(R.string.app_name) + SD_FILE_NAME);
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                raf.seek(targetFile.length());
                raf.write(string.getBytes());//换行
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFile(String path) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String sdPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                File files = new File(sdPath + path);
                files.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> fileList(String path) {
        ArrayList<String> fileName = new ArrayList<>();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String sdPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                File file = new File(sdPath + path);
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    fileName.add(files[i].getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public static void createDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + "/" + context.getResources().getString(R.string.app_name));
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createFile(Context context, String name) {
        File txt = null;
        try {
            txt = new File(Environment.getExternalStorageDirectory().getCanonicalPath() + "/" +
                    context.getResources().getString(R.string.app_name) + "/" + name);
            if (!txt.exists()) {//判读文件是否存在
                txt.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
