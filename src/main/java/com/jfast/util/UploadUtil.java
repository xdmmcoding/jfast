package com.jfast.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);
    //获取配置参数
    private static final String type = PropertiesUtil.getPropValue("upload.properties", "type");
    private static final String host = PropertiesUtil.getPropValue("upload.properties", "host");
	private static final String username = PropertiesUtil.getPropValue("upload.properties", "username");
	private static final String password = PropertiesUtil.getPropValue("upload.properties", "password");
	private static final String basepath = PropertiesUtil.getPropValue("upload.properties", "basepath");
	private static final String port = PropertiesUtil.getPropValue("upload.properties", "port");
    /**
     * 
     * @Description 上传文件  
     * @Author      lixudong  
     * @Date        2019年8月7日 上午9:17:34  
     * @param @param filePath
     * @param @param filename
     * @param @param input
     * @param @return 参数  
     * @return boolean 返回类型   
     * @throws
     */
    public static boolean upload(String path, String filename, InputStream input) {
        boolean result = false;
        try {
        	// 上传到本地文件夹
            if("1".equals(type)){
            	if(input == null) return false;
            	result = uploadFile(read(input),path,filename);
            }
            // 上传到ftp服务器
            if("2".equals(type)){
            	result = uploadFtp(path, filename, input);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Description: 向FTP服务器上传单个文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFtp(String path, String filename, InputStream input) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, Integer.valueOf(port));// 连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            if(StringUtils.isNotEmpty(basepath)){
            	path = basepath +"/"+ path;
            }
            if(!ftp.changeWorkingDirectory(path)) {
                if (!ftp.makeDirectory(path)) {
                	return false;
                } else {
                    ftp.changeWorkingDirectory(path);
                }
            }

            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
            	return false;
            }
            input.close();
            ftp.logout();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                	logger.error(ioe.getMessage(),ioe);
                }
            }
        }
        return true;
    }
    
    /**
     * 
     * @Description 上传到本地文件夹  
     * @Author      lixudong  
     * @Date        2019年8月7日 上午9:41:42  
     * @param @param file
     * @param @param filePath
     * @param @param fileName
     * @param @throws Exception 参数  
     * @return void 返回类型   
     * @throws
     */
    public static boolean uploadFile(byte[] file, String filePath, String fileName){
    	boolean res = false;
    	FileOutputStream out = null;
    	try {
    		filePath = basepath+"/"+filePath;
	        File targetFile = new File(filePath);
	        if(!targetFile.exists()){
	            targetFile.mkdirs();
	        }
	        /*int index = fileName.indexOf("/");
	        if(index > -1){
	            String fName = fileName.substring(0,fileName.lastIndexOf("/"));
	
	            if(fName!=null){
	                File fFile = new File(filePath+"/"+fName);
	                if(!fFile.exists()){
	                    fFile.mkdirs();
	                }
	            }
	        }*/
	        out =  new FileOutputStream(filePath+"/"+fileName);
	        out.write(file);
	        out.flush();
	        res =  true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try {
				if(out != null )out.close();
			} catch (IOException e) {
				
			}
        }
    	return res;
    }
    /**
   	 * 下载文件时，针对不同浏览器，进行附件名的编码
   	 * 
   	 * @param filename
   	 *            下载文件名
   	 * @param agent
   	 *            客户端浏览器
   	 * @return 编码后的下载附件名
   	 * @throws IOException
   	 */
   	public static String encodeDownloadFilename(String filename, String agent) throws IOException {
   		if (agent.contains("Firefox")) { // 火狐浏览器
   			filename = "=?UTF-8?B?" + new Base64().encode(filename.getBytes("utf-8")) + "?=";
   			filename = filename.replaceAll("\r\n", "");
   		} else { // IE及其他浏览器
   			filename = URLEncoder.encode(filename, "utf-8");
   			filename = filename.replace("+", " ");
   		}
   		return filename;
   	}
   	
   	/**
   	 * 
     * @Description InputStream 转 byte  
     * @Author      lixudong  
     * @Date        2019年8月7日 上午9:48:51  
     * @param @param inputStream
     * @param @return
     * @param @throws IOException 参数  
     * @return byte[] 返回类型   
     * @throws
   	 */
   	public static byte[] read(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
   	
   	/**
   	 * 
     * @Description 将源文件名，修改为UUID生成的文件名  
     * @Author      lixudong  
     * @Date        2019年8月13日 下午1:50:50  
     * @param @param originalName
     * @param @return
     * @param @throws IOException 参数  
     * @return String 返回类型   
     * @throws
   	 */
   	public static String randomNameUUID(String originalName) throws IOException {
   		int index = originalName.lastIndexOf(".");
   		//将图片名称全部生成随机数
   		String lastName = originalName.substring(index);
   		return  UUIDGenerator.getUUID() + lastName;
   	}
   	/**
   	 * 
     * @Description byte转string 
     * @Author      lixudong  
     * @Date        2019年10月21日 上午8:57:44  
     * @param @param bytes
     * @param @return
     * @param @throws Exception 参数  
     * @return String 返回类型   
     * @throws
   	 */
   	public static String getByteToString(byte[] bytes){
   		try {
   			Base64 b64 = new Base64();			
   			if(bytes !=null)return b64.encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
   		return null;
	}
   	
   	public static String bytesToHexString(byte[] bArr) {
   	    StringBuffer sb = new StringBuffer(bArr.length);
   	    String sTmp;

   	    for (int i = 0; i < bArr.length; i++) {
   	        sTmp = Integer.toHexString(0xFF & bArr[i]);
   	        if (sTmp.length() < 2)
   	            sb.append(0);
   	        sb.append(sTmp.toUpperCase());
   	    }

   	    return sb.toString();
   	}

   	public static String getPicType(InputStream is){
   		//读取文件的前几个字节来判断图片格式
		byte[] b = new byte[4];
		try {
			is.read(b, 0, b.length);
			String type = bytesToHexString(b).toUpperCase();
			if (type.contains("FFD8FF")) {
				return "jpeg";
			} else if (type.contains("89504E47")) {
				return "png";
			} else if (type.contains("47494638")) {
				return "gif";
			} else if (type.contains("424D")) {
				return "bmp";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null)
				try {
					is.close();
				} catch (Exception e){
					
				}
		}
		return "jpeg";
   	}
}