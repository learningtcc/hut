package com.hut.manage.pojos;

import com.hut.common.model.PojoPersistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jared on 016/12/11.
 */

//默认和类名相同 UserInfo：user_info  @NameStyle  @Table  加一个就可以
//@NameStyle(Style.normal)

@Entity
@Table(name="manage_user")
public class User implements PojoPersistent {

    //由于基本类型,如int作为实体类字段时会有默认值0,而且无法消除,所以实体类中建议不要使用基本类型.
    @Id
    private Integer id;
    //默认驼峰
    @Column(name="createdAt")
    private Date createdAt;
    private boolean trash;

    private String userName;
    private String password;

    @Override
    public Integer getId() {
        return id;
    }

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
}
