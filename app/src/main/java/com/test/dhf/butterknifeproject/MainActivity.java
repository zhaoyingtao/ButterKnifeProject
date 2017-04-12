package com.test.dhf.butterknifeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    Button mTextView;
    @BindView(R.id.textView01)
    Button mTextView01;
    @BindView(R.id.textView02)
    Button mTextView02;
    @BindView(R.id.textView03)
    Button mTextView03;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.resultText)
    TextView mResultText;
    private DBManager mDBManager;

    StringBuffer resultString = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDBManager = DBManager.getDBManagerInstance(new GreenDaoContext(this));
    }

    @OnClick({R.id.textView, R.id.textView01, R.id.textView02, R.id.textView03, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView:
                deletOneData(0);
                break;
            case R.id.textView01:
                insertDB();
                break;
            case R.id.textView02:
                queryListDB();
                break;
            case R.id.textView03:
                initDB();
                break;
            case R.id.button:
                updatebtn(3);
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initDB() {
        mDBManager.deleteAll();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId((long) i);
            user.setAge(i * 10);
            user.setName("第" + i + "人");
            user.setPassWord("123131313131");
            mDBManager.insertUser(user);
            resultString.append("insertDB:name="+user.getName()+"; age="+user.getAge()+"; setPassWord="+user.getPassWord() + "\n");
        }
        mResultText.setText(resultString.toString());
    }

    /**
     * 插入数据
     */
    private void insertDB(){
        resultString.delete(0,resultString.length());
        List<User> userList = mDBManager.queryUserList();
        int len = userList.size();
        User user = new User();
        user.setId((long) len);
        user.setAge(len * 3);
        user.setName("第" + len + "人");
        mDBManager.insertUser(user);


        userList = mDBManager.queryUserList();
        int leng = userList.size();
        for (int i = 0; i<leng;i++){
            User u = userList.get(i);
            Log.e("db", "insertDB:name="+u.getName()+"; age="+u.getAge());
            resultString.append("insertDB:name="+u.getName()+"; age="+u.getAge() + "\n");
        }
        mResultText.setText(resultString.toString());
    }

    /**
     * 查询所有数据
     */
    private void queryListDB(){
        resultString.delete(0,resultString.length());
        List<User> userList = mDBManager.queryUserList();
        for (User user : userList) {
            Log.e("db", "insertDB:name=" + user.getName() + "; age=" + user.getAge());
            resultString.append("insertDB:name="+user.getName()+"; age="+user.getAge() + "\n");
        }
        mResultText.setText(resultString.toString());
    }

    /**
     * 删除数据
     */
    private void deletOneData(int delet){
        resultString.delete(0,resultString.length());
        List<User> userList = mDBManager.queryUserList();
        for (User user:userList){
            if (delet == user.getId()){
                mDBManager.deleteUser(user);
            }
        }

        userList = mDBManager.queryUserList();
        for (User user : userList) {
            Log.e("db", "insertDB:name=" + user.getName() + "; age=" + user.getAge());
            resultString.append("insertDB:name="+user.getName()+"; age="+user.getAge() + "\n");
        }
        mResultText.setText(resultString.toString());
    }

    /**
     * 更新某一条数据
     */
    private void updatebtn(int item){
        resultString.delete(0,resultString.length());
        List<User> userList = mDBManager.queryUserList();
        for (User user:userList){
            if (item == user.getId()){
                user.setAge(10000);
                user.setName("张三");
                mDBManager.upDateUser(user);
            }
        }

        userList = mDBManager.queryUserList();
        for (User user : userList) {
            Log.e("db", "insertDB:name=" + user.getName() + "; age=" + user.getAge());
            resultString.append("insertDB:name="+user.getName()+"; age="+user.getAge() + "\n");
        }
        mResultText.setText(resultString.toString());
    }

}
