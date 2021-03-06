package com.hut.manage.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hut.common.model.PojoPersistent;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Jared on 016/12/11.
 */

/*
    1.表名默认使用类名,驼峰转下划线(只对大写字母进行处理),如UserInfo默认对应的表名为user_info。
    2.表名可以使用@Table(name = "tableName")进行指定,对不符合第一条默认规则的可以通过这种方式指定表名.
    3.字段默认和@Column一样,都会作为表字段,表字段默认为Java对象的Field名字驼峰转下划线形式.
    4.可以使用@Column(name = "fieldName")指定不符合第3条规则的字段名
    5.使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用.
    6.建议一定是有一个@Id注解作为主键的字段,可以有多个@Id注解的字段作为联合主键.
    7.默认情况下,实体类中如果不存在包含@Id注解的字段,所有的字段都会作为主键字段进行使用(这种效率极低).
    8.实体类可以继承使用,可以参考测试代码中的tk.mybatis.mapper.model.UserLogin2类.
    9.由于基本类型,如int作为实体类字段时会有默认值0,而且无法消除,所以实体类中建议不要使用基本类型.
    10.@NameStyle注解，用来配置对象名/字段和表名/字段之间的转换方式，该注解优先于全局配置style，可选值：
        · normal:使用实体类名/属性名作为表名/字段名
        · camelhump:这是默认值，驼峰转换为下划线形式
        · uppercase:转换为大写
        · lowercase:转换为小写
* */

//默认和类名相同 UserInfo：user_info  @NameStyle  @Table  加一个就可以
//@NameStyle(Style.normal)
@Table(name="user")
@Entity
public class User implements PojoPersistent {


    //由于基本类型,如int作为实体类字段时会有默认值0,而且无法消除,所以实体类中建议不要使用基本类型.
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Date createdAt;
    private boolean trash;

    @Size(message = "用户名必须是8~12位",min = 8,max = 12)
    @NotNull
    private String userName;

    @Size(message = "密码必须是8~12位",min = 8,max = 12)
    @NotNull
    private String password;

    private String photoUrl;

    private String name;

    private Date birthday;

    private String gender;

    private String identity;

    @Pattern(regexp ="^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$" ,message = "手机号码不合规")
    private String phone;

    @Email(message = "邮箱地址不合规")
    private String email;


    @Transient
    @JsonIgnore
    public int getAge(){
        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();
        int birthYeah = this.getBirthday().getYear();
        int age = nowYear-birthYeah;
        return age;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean isTrash() {
        return trash;
    }

    @Override
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", trash=" + trash +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", identity='" + identity + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
