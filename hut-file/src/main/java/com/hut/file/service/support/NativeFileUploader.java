package com.hut.file.service.support;

import com.hut.file.pojos.PersistentFile;
import com.hut.file.service.FileUploader;
import com.hut.common.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Dee·Bruce
 *
 *本地存储方式为按照时间
 *   /{year}/{month}/{day}/{uuid}.{format}
 *   
 */
@Component
public class NativeFileUploader implements FileUploader {
	
	private static final boolean isWindows = !File.separator.equals("/");
	private static final String NATIVE_HEAD = INDEX_HEAD+"local";
	
	@Value("${uploader.pathRoot:../../wdcf-data}")
	private String pathRoot;
	
	private String  createFile(String filename,byte[] data){
		try {
			Date now = new Date();
			
			String createPath = createDatePath(now);
		
			File directory = new File(complete(createPath));
			
			if(!directory.exists() && directory.mkdirs());
			
			String newName =createName(filename);
		
			
			FileUtils.writeByteArrayToFile(createNativeFile(directory+"/"+newName), data);
			
			return createPath+"/"+newName;
		} catch (IOException e) {
			throw new IllegalArgumentException("文件管理异常 ："+e.getMessage());
		}
	}
	
	
	private  String createDatePath(Date date){
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int[] times = new int[]{
				cl.get(Calendar.YEAR),
				cl.get(Calendar.MONTH),
				cl.get(Calendar.DAY_OF_MONTH)
		};
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<times.length;i++){
			sb.append("/").append(times[i]);
		}
		return sb.toString();
	}
	
	private String complete(String path){
		return this.pathRoot + path;
	}


	private String createName(String fileName) {
		int index =  fileName.lastIndexOf(".");
		
		String format =index>0?fileName.substring(index,fileName.length())+"":"";
		
		return UUID.randomUUID().toString()+format;
	}
	
	
	public File createNativeFile(String filepath){
		filepath = isWindows ? StringUtils.replace(filepath, "/", "\\"):filepath;
		return new File(filepath);
	}

	public byte[] getFileData(String index) {
		if(!Utils.isEmpty(index)){
			if(index.startsWith(NATIVE_HEAD)){
				index = index.substring(NATIVE_HEAD.length(), index.length());
				
				File file = createNativeFile(complete(index));
				
				try {
					return FileUtils.readFileToByteArray(file);
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
		return null;
	}

	public String createIndex(PersistentFile persistentFile, byte[] data) {
		String index =  createFile(persistentFile.getFilename(), data);
		return NATIVE_HEAD+index;
	}
	
}
