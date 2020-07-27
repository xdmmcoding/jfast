package com.jfast.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class Common {
	public static String SHA1(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String toHexString(byte[] keyData) {
		if (keyData == null) {
			return null;
		}
		int expectedStringLen = keyData.length * 2;
		StringBuilder sb = new StringBuilder(expectedStringLen);
		for (int i = 0; i < keyData.length; i++) {
			String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
			if (hexStr.length() == 1) {
				hexStr = "0" + hexStr;
			}
			sb.append(hexStr);
		}
		return sb.toString();
	}
	
	public static String getClientIP(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
   
    
    
    public static String getAppType(String baseos) {
		String appType;
		if(("Android").equals(baseos)) {
			appType="1";
		} else if(("iPhone").equals(baseos)) {
			appType="2";
		}  else if(("iPad").equals(baseos)) {
			appType="3";
		}  else {
			appType="4";
		}
		return appType;
    }
    
	/**
     * 去掉文本中的html标签
     *
     * @param inputString
     * @return
     */
    public static String html2Text(String inputString) {
        if (StringUtils.isEmpty(inputString)) {
            return null;
        }
        String htmlStr = inputString;
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script,
                    Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern
                    .compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern
                    .compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

            // 替换&amp;nbsp;
            textStr = textStr.replaceAll("&amp;", "").replaceAll("nbsp;", "");

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
    
    public static String getRandomStr(int len) {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder key = new StringBuilder();
		Random rdm = new Random();
		for (int i = 0; i < len; i++) {
			key.append(chars.charAt(rdm.nextInt(62)));
		}
		return key.toString();
	}
    
    public static String getRandomNumber(int size){
		Random random = new Random();		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < size; i++){
			int key = random.nextInt(10);
			if(i==0 && key == 0){ 
				key = 1;
			}
			sb.append(key); 
		}		
		return sb.toString();
	}
    public static final String DOUBLE = ".##"; // double的小数位
 // 把数值转为金钱大写
 	public static String valuesToString(Double value) {
 		if (value <= 0)
 			return "";
 		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
 		char[] vunit = { '万', '亿' }; // 段名表示
 		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
 		BigDecimal db = new BigDecimal(value);
 		db = new BigDecimal(decimalTo2ToString(db));// 保留两位小数
 		String str = db.toPlainString().replace(".", "");// 去掉小数点
 		String valStr = str; // 转化成字符串
 		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
 		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

 		String prefix = ""; // 整数部分转化的结果
 		String suffix = ""; // 小数部分转化的结果
 		// 处理小数点后面的数 如果加整则去掉下面注释 把注释下面那行代码去掉
// 		if (rail.equals("00")) { // 如果小数部分为0
//// 			suffix = "整";
// 			suffix = "";
// 		} else {
// 		suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
// 		} 
 		suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
 		// 处理小数点前面的数
 		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
 		boolean preZero = false; // 标志当前位的上一位是否为有效0位（如万位的0对千位无效）
 		byte zeroSerNum = 0; // 连续出现0的次数
 		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
 			int idx = (chDig.length - i - 1) % 4; // 取段内位置
 			int vidx = (chDig.length - i - 1) / 4; // 取段位置
 			if (chDig[i] == '0') { // 如果当前字符是0
 				preZero = true;
 				zeroSerNum++; // 连续0次数递增
 				if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
 					prefix += vunit[vidx - 1];
 					preZero = false; // 不管上一位是否为0，置为无效0位
 				}
 			} else {
 				zeroSerNum = 0; // 连续0次数清零
 				if (preZero) { // 上一位为有效0位
 					prefix += digit[0]; // 只有在这地方用到'零'
 					preZero = false;
 				}
 				prefix += digit[chDig[i] - '0']; // 转化该数字表示
 				if (idx > 0)
 					prefix += hunit[idx - 1];
 				if (idx == 0 && vidx > 0) {
 					prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
 				}
 			}
 		}

 		if (prefix.length() > 0)
 			prefix += '元'; // 如果整数部分存在,则有圆的字样
 		return prefix + suffix; // 返回正确表示
 	}
 	/**
	 * 金额类型数据 小数位转为两位  不够两位加零
	 * 
	 * @param decimal
	 * @return
	 */
	public static String decimalTo2ToString(BigDecimal decimal) {
		Double temp = decimal.doubleValue();
		DecimalFormat format = new DecimalFormat(DOUBLE);
		String string = format.format(temp);
		int i=string.indexOf(".");
		if(i!=-1){
			String s=string.substring(i+1,string.length());
			if(s.length()<2){
				return string+"0";
			}
		}else{
			return string+"00";
		}
		return string;
	}
	/**
	 * 
	 * @Title: formatterBigMoney 
	 * @Description: 大写金额格式化
	 * @param bigMoney
	 * @return
	 * @author fuxiaodong-lhq  2017年7月5日 下午5:53:24
	 */
	public static String formatterBigMoney(String bigMoney){
		if (null != bigMoney) {
			//去掉开头的零
			while(true){
				if (bigMoney.startsWith("零")) {
					bigMoney = bigMoney.substring(2);
				}else {
					break;
				}
			}
			// 零百(千,万,十等) -> 零
			if(bigMoney.startsWith("零拾")){
				bigMoney = bigMoney.replace("零万", "零").replace("零仟", "零").replace("零佰", "零").replace("零拾", "零").replace("零元", "元");
			}else{
				bigMoney = bigMoney.replace("零万", "万").replace("零仟", "零").replace("零佰", "零").replace("零拾", "零").replace("零元", "元");
			}
			while(true){
				if (bigMoney.contains("零零")) {
					bigMoney = bigMoney.replace("零零", "零");
				}else {
					break;
				}
			}	
			bigMoney = bigMoney.replace("零元", "元");
		}
		//如果出现壹分 则转换成零角零分
		if (2 == bigMoney.length()) {
			bigMoney = "零角" + bigMoney;
		}
		return bigMoney;
	}
	
	public static String formatterBigMoney(Double value){
		String toDrawMoneyS = valuesToString(value);
		return formatterBigMoney(toDrawMoneyS).replace("零角零分", "整");
		
	}
}
