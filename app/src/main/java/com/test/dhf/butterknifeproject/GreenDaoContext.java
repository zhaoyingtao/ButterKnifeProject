package com.test.dhf.butterknifeproject;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by dhf on 2017/3/1.
 */

public class GreenDaoContext extends ContextWrapper {
    private String currentUserId = "greendao";//一般用来针对一个用户一个数据库，以免数据混乱问题
    private Context mContext;
    private Activity mActivity;

    public GreenDaoContext(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        mActivity = (Activity) mContext;
//        this.currentUserId = "greendao";//初始化
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        String dbDir = getDBPath();
        if (TextUtils.isEmpty(dbDir)) {
            Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
            return null;
        }
        File baseFile = new File(dbDir);
        // 目录不存在则自动创建目录
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        buffer.append(File.separator);
        buffer.append(currentUserId);
        dbDir = buffer.toString();// 数据库所在目录
        buffer.append(File.separator);
//        buffer.append(dbName+"_"+currentUserId);//也可以采用此种方式，将用户id与表名联系到一块命名
        buffer.append(dbName);
        String dbPath = buffer.toString();// 数据库路径
        // 判断目录是否存在，不存在则创建该目录
        File dirFile = new File(dbDir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 数据库文件是否创建成功
        boolean isFileCreateSuccess = false;
        // 判断文件是否存在，不存在则创建该文件
        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();// 创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            isFileCreateSuccess = true;
        // 返回数据库文件对象
        if (isFileCreateSuccess)
            return dbFile;
        else
            return super.getDatabasePath(dbName);
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
     * android.database.sqlite.SQLiteDatabase.CursorFactory,
     * android.database.DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

    /**
     * 数据库存放地址
     * @return
     */
    private String getDBPath() {
        String dbPath;

//        PackageManager packageManager = mContext.getPackageManager();
//        packageManager.getInstallerPackageName();
        dbPath =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getPackageName()+"/database";
        Log.e("zyt","______--getExternalStorageDirectory11111---------"+Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.e("zyt","______getPackageName--2222222---------"+getPackageName());
        Log.e("zyt","______-getDataDirectory-333333---------"+Environment.getDataDirectory().getPath());
        Log.e("zyt","______-getRootDirectory----------"+Environment.getRootDirectory().getPath());
        return dbPath;
    }
}
