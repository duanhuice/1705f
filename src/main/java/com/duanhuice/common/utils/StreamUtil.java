/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: StreamUtil.java 
 * @Prject: duanhuice-util
 * @Package: com.duanhuice.common.utils 
 * @Description: TODO
 * @author: Administrator
 * @date: 2019年9月6日 上午11:06:36 
 * @version: V1.0   
 */
package com.duanhuice.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: StreamUtil 
 * @Description: TODO
 * @author:Administrator
 * @date: 2019年9月6日 上午11:06:36  
 */
public class StreamUtil {
	/*
	* 方法1：批量关闭流，参数能传无限个。(3分)
	* 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	*/
	public static void closeAll(AutoCloseable ... ables){
		
		if(ables.length!=0) {
			
			for (AutoCloseable autoCloseable : ables) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
			}
			}
		}
	}
	/*
	* 方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(3分)，要求方法内部调用上面第1个方法关闭流(3分)
	*/
	public static String readTextFile(InputStream src){
		//字节输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b=new byte[1024];
		String str =null;
		int x=0;
		try {
			while((x=src.read(b))!=-1) {
				out.write(b);	
			}
			//
			 str =out.toString("utf-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//关流
		closeAll(out,src);
		return  str;
	}
	/*
	* 方法3：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第2个方法(5分)。* 这是典型的方法重载，记住了吗？少年…
	*/
	public static String readTextFile(File txtFile){
		try {
			return readTextFile(new FileInputStream(txtFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	/**
	 * 
	 * @Title: readText 
	 * @Description: 一行读取
	 * @param src
	 * @return
	 * @return: List<String>
	 */
 public static List<String>readTextForLine(InputStream ins){
	 //创建数组     
	 ArrayList<String> list = new ArrayList<String>();
	 //创建缓冲读取流
       BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String lineStr =null;
         try {
			while((lineStr=reader.readLine())!=null) {
				list.add(lineStr);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(ins,reader);
		}
         return list;
        
 
 
 }
}
