package com.hut.file.utils;


import com.hut.common.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Jared
 */
public class Crypto {

		private MessageDigest  digest;
		
		public Crypto(String algorithm){
			try {
				digest = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		public Crypto update(String data,String encoding){
			try {
				encoding = Utils.isEmpty(encoding)?"utf-8":encoding;
				digest.update(data.getBytes(encoding));
			} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException(e);
			}
			return this;
		}
		
		public Crypto update(byte[] data){
			digest.update(data);
			return this;
		}
		
		public Buffer digest(byte[] salt){
			byte[] result =	digest.digest(salt);
			return new Buffer(result);
		}
		public Buffer digest(String salt){
			return digest(salt,"utf-8");
		}
		
		public Buffer digest(String salt,String encoding){
			encoding = Utils.isEmpty(encoding)?"utf-8":encoding;
			try {
				byte[] result = digest.digest(salt.getBytes(encoding));
				return new Buffer(result);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		
		public Buffer digest(){
			byte[] result =	digest.digest();
			return new Buffer(result);
		}
		
		public byte[] digestBinary(){
			return digest().toBinary();
		}
		
		public String digestHex(){
			return digest().toHex();
		}
		
		public String digestBase64(){
			return digest().toBase64();
		}

}
