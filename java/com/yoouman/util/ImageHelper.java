package com.yoouman.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageHelper {
	/*
	 * 根据尺寸图片居中裁剪
	 */
	 public static void cutCenterImage(String src,String dest,int w,int h) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);  
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));           
  
	 }
	/*
	 * 图片裁剪二分之一
	 */
	 public static void cutHalfImage(String src,String dest) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         int width = reader.getWidth(imageIndex)/2; 
         int height = reader.getHeight(imageIndex)/2; 
         Rectangle rect = new Rectangle(width/2, height/2, width, height); 
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));   
	 }
	/**
	 * 图片剪切接口
	 * @param src	原图片地址
	 * @param dest	剪切后图片地址
	 * @param x	
	 * @param y
	 * @param w
	 * @param h
	 * @param imageTTF	文件后缀/格式
	 * @throws IOException
	 */
    public static void cutImage(String src,String dest,int x,int y,int w,int h,String imageTTF) throws IOException{ 
           Iterator iterator = ImageIO.getImageReadersByFormatName(imageTTF);
           ImageReader reader = (ImageReader)iterator.next(); 
           InputStream in=new FileInputStream(src);
           ImageInputStream iis = ImageIO.createImageInputStream(in); 
           reader.setInput(iis, true); 
           ImageReadParam param = reader.getDefaultReadParam(); 
           Rectangle rect = new Rectangle(x, y, w,h);  
           param.setSourceRegion(rect); 
           BufferedImage bi = reader.read(0,param);   
           ImageIO.write(bi, "jpg", new File(dest));           

    } 
    /*
     * 图片缩放
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
		double wr=0,hr=0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		wr=w*1.0/bufImg.getWidth();
		hr=h*1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
