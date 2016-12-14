package com.hut.file.service;

import com.hut.file.pojos.FileUploadBean;
import com.hut.file.pojos.PersistentFile;

/**
 * Created by Jared on 2016/12/11.
 */
public interface FileUploadService {

    boolean upload(FileUploadBean form, byte[] data);

    boolean upload(FileUploadBean form);

    PersistentFile getPersistentFileByPath(String path);

    byte[] getData(PersistentFile file);

    int getSumByUserId(int userId);

    int getTotalSizeByUserId(int userId);
}
