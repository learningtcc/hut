package com.hut.sso.pojos;

import com.hut.common.model.PojoPersistent;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Jared on 016/12/11.
 */
public class User implements PojoPersistent {

    private int id;
    private LocalDateTime createdAt;
    private boolean trash;

    private String userName;
    private String password;
    private String photoUrl;
    private String name;
    private LocalDate birthDay;
    private String gender;
    private String identity;
    private String phonenumber;
    private String email;

    private String country;
    private String province;
    private String city;
    private String school;
    private String[] hobby;



    public int getAge(){
        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();
        int birthYeah = this.getBirthDay().getYear();
        int age = nowYear-birthYeah;
        return age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }
}
