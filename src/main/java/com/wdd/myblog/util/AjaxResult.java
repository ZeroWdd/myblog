package com.wdd.myblog.util;

/**
 * @Classname AjaxResult
 * @Description ajax json 返回数据
 * @Date 2019/7/7 13.48
 * @Created by WDD
 */
public class AjaxResult<T> {
    private boolean success;
    private String message;
    private String type;
    private String img;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
