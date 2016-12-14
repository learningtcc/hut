package com.hut.file.utils;

import java.nio.charset.Charset;


/**
 * @author Dee·Bruce
 *
 */
public class Buffer {
	
	public static  final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

	private byte[] bytes;
	private Charset charset;

	public Buffer(byte[] bytes) {
		this(bytes, DEFAULT_CHARSET);
	}
	
	private Buffer(byte[] bytes, Charset charset) {
		super();
		this.bytes = bytes;
		this.charset = DEFAULT_CHARSET;
	}
	
	public Buffer(String str){
		this(str.getBytes(DEFAULT_CHARSET));
	}
	
	
	public Buffer(String str, Charset charset){
		this(str.getBytes(charset),charset);
	}
	
	
	public  byte[] toBinary(){
		return bytes;
	}
	
	public String toHex(){
		return Utils.toHexString(bytes);
	}
	
	public String toBase64(){
		return new String(Base64.encode(bytes), charset);
	}

	@Override
	public String toString() {
		return new String(bytes, DEFAULT_CHARSET);
	}
	
}
