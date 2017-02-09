package com.hut.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jared
 * @version  1.0.2
 */
public class Utils {


	private static final char[] HEX = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};


	public static String toMd5String(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for (byte b:bytes
			 ) {

			int temp = b&0xff;
			String s = Integer.toHexString(temp);
			s = s.length()>1?s : "0"+s;
			sb.append(s);
		}
		return sb.toString();
	}
	
	/**
	 * 杞崲鎴� 16杩涘埗瀛楃涓层��
	 */
	public static String toHexString(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2*nBytes];

        int j = 0;
        for (int i=0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4 ];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }
        
        return new String(result);
    }

	/**
	 * 灏嗗崄鍏繘鍒跺瓧绗︿覆 杞崲鎴恇yte鏁扮粍
	 */
    public static byte[] decodeHexString(String s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException("Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i+1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException("Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }
	
	
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	
	
	public static boolean isEmailAddress(String str) {
		return matcher(str,
				"^[0-9a-zA-Z\\-\\_\\.]+@(([0-9a-zA-Z]+)[.])+[a-z]{2,4}$");
	}

	/**
	 * 楠岃瘉鏄惁鏄墜鏈哄彿 浣跨敤浜嗘姝ｅ垯琛ㄨ揪 锛� ^[1][3,4,5,7,8][0-9]{9}$
	 *
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		return matcher(str, "^[1][3,4,5,7,8][0-9]{9}$");
	}

	/**
	 * 姝ｅ垯鍖归厤
	 *
	 * @param String
	 *            -- 瑕侀獙璇佺殑鏂囧瓧
	 * @param test
	 *            --- 姝ｅ垯琛ㄨ揪寮�
	 * @return
	 */
	public static boolean matcher(String String, String test) {
		if (isEmpty(String)) {
			return false;
		}
		Pattern pattern = Pattern.compile(test);
		Matcher matcher = pattern.matcher(String);
		return matcher.matches();
	}



	/**
	 * 灏� 閫氶厤绗� 鎸夐『搴忔浛鎹㈡垚 object 鏁扮粍
	 * @param source
	 * @param placeholder
	 * @param objects
	 * @return
	 */
	public static String placeholderReplace(String source,String placeholder,Object[] objects){
		if(isEmpty(source) || isEmpty(placeholder)){
			return source;
		}
		StringBuilder sb = new StringBuilder();
		int pos =0;
		int index = source.indexOf(placeholder);
		int oldLen = placeholder.length();
		int counter = 0;
		while(index>=0){
			sb.append(source.substring(pos, index));
			sb.append(String.valueOf(objects[counter++]));
			pos = oldLen+index;
			index = source.indexOf(placeholder,pos);
		}
		sb.append(source.substring(pos));
		return sb.toString();
	}


	/**
	 * 闂彿閫氶厤绗︽浛鎹�
	 * @param source
	 * @param objects
	 * @return
	 */
	public static String questionMarkReplace(String source,Object...objects){
		return placeholderReplace(source, "?", objects);
	}


	/**
	 *鏄惁涓轰腑鏂囧瓧绗�
	 * @param c
	 * @return
	 */
	public static boolean isChineseCharacter(char c){
		int i = (int)c;
		return (i>=0x4e00 && i<=0x9fa5);
	}

	public static String urlEncoding(String url){
		try {

			return URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {

			throw new IllegalArgumentException(e);
		}
	}


	public static String pathChain(String... paths) {
        String realpath =null;

        if(paths.length<2){
            realpath = paths[0];
        }
        else{
            String pre=null;
            StringBuilder sb = new StringBuilder();
            for (String path : paths) {
                if (pre == null) {
                    pre = path;
                }
                else{
                    sb.append(stringpathchain(pre, path));
                    pre=null;
                }
            }
            if (pre == null) {
                realpath= sb.toString();
            }
            else{
                realpath = stringpathchain(sb.toString(),pre);
            }
        }

		if (realpath.startsWith("http://")) {
			return realpath;
		}

        return realpath.charAt(0)=='/'?realpath : '/'+realpath;
	}

    private static String stringpathchain(String path1,String path2) {
    	if(isEmpty(path1)){
    		return path2;
    	}
    	if(isEmpty(path2)){
    		return path1;
    	}
        char preend = path1.charAt(path1.length() - 1);
        char endstart = path2.charAt(0);

        if(preend == '/' && endstart =='/'){
            return path1 + path2.substring(1);
        }
        else if(preend !='/' && endstart !='/'){
            return path1 + '/'+ path2;
        }
        else{
            return  path1 + path2;
        }
    }

}
