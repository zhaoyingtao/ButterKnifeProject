package com.test.dhf.butterknifeproject;

import android.app.Application;
import android.content.Context;

import com.test.dhf.butterknifeproject.gen.DaoMaster;
import com.test.dhf.butterknifeproject.gen.DaoSession;

/**
 * Created by dhf on 2017/2/24.
 */

public class App extends Application {
    public final static String dbName = "zyt_db";
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;




    public static DaoMaster getDaoMaster(Context mContext) {
        if (sDaoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(mContext, dbName);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public static DaoSession getDaoSession(Context mContext) {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster(mContext);
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
