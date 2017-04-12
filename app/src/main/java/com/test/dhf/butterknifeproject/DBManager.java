package com.test.dhf.butterknifeproject;


import android.content.Context;

import com.test.dhf.butterknifeproject.gen.DaoSession;
import com.test.dhf.butterknifeproject.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dhf on 2017/2/23.
 */

public class DBManager {
    private static DBManager mDBManager;
    private UserDao userDao;
    public static DBManager getDBManagerInstance(Context mContext) {
        if (mDBManager == null){
            synchronized (DBManager.class){
                mDBManager = new DBManager();
                DaoSession daoSession = App.getDaoSession(mContext);
                mDBManager.userDao = daoSession.getUserDao();
            }
        }
        return mDBManager;
    }

    public void insertUser(User user){
        userDao.insert(user);
    }
    public void insertUserList(List<User> users){
        if (users == null || users.isEmpty()){
            return;
        }
        userDao.insertInTx(users);
    }

    public void deleteAll(){
        userDao.deleteAll();
    }
    public void deleteUser(User user){
        userDao.delete(user);
    }
    public void upDateUser(User user){
        userDao.update(user);
    }
    public List<User> queryUserList() {
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    public List<User> queryUsesList(int age){
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        List<User> list = qb.list();
        return list;
    }
}
