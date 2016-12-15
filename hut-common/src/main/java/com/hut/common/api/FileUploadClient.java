package com.hut.common.api;

import com.hut.common.messages.Msg;

import java.util.Map;

/**
 * Created by Jared on 2016/12/15.
 */
public interface FileUploadClient {

    Msg<Map> upload(String filename, String userId, byte[] data);

    Map totalCount(String userId);

    Map totalSize(String userId);
}
