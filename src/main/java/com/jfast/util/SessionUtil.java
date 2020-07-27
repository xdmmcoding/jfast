package com.jfast.util;



import javax.servlet.http.HttpServletRequest;


/**
 * session工具类
 */
public class SessionUtil {
    /**
     * 设置Session属性
     * @param request
     * @param key
     * @param value
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * 删除session属性
     * @param request
     * @param key
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }

    /**
     * 获取Session属性
     * @param request
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T getAttribute(HttpServletRequest request, String key) {
        Object value = request.getSession().getAttribute(key);
        if (value != null) {
            try {
                return (T)value;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
