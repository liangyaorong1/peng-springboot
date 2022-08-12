package com.peng.common.lang;
/**
 * 常量类
 */
public class Constants {

    public final static int OK_CODE = 200;//成功
    public final static int FAIL_CODE = 400;//失败
    public final static int OTHER_FAIL_CODE = 333;    // 其它错误
    public final static String OK_MSG = "请求成功";
    public final static String FAIL_MSG = "请求失败";
    public final static int STATUS_0 = 0;   // 可用状态
    public final static int STATUS_1 = 1;   // 禁用状态

    public final static String CAPTCHA_KEY="captcha";
    public final static String EMAIL_KEY="emailCode";
    public final static String CACHE_NAME = "KACache";

    public final static String XSS_NOTICE_KEY = "notice";

    /**
     * 用户文件上传基础路径
     */
    public final static String BASE_USER_FILE_PATH = "";

    // --------------- 删除标志 start ------------------------------
    /** 已删除 */
    public static final String ISDELETED = "Y";

    /** 未删除 */
    public static final String NODELETED = "N";
}
