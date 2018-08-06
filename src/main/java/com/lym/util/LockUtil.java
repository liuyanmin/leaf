package com.lym.util;

/**
 * @ClassName LockUtil
 * @Description 多线程同步锁
 * @Author LYM
 * @Date 2018/8/5 9:02
 * @Version 1.0
 */
public class LockUtil {

    private static final LockUtil lock = new LockUtil();

    private LockUtil() {
    }

    public static LockUtil getInstance() {
        return lock;
    }

}
