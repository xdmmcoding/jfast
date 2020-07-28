package com.jfast.vo;

import java.io.Serializable;

/**
 * <h1>操作结果</h1>
 * <p>
 *     表示系统内一次动作执行的结果, 其数据项包括:
 * </p>
 * <ul>
 *     <li>是否成功(success)</li>
 *     <li>错误码(code)</li>
 *     <li>错误消息(message)</li>
 * </ul>
 */
public class OptResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误消息
     */
    private String message;

    public OptResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
