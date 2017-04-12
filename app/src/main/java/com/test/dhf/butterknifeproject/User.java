package com.test.dhf.butterknifeproject;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by dhf on 2017/2/23.
 */
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int age;
    private String passWord;
@Generated(hash = 160832684)
public User(Long id, String name, int age, String passWord) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.passWord = passWord;
}

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
