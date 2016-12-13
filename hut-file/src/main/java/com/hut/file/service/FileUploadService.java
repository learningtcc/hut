package com.hut.file.service;

import com.hut.file.pojos.FileUploadBean;

/**
 * Created by Jared on 2016/12/11.
 */
public interface FileUploadService {

    boolean upload(FileUploadBean form, byte[] data);

    boolean upload(FileUploadBean form);

    CloudFile getCloudFileByPath(String path);

    byte[] getData(CloudFile file);

    int getSumByUserId(String userId);

    int getTotalSizeByUserId(String userId);
}
