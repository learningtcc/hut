package com.hut.file.dao;

import com.hut.file.pojos.PersistentFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jared on 2016/12/14.
 */
public interface FileUploadMapper {

    @Select("select * from cloud_files where trash=0 and md5=#{md5} limit 1")
    PersistentFile getFileByMd5(String md5);

    @Insert("insert into cloud_files (createdAt,userId,appId,path,filename,size,contentType,md5,fileIndex,acl) " +
            "values(#{createdAt},#{userId},#{appId},#{path},#{filename},#{size},#{contentType},#{md5},#{fileIndex},#{acl})")
    void insertFile(PersistentFile file);

    @Select("select * from cloud_files where trash=0 and path=#{path}")
    PersistentFile getFileByPath(String path);

    @Select("select count(*) from cloud_files where trash=0 and userId=#{userId}")
    int sumByUserId(int userId);

    @Select("select ifnull(0,sum(size)) from cloud_files where trash=0 and userId=#{userId}")
    int getTotalSizeByUserId(int userId);
}
