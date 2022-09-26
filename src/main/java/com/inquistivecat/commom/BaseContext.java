package com.inquistivecat.commom;

/**
 * 基于ThreadLocal封装工具类，来保存获取当前线程用户ID
 * @author hp
 */
public class BaseContext {
    private static ThreadLocal<Long>  threadLocal =  new ThreadLocal<Long>();
    public static  void setCurrentId(long id) {
        threadLocal.set(id);
    }
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
