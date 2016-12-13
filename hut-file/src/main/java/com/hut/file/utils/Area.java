package com.hut.file.utils;

import org.springframework.util.StringUtils;

/**
 * Created by Jared on 2016/12/11.
 */
public class Area {

	private static final int  DEFAULT_SIZE = 100;

	private int height;
	private int width;

	public Area(String value){
		if(StringUtils.isEmpty(value)){
			height=DEFAULT_SIZE;
			width =DEFAULT_SIZE;
		}
		else{
			value  = value.toUpperCase();
			
			int index = value.lastIndexOf("*");

			//300*400
			if(index>0){
					String[] temp = StringUtils.split(value, "*");
					this.width = Integer.parseInt(temp[0]);
					this.height = Integer.parseInt(temp[1]);
			}
			else{
				this.width = Integer.parseInt(value);
				this.height = this.width;
			}
			if (height < 5 || height > 900) {
				this.height = DEFAULT_SIZE;
			}
			if (width < 5 || width > 900) {
				this.width = DEFAULT_SIZE;
			}

		}
	
	}

	public Area(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}

}
