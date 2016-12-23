package com.hut.common.struct;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dee·Bruce
 *
 */
public class UploadForm {
	
	private String filename;
	
	private String path;
	
	private String acl;
	
	private String contentType;
	
	private MultipartFile file;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAcl() {
		return acl;
	}

	public void setAcl(String acl) {
		this.acl = acl;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}