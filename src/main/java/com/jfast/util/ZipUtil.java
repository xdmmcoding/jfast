package com.jfast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
	/**
	 * 
     * @Description 解压zip文件
     * @Author      xd  
     * @Date        2019年11月26日 上午10:11:40  
     * @param @param srcFile
     * @param @param destDirPath
     * @param @throws RuntimeException 参数  
     * @return void 返回类型   
     * @throws
	 */
	public static void unZip(File srcFile, String destDirPath) throws RuntimeException {	
		long start = System.currentTimeMillis();
		// 判断源文件是否存在
		if (!srcFile.exists()) {
			throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		}
		// 开始解压
		ZipFile zipFile = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			zipFile = new ZipFile(srcFile);
		    Enumeration<?> entries = zipFile.entries();
		    while (entries.hasMoreElements()) {
		    	ZipEntry entry = (ZipEntry) entries.nextElement();
		    	// 如果是文件夹，就创建个文件夹
		    	if (entry.isDirectory()) {
		    		String dirPath = destDirPath + "/" + entry.getName();	
		            File dir = new File(dirPath);	
		            dir.mkdirs();
		    	} else {	
	                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
	                File targetFile = new File(destDirPath + "/" + entry.getName());
	                // 保证这个文件的父文件夹必须要存在
	                if(!targetFile.getParentFile().exists()){
	                    targetFile.getParentFile().mkdirs();
	                }
	                targetFile.createNewFile();
	                // 将压缩文件内容写入到这个文件中
	                is = zipFile.getInputStream(entry);
	                fos = new FileOutputStream(targetFile);
	                int len;
	                byte[] buf = new byte[102400];
	                while ((len = is.read(buf)) != -1) {
	                    fos.write(buf, 0, len);
	                }
	                // 关流顺序，先打开的后关闭
	                fos.close();
	                is.close();	
		        }
		    }	
            long end = System.currentTimeMillis();	
            System.out.println("解压完成，耗时：" + (end - start) +" ms");	
		} catch (Exception e) {	
			throw new RuntimeException("unzip error from ZipUtils", e);	
	    } finally {	
	    	if(fos != null){	
	        	try {	
	        		fos.close();	
	        	} catch (IOException e) {	
	        		e.printStackTrace();	
	        	}	
	        }	
	        if(is != null){	
	        	try {	
	        		is.close();	
	        	} catch (IOException e) {	
	        		e.printStackTrace();	
	        	}	
	        }
	        if(zipFile != null){	
	            try {	
	                zipFile.close();	
	            } catch (IOException e) {	
	                e.printStackTrace();	
	            }	
	        }		
	    }	
	}
	
	public static LinkedList<File> folderMethod(String path,String expand) {		
	    File file = new File(path);
	    LinkedList<File> list = new LinkedList<>();        
	    //保存所有pdf文件的对象
	    LinkedList<File> pdfList = new LinkedList<File>();
	    //该路径对应的文件或文件夹是否存在
	    if (file.exists()) {        	
	    	//如果该路径为---文件或空文件夹
	        if (null == file.listFiles()) {
	        	if(file.getAbsolutePath().endsWith(expand)) 
	        		pdfList.add(file);
	        }else {
	        	//将该路径下的所有文件（文件或文件夹）对象加入队列
	            list.addAll(Arrays.asList(file.listFiles()));
	            //遍历该队列     
	            while (!list.isEmpty()) {             	
	            	File firstF = list.removeFirst();                	
	            	//这里不论是文件夹还是文件，只需判断是否以“.pdf”结尾
	        		if(firstF.getAbsolutePath().endsWith(expand))pdfList.add(firstF);               	
	            }
	        }
	
	    }
	    return pdfList;
	}
	/**
	 * 
     * @Description  删除文件  
     * @Author      xd  
     * @Date        2019年11月26日 上午11:06:52  
     * @param @param file
     * @param @return 参数  
     * @return boolean 返回类型   
     * @throws
	 */
	public static boolean delFile(File file) {
		if (!file.exists()) {
		    return false;
		}		
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				delFile(f);
			}
		}
		return file.delete();
	}
}
