package com.hut.common.model;

import java.util.Date;

/**
 * Created by Jared on 2016/12/14.
 */
public interface PojoPersistent {

    /**id，方便扩展*/
    Integer getId();
    void setId(Integer id);

    /**创建时间*/
    Date getCreatedAt();
    void setCreatedAt(Date createdAt);

    /**是否废弃*/
    boolean isTrash();
    void setTrash(boolean trash);
}
