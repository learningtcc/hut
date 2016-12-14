package com.hut.file.service;


import com.hut.file.pojos.PersistentFile;

/**
 * @author Dee·Bruce
 *
 */

public interface FileUploader {
	
	String INDEX_HEAD ="TFS://";
	
	String createIndex(PersistentFile persistentFile, byte[] data);
	
	byte[] getFileData(String index);

}
