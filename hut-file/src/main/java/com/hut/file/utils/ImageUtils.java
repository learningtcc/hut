package com.hut.file.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 *  Thumbnails对图片各种操作
 */
public  abstract class ImageUtils {
	
	//旋转
	public static byte[] rotate(byte[] image,int rotate) throws Exception {

		//rotate(角度){正数：顺时针 负数：逆时针}
		ByteArrayInputStream in = new ByteArrayInputStream(image);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Thumbnails.of(in)
				.scale(1)
		        .rotate(rotate) 
		        .toOutputStream(out);

		return out.toByteArray();
	}
	
	//水印
	public static byte[] watermark(byte[] image,BufferedImage watermark) throws Exception {
		//watermark(位置，水印图，透明度)
		ByteArrayInputStream in = new ByteArrayInputStream(image);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		
		Thumbnails.of(in) 
		        .watermark(Positions.BOTTOM_RIGHT, watermark, 0.5f) 
                .outputQuality(0.8f) 
                .toOutputStream(out);
		
		return out.toByteArray();
	}
	
	
	//转化图像格式
	public static byte[] format(byte[] image,String format) throws Exception {
		//outputFormat(图像格式)
		ByteArrayInputStream in = new ByteArrayInputStream(image);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		
		Thumbnails.of(in) 
                .outputFormat(format) 
                	.toOutputStream(out);
		
		return out.toByteArray();
	}
	
	/**
	 *  等比例进行缩放 生成图片
	 */
	public static byte[]  autoNarrow(byte[] bytes){
		ByteArrayOutputStream  out = new ByteArrayOutputStream();
		autoNarrow(out, bytes);
		return out.toByteArray();
	}
	
	/**
	 *  等比例进行缩放 生成图片
	 */
	public static void  autoNarrow(OutputStream out,byte[] bytes){
		ByteArrayInputStream in  = new ByteArrayInputStream(bytes);
		autoNarrow(out, in);
	}
	
	/**
	 *  等比例进行缩放 生成图片
	 */
	public static void autoNarrow(OutputStream out,InputStream in){
		try {
			Thumbnails.of(in).scale(0.75f).toOutputStream(out);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 *  指定大小进行缩放 生成图片
	 */
	public static byte[]  sizeNarrow(byte[] bytes,int width,int height){
		ByteArrayOutputStream  out = new ByteArrayOutputStream();
		sizeNarrow(out, bytes, width, height);
		return out.toByteArray();
	}
    public static byte[]  sizeNarrow(byte[] bytes,Area area){
        return sizeNarrow(bytes, area.getWidth(), area.getHeight());
    }
	
	/**
	 *  指定大小进行缩放 生成图片
	 */
	public static void  sizeNarrow(OutputStream out,byte[] bytes,int width,int height){
		ByteArrayInputStream in  = new ByteArrayInputStream(bytes);
		sizeNarrow(out, in, width, height);
	}
	
	/**
	 *  指定大小进行缩放 生成图片
	 */
	public static void sizeNarrow(OutputStream out,InputStream in,int width,int height){
		
		try {
			Thumbnails.of(in)
					//.scale(0.75f)
					.size(width, height).toOutputStream(out);

		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
