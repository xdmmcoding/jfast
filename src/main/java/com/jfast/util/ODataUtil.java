package com.jfast.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import com.jfast.core.http.HttpClientUtil;

import net.sf.json.JSONObject;


public class ODataUtil {
	
	private static String ODataUrl=PropertiesUtil.getPropValue("application.yml", "odata.url");
	private static String appId=PropertiesUtil.getPropValue("application.yml", "odata.appid");
	private static String appKey=PropertiesUtil.getPropValue("application.yml", "odata.appkey");
	static String did = "{123456}";
	static String mobile = "";
	static String location = "0,0";
	static String ip = "";
	
	public static JSONObject GetObjDataLists(String type, String fields, String query, String order, String group,
			int start, int length) {
		// 时间戳
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		// 加密指纹（注意：指纹加密的数据都是未经过URL编码前的数据）
		String txt = "timestamp=" + timestamp + "&appid=" + appId + "&appkey=" + appKey + "&ip=" + ip + "&location="
				+ location + "&mobile=" + mobile + "&did=" + did + "&op=data_gets&type=" + type + "&fields=" + fields
				+ "&query=" + query + "&group=" + group + "&order=" + order;
		SHA1 sha1 = new SHA1();
		String sign = sha1.Digest(txt, "UTF-8").toLowerCase();
		String remoteUrl = "";
		try {
			remoteUrl = ODataUrl + "api/db/" + URLEncoder.encode(type, "utf-8");
			System.out.println("odataurl:"+remoteUrl);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			remoteUrl = remoteUrl + "?fields=" + URLEncoder.encode(fields, "utf-8") + "&query="
					+ URLEncoder.encode(query, "utf-8") + "&group=" + URLEncoder.encode(group, "utf-8") + "&order="
					+ URLEncoder.encode(order, "utf-8") + "&start=" + String.valueOf(start) + "&length="
					+ String.valueOf(length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		Map<String, String> mapHeader = new TreeMap<String, String>();
		mapHeader.put("app_id", appId);
		mapHeader.put("timestamp", timestamp);
		mapHeader.put("sign", sign);
		mapHeader.put("ip", ip);
		mapHeader.put("did", did);
		mapHeader.put("mobile", mobile);
		mapHeader.put("location", location);
		
		String result = null;
		try {
			result = ((HttpClientUtil) SpringUtils.getBean("HttpClientUtil")).doGet(remoteUrl, null, mapHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result != null){
			return JSONObject.fromObject(result);
		}
		return null;
	}
}
