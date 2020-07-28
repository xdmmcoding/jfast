/**
 *  Copyright (c) 2015 Neusoft.com corporation All Rights Reserved.
 */

package com.jfast.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  此处进行功能描述。
 *
 *  @author Hp
 *  @version 1.0
 *
 *  <pre>
 *  使用范例：
 *  创建时间:2015-1-15 下午02:51:29
 *  修改历史：
 *     ver     修改日期     修改人  修改内容
 * ──────────────────────────────────
 *   V1.00   2015-1-15   Hp  初版
 *
 *  </pre>
 *
 */
public class PropertiesUtil {
	 public static String getPropValue(String properties, String key)
	    {
	        Properties props = new Properties();
	        InputStream in = null;
	        try
	        {
	            in = PropertiesUtil.class
	                    .getResourceAsStream("/" + properties);
	            props.load(in);
	            in.close();
	            return props.getProperty(key, "");
	        }
	        catch (IOException e)
	        {
	            if(in!=null){
	                try
	                {
	                    in.close();
	                }
	                catch (IOException e1)
	                {
	                    e1.printStackTrace();
	                    return "";
	                }
	            }
	            e.printStackTrace();
	            return "";
	        }

	    }
}

